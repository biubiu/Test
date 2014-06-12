package com.shawn.GC;

/**
 * User: Shawn cao
 * Date: 14-5-23
 * Time: PM4:05
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes ! I am still alive:");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalize method executed ");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable{

        SAVE_HOOK = new FinalizeEscapeGC();

        //save itself by late execution of finalize()
        SAVE_HOOK = null;
        System.gc();
        //wait for finalize() to execute ; the priority of finalize() is low
        Thread.sleep(500);

        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no, I am dead :( ");
        }


        //second time , unsuccessful.
        //cause finalize of a object can only be executed one time .
        //in the second time, the finalize() will not execute.
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no, I am dead :( ");
        }

    }
}
