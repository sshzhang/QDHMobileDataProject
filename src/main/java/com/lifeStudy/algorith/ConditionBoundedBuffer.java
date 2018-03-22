package com.lifeStudy.algorith;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer<T> {
    protected final Lock lock = new ReentrantLock();

    //条件谓词 不满
    protected final Condition notFully = lock.newCondition();

    //条件谓词 不空
    protected final Condition notEmpty = lock.newCondition();

    protected final T[] items;
    private int tail, head, count;

    protected ConditionBoundedBuffer(int capacity) {
        items = (T[]) new Object[capacity];
    }


    public void put(T x) throws InterruptedException {

        lock.lock();
        try {
            while (count == items.length)
                notFully.await();
            items[tail] = x;
            if (++tail == items.length) tail = 0;
            ++count;
            notEmpty.signal();//唤醒不为空的信号
        } finally {
            lock.unlock();
        }
    }


    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            T t = items[head];
            if (++head == items.length) head = 0;
            --count;
            notFully.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String... args) {

        ConditionBoundedBuffer<String> objectConditionBoundedBuffer = new ConditionBoundedBuffer<String>(2);

        new Thread(new Processors(objectConditionBoundedBuffer,"pro")).start();
        new Thread(new Processors(objectConditionBoundedBuffer,"con")).start();

    }


    private static class Processors implements Runnable {
        private ConditionBoundedBuffer bf;
        private  String flages;

        private Processors(ConditionBoundedBuffer bf,String flages) {
            this.bf = bf;
            this.flages = flages;
        }
        public void run() {
         if("pro".equals(flages)){
             while (true) {
                 try {
                     bf.put("12");
                     System.out.println("put");
                 } catch (InterruptedException e) {
                     Thread.currentThread().interrupt();
                 }
             }
         }else{
             while (true) {
                 try {
                     bf.take();
                     System.out.println("take");
                 } catch (InterruptedException e) {
                     Thread.currentThread().interrupt();
                 }
             }
         }
        }
    }
}
