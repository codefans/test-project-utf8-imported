package com.codefans.basicjava.generics;

import java.io.Serializable;

/**
 * @author: caishengzhi
 * @date: 2018-05-31 17:15
 * 相关测试类
 *    GenericsConstructorTest.java
 *
 * 参考资料：
 * Java泛型详解
 * http://www.importnew.com/24029.html
 */
public class GenericsMessage<D> implements Serializable {

    /**
     * 结果。
     */
    private String result;

    /**
     * 编码(可表示错误码等)。
     */
    private int code;

    /**
     * 错误信息编码(可表示错误码等)。
     */
    private String message;

    /**
     * 数据。
     */
    private D data;


	public GenericsMessage() {
    }

	public GenericsMessage(String result, Integer code,String message) {
        super();
        this.result = result;
        this.code = code;
        this.message = message;
    }

	public GenericsMessage(String result, int code, D data) {
        super();
        this.result = result;
        this.code = code;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
