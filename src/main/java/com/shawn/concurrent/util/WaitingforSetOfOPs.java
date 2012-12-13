package com.shawn.concurrent.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class WaitingforSetOfOPs {
    public static void main(String[] args) {
        VideoConference conference = new VideoConference(10);
        Thread threadConference = new Thread(conference);
        threadConference.start();
        for (int i = 0; i < 10; i++) {
            Participant participant = new Participant(conference, "participant " + i);
            Thread thread = new Thread(participant);
            thread.start();
        }
    }
}

class VideoConference implements Runnable{

    private final CountDownLatch controller;
    public VideoConference(int number) {
        controller = new CountDownLatch(number);
    }

    public void arrive(String name){
        System.out.printf("%s has arrived. ",name);
        controller.countDown();
        System.out.printf("Video conference: wating for %d participants. \n",controller.getCount());
    }
    public void run() {
        System.out.printf("Video conference: Initialization: %d participants.\n",controller.getCount());
        try {
            controller.await();
            System.out.printf("Video conference: All the participants have come \n ");
            System.out.printf("Video conference: Let's start...\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Participant implements Runnable{
    private VideoConference videoConference;
    private String name;

    public Participant(VideoConference videoConference,String name) {
        this.videoConference = videoConference;
        this.name = name;
    }

    public void run() {
        long duration = (long)(Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        videoConference.arrive(name);
    }


}
