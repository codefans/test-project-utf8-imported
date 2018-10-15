package com.codefans.basicjava.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ShengzhiCai
 * @date: 2018-10-05 09:49
 * T - 限定为某一类型
 * ? - 限定为某一范围
 */
public class TQuestionObject {

    public static void main(String[] args) {
        TQuestionObject tQuestionObject = new TQuestionObject();
        tQuestionObject.genericsTest();
    }

    public void genericsTest() {

        this.testT();

        this.testQuestionMark();

    }

    public void testT() {
        Response<Integer> intResponse = new Response<Integer>();
        intResponse.setObj(new Integer(1));
        Response<Float> floatResponse = new Response<Float>();
        floatResponse.setObj(new Float(2));
        Response<Double> doubleResponse = new Response<Double>();
        doubleResponse.setObj(new Double(3));
    }

    public void testQuestionMark() {

        List<SubClass> subClassList = new ArrayList<SubClass>();
        List<Sub2Class> sub2ClassList = new ArrayList<Sub2Class>();

        QuestionMark questionMark = new QuestionMark();
        questionMark.set(subClassList);
        questionMark.set(sub2ClassList);

    }


    /**
     * T的作用：限制为某一个类型
     * @param <T>
     */
    class Response<T> {

        private T data;

        public T getObj() {
            return data;
        }

        public void setObj(T data) {
            this.data = data;
        }

    }

    class SuperClass {
        private int age;
    }

    class SubClass extends SuperClass {
        private String name;
    }

    class Sub2Class extends SuperClass {
        private String name;
    }

    class QuestionMark {

        /**
         * 问号将类型限定在某一范围
         * @param list
         */
        public void set(List<?> list) {

        }

    }

}
