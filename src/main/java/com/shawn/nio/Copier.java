package com.shawn.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.time.StopWatch;

import com.google.common.collect.Lists;

public class Copier {

    private CountDownLatch countDownLatch;
    private ExecutorService executorService;
    private String completeSuffix;
    private String storePath;
    private List<File> watchPaths;

    public static void main(String[] args) {
        Copier copier = new Copier(Lists.newArrayList("/opt/apache-tomcat-7.0.42/logs/test_logs"), "COMPLETED", "/home/shawncao/flume/.store");
    }

    public Copier(List<String> dirPath, String suffix, String storePath) {
        this.completeSuffix = suffix;
        this.storePath = storePath;
        for (String path : dirPath) {
            if (Files.exists(Paths.get(path), LinkOption.NOFOLLOW_LINKS)) {
                try {
                    System.out.println("walking file: ---" + Paths.get(path).toString());
                    Files.walkFileTree(Paths.get(path), new ChangedLogVisitor(suffix, watchPaths, storePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void process() {

    }

    private final class ChangedLogVisitor extends SimpleFileVisitor<Path> {

        private String suffix;
        private List<File> watchPaths;
        private String storePath;

        ChangedLogVisitor(String suffix, List<File> watchPaths, String storePath) {

            this.suffix = suffix;
            this.watchPaths = watchPaths;
            this.storePath = storePath;
            System.out.println("file visitor: " + this.toString());
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            if (attrs.isRegularFile() && !checkFileExtension(file.getFileName().toString(), suffix) && (attrs.size() > 0L)) {
                System.out.println("start ");
                StopWatch watch = new StopWatch();
                watch.start();
                Files.copy(file, Paths.get(storePath + "/" + file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                // copyFile(file.toFile(), new File(storePath+"/"+file.getFileName()));
                //copyFileByMemBuffer(file.toFile(), new File(storePath + "/" + file.getFileName()));
                watch.stop();
                System.out.println("finish copying --" + watch.toString());
                watch.reset();
                watch.start();
                File oldFile = file.toFile();
                System.out.println("pat " + (oldFile.getAbsolutePath() + "." + suffix));
                oldFile.renameTo(new File(oldFile.getAbsolutePath() + "." + suffix));
                watch.stop();
                System.out.println("finish rename --" + watch.toString());
            }
            return FileVisitResult.CONTINUE;
        }

        private boolean checkFileExtension(String filename, String suffix) {
            return filename.lastIndexOf(".") == -1 ? false : (filename.substring(filename.lastIndexOf("."))).equals("." + suffix);
        }

        @Override
        public String toString() {

            return ToStringBuilder.reflectionToString(this);
        }

        private void copyFile(File sourceFile, File destFile) throws IOException {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }

            FileChannel source = null;
            FileChannel destination = null;
            try {
                source = new FileInputStream(sourceFile).getChannel();
                destination = new FileOutputStream(destFile).getChannel();
                // destination.transferFrom(source, 0, source.size());
                source.transferTo(0, source.size(), destination);
            } finally {
                if (source != null) {
                    source.close();
                }
                if (destination != null) {
                    destination.close();
                }
            }
        }
    }

    private void copyFileByMemBuffer(File sourceFile, File destFile) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 50);
        FileChannel fin = new RandomAccessFile(sourceFile, "r").getChannel();
        FileChannel fout = new RandomAccessFile(destFile, "rw").getChannel();
        while (true) {
            buffer.clear();

            int flag = fin.read(buffer);
            if (flag == -1) {
                break;
            }
            buffer.flip();
            fout.write(buffer);
        }
        fin.close();
        fout.close();
    }

    private final class FileCopyRunnable implements Runnable {

        public FileCopyRunnable() {

        }

        @Override
        public void run() {

        }
    }
}
