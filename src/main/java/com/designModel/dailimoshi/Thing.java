package com.designModel.dailimoshi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Thing implements Serializable {

    private final  ArrayList<String> params = new ArrayList<String>();

    public void setValue(String value) {
        params.add(value);
    }

    public List<String> getValues() {
        return params;
    }
//    //默认浅拷贝
//    @Override
//    public Thing clone(){
//
//        Thing thing = null;
//        try {
//            thing = (Thing) super.clone();
//            //深拷贝
////            thing.params = (ArrayList<String>) this.params.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        return thing;
//    }


    public static void main(String... args) throws IOException, ClassNotFoundException {

//        Thing thing = new Thing();
//        thing.setValue("张三");
//        Thing clonet = thing.clone();
//        clonet.setValue("李四");
//        System.out.println(thing.params);
        Thing thing = new Thing();
        thing.setValue("张三");
        Thing depthclone = (Thing) depthClone(thing);
        depthclone.setValue("李四");
        System.out.println(depthclone.params);
        System.out.println(thing.params);
    }

    //通过自定义二进制流来操作对象，从而实现深拷贝
    public static Object depthClone(Object srcobj) throws IOException, ClassNotFoundException {
        Object cloneobj = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(srcobj);

        ByteArrayInputStream in = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(in);
        cloneobj=oi.readObject();
        return cloneobj;
    }

}
