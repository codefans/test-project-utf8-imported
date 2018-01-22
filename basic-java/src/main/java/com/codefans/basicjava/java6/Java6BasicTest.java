package com.codefans.basicjava.java6;

import org.junit.Test;

/**
 * @author caishengzhi
 * @date 2018/1/22 11:39
 */
public class Java6BasicTest {


    @Test
    public void paramTest() {
        StringBuffer sb = new StringBuffer();
        sb.append("hello ");
        this.changeValue(sb);
        //输出[hello ]
        //原因在于java采用的是值传递，
        // 对于引用变量，传递的是引用的值，也就是说让实参和形参同时指向了同一个对象，
        // 因此让形参重新指向另一个对象对实参并没有任何影响。
        System.out.println(sb.toString());
    }

    public void changeValue(StringBuffer sb) {
        sb = new StringBuffer();
        sb.append("world");
        System.out.println("changeValue-->" + sb.toString());
    }
}
