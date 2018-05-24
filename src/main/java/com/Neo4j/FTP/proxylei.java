package com.Neo4j.FTP;

import com.sun.deploy.net.proxy.ProxyHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class proxylei implements InvocationHandler {//java动态代理
    private Object concreteClass;

    public proxylei(Object concreteClass) {
        this.concreteClass = concreteClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy:" + proxy.getClass().getName());
        System.out.println("method:" + method.getName());
        System.out.println("args:" + args[0].getClass().getName());
        System.out.println("Before invoke method...");
        Object object=method.invoke(concreteClass, args);//普通的Java反射代码,通过反射执行某个类的某方法
        //System.out.println(((ConcreteClass)concreteClass).targetMethod(10)+(Integer)args[0]);
        System.out.println("After invoke method...");
        return object;
    }//java动态代理

    public static void main(String... args) {

        ConcreteClass concreteClass = new ConcreteClass();
        proxylei proxyleis = new proxylei(concreteClass);
        TargetInterface tf = (TargetInterface) Proxy.newProxyInstance(concreteClass.getClass().getClassLoader(), concreteClass.getClass().getInterfaces(), proxyleis);
        int i = tf.targetMethodB(12);
        System.out.println(i);
        System.out.println();

    }


}
