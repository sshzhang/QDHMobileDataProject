package com.lifeStudy.algorith;

public abstract class BaseBoundedBuffer<V> {

    private final V[] buf;
    private int tail;
    private int head;
    private int count;

    protected BaseBoundedBuffer(int capacity) {
        //还可以通过直接new Object[]数组，等到需要具体化时候才 强制转换单个元素
        buf = (V[]) new Object[capacity];//泛型擦除，其实就是(Object[])转换
    }


    protected synchronized final void doPut(V v) {
        buf[tail] = v;
        if (++tail == buf.length) {
            tail = 0;
        }
        count++;
    }

    protected synchronized final V doTake() {
        V rs = buf[head];
        buf[head] = null;
        if (++head == buf.length) {
            head = 0;
        }
        count--;
        return rs;
    }

    public synchronized final boolean isFull() {
        return count == buf.length;
    }

    public synchronized final boolean isEmpty() {
        return count == 0;
    }
}
