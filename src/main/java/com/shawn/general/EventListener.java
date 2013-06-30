package com.shawn.general;

import java.util.concurrent.Executors;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventListener {
    public int intMsg;
    public String strMsg;
    public Long longMsg;
    private Message message;
    private static final EventListener EVENT_LISTENER = new EventListener();
    public static EventListener getInstance(){
        return EVENT_LISTENER;
    }
    @Subscribe
    public void listen(TestEvent testEvent){
        intMsg = (Integer)(testEvent.getMessage());
    }

    @Subscribe
    public void listenStr(String s){
        strMsg = s;
    }

    @Subscribe
    public void listenLong(Long l){
        longMsg = l;
    }

    @Subscribe
    public void onMessage(Message message){
        this.message = message;
    }

    public Message getMessage(){
        return message;
    }
    public int getLastMessage(){
        return intMsg;
    }

    @Test
    public void receiveMultiEvents() throws Exception{

        EventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(2));
        EventListener listener = EventListener.getInstance();
        eventBus.register(listener);

        eventBus.post("hello");
        eventBus.post(123);
        eventBus.post(new Long(1000));

        eventBus.post(new Message("this is message", 1000l, 3));

        System.out.println("thread: " + Thread.currentThread());
/*        Assert.assertTrue(listener.strMsg.equals("hello"));
        Assert.assertTrue(listener.longMsg.equals(new Long(1000)));*/
        System.out.println(listener.getMessage().toString());
        Assert.assertTrue(listener.getMessage().equals(new Message("this is message", 1000l, 3)));
    }
    @Test
    public void ReceiveEvent() throws Exception{
        EventBus eventBus = new EventBus();
        EventListener listener = new EventListener();
        eventBus.register(listener);
        eventBus.post(new TestEvent(200));
        Assert.assertTrue(listener.getLastMessage()==200);
    }
}

class TestEvent{
    private final Object message;

    public TestEvent(Object message){
        this.message = message;
    }

    public Object getMessage(){
        return message;
    }
}

class Message{
    private String msg;
    private long id;
    private int block;

    public Message(String msg, long id, int block){
        this.msg = msg;
        this.id = id;
        this.block= block;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getBlock() {
        return block;
    }
    public void setBlock(int block) {
        this.block = block;
    }

    @Override
    public boolean equals(Object obj) {
        Message message = (Message)(obj);
        return message.getId() == this.getId() && message.getMsg().equals(this.getMsg()) && message.getBlock()==this.getBlock();
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
