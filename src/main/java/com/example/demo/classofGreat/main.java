package com.example.demo.classofGreat;

import com.example.demo.classofGreat.Generate.Task;

public class main {
    public static String operate(String str,int StoreID) throws Exception{
        Task task1 = new Task(str, StoreID);
        Thread t1 = new Thread(task1);
        t1.setDaemon(true);
        t1.start();
        try {
            t1.join(4000); // 在主线程中等待t1执行2秒

        } catch (InterruptedException e) {
            System.out.println("t1 interrupted when waiting join");
            e.printStackTrace();
            return "t1 interrupted when waiting join";
        }
        if(t1.isAlive())
            return task1.getReturnValue();
        else
            return "Time is over";
    }
}




