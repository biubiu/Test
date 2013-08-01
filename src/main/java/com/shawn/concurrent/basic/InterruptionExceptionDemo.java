package com.shawn.concurrent.basic;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class InterruptionExceptionDemo {
	public static void main(String[] args) {
		Thread thread =new Thread(new FileSearch("/Users/caocao024/workspace/Test", "InterruptionExceptionDemo.java"));
		thread.start();
		try {
			TimeUnit.MILLISECONDS.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
}

class FileSearch implements Runnable {
	private String initPath;
	private String fileName;

	public FileSearch(String initPath, String fileName) {
		this.initPath = initPath;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		File file = new File(initPath);
		if (file.isDirectory()) {
			try {
				directoryProcess(file);
			} catch (InterruptedException e) {
				System.out.printf("%s: The search has been interrupted ",
						Thread.currentThread().getName());
			}
		}
	}

	private void directoryProcess(File file) throws InterruptedException {
		File list[] = file.listFiles();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].isDirectory()) {
					directoryProcess(list[i]);
				} else {
					fileProcess(list[i]);
				}
			}
		}
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

	private void fileProcess(File file) throws InterruptedException {
		if (file.getName().equals(fileName)) {
			System.out.printf("%s : %s \n", Thread.currentThread().getName(),
					file.getAbsoluteFile());
		}
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

}
