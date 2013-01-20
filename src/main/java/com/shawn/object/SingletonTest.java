package com.shawn.object;

/*
 * singleton -->class is instantiated exactly once,only one instance ,no more no less
 */
public class SingletonTest {

}

class Elvis{

    //singleton : make member a final field
    public static final Elvis INSTANCE = new Elvis();
    private Elvis(){}
    //singleton: public member is a static factory method
    public static Elvis getInstance(){
        return INSTANCE;
    }

    //each time a serialized instance is deserializaed ,a new instance will be creted
    private Object readResolve(){
        return INSTANCE;
    }

    //singleton:serialization mechinery for free, best way to implement a singleton
    public enum ElvisInstance{
        INSTACE;
    }

}
