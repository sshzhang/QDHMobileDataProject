package com.lifeStudy.algorith;


public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {//条件队列

    protected BoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws InterruptedException {

        while (isFull()) {
            System.out.println(Thread.currentThread().getId() + " put竞争失败，挂起");
            this.wait();
            System.out.println(Thread.currentThread().getId() + " put被唤醒，二次激活");
        }
        boolean empty = isEmpty();
        doPut(v);
        if(empty)//由空变为非空 才唤醒挂起的线程
        this.notifyAll();//唤醒所有在this对象上挂起的线程
    }

    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            System.out.println(Thread.currentThread().getId() + " get竞争失败，挂起");
            this.wait();//线程挂起，放开锁synchronized中this对象。
            System.out.println(Thread.currentThread().getId() + " get被唤醒，二次激活");
        }
        boolean full = isFull();//由满变成非满 才唤醒挂起的线程
        V v = doTake();
        if(full)
        this.notifyAll();//唤醒所有在this对象上挂起的线程
        return v;
    }

    public static void main(String... args) throws InterruptedException {
        BoundedBuffer<Integer> integerBoundedBuffer = new BoundedBuffer<Integer>(12);
//        new Thread(new Consummer(integerBoundedBuffer)).start();
//        TimeUnit.SECONDS.sleep(10);
        new Thread(new Producer(integerBoundedBuffer)).start();
        //通过下面可以看到  调用wait挂起线程时会释放锁.
//        new Thread(new Producer(integerBoundedBuffer)).start();
    }

    private static class Consummer implements Runnable {

        private final BoundedBuffer bf;

        public Consummer(BoundedBuffer bf) {
            this.bf = bf;
        }

        public void run() {
            try {
                while (true) {
                    bf.take();
                    System.out.println("get");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    private static class Producer implements Runnable {
        private final BoundedBuffer bs;

        protected Producer(BoundedBuffer bs) {
            this.bs = bs;
        }

        public void run() {
            try {
                while (true) {
                    bs.put(123);
                    System.out.println("put");
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}

