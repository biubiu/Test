package com.shawn.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Shawn cao Date: 13-4-2 Time: AM10:14
 */
public class ConsumerAndProducerWithConditionLock {
    public static void main(String[] args) {
        FileMock fileMock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);

        ProducerCon producer = new ProducerCon(fileMock, buffer);
        Thread threadProducer = new Thread(producer,"producer");


        ConsumerCon[] consumerCons = new ConsumerCon[3];
        Thread threadConsumers[] = new Thread[3];
        for (int i = 0; i < threadConsumers.length; i++) {
            consumerCons[i] = new ConsumerCon( buffer);
            threadConsumers[i] = new  Thread(consumerCons[i],"Consumer"+i);
        }
        threadProducer.start();
        for (int i = 0; i < 3; i++) {
            threadConsumers[i].start();
        }
    }
}

class ConsumerCon implements Runnable {
    private Buffer buffer;

    public ConsumerCon( Buffer buffer) {
        this.buffer = buffer;
    }


    public void run() {
        while (buffer.hasPendingLines()) {
            String line = buffer.get();
            processLine(line);
        }
    }

    private void processLine(String line) {
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(100));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ProducerCon implements Runnable {
    private FileMock fileMock;
    private Buffer buffer;

    public ProducerCon(FileMock fileMock, Buffer buffer) {
        this.fileMock = fileMock;
        this.buffer = buffer;
    }

    public void run() {
        buffer.setPendingLines(true);
        while (fileMock.hasMoreLines()) {
            String line = fileMock.getLine();
            buffer.insert(line);
        }
    }
}

class FileMock {
    private String[] content;
    private int index;

    public FileMock(int size, int length) {
        content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder buffer = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int indice = (int) Math.random() * 255;
                buffer.append((char) indice);
            }
            content[i] = buffer.toString();
        }
        index = 0;
    }

    public boolean hasMoreLines() {
        return index < content.length;
    }

    public String getLine() {
        if (this.hasMoreLines()) {
            System.out.println("Mock: " + (content.length - index));
            return content[index++];
        }
        return null;
    }
}

class Buffer {
    private LinkedList<String> buffer;
    private int maxSize;
    private ReentrantLock lock;
    private Condition lines;
    private Condition space;
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<String>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines = true;
    }

    public void insert(String line) {
        lock.lock();
        try {
            while (buffer.size() == maxSize) {
                space.await();
            }
            buffer.offer(line);
            System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(), buffer.size());
            lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        String line = null;
        lock.lock();
        try {
            while ((buffer.size() == 0) && (hasPendingLines())) {
                lines.await();
            }
            if (hasPendingLines()) {
                line = buffer.poll();
                System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(), buffer.size());
                space.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return line;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }
}
