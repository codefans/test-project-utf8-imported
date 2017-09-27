package com.codefans.interview.classload;

/**
 * @author: caishengzhi
 * @date: 2017-07-19 18:00
 **/
public class ClassLoadTest {
    private int i = 1;
    public ClassLoadTest() {
        displayResult();
    }
    public void displayResult() {
        System.out.println("ClassLoadTest.i:" + i);
    }
}
class SubClassMain extends ClassLoadTest {
    private int i = 2;
    public SubClassMain() {
        i = 222;
    }
    @Override
    public void displayResult() {
        System.out.println("SubClassMain.i=" + i);
    }
    public static void main(String[] args) {
        new SubClassMain();
    }
}
