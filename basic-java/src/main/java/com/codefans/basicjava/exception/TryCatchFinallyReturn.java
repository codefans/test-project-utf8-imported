package com.codefans.basicjava.exception;

import org.junit.Test;

/**
 * @author: caishengzhi
 * @date: 2018-05-23 16:19
 */
public class TryCatchFinallyReturn {

    public static void main(String[] args) {
        TryCatchFinallyReturn tcfr = new TryCatchFinallyReturn();
        tcfr.process();
    }

    public void process() {

        String result = "";

//        result = this.tryCatchFinallyReturn();
//        System.out.println("result:" + result);

//        result = this.tryReturnCatchFinallyReturn();
//        System.out.println("result:" + result);


//        result = this.tryCatchReturnFinallyReturn();
//        System.out.println("result:" + result);

//        result = this.returnInTryCatchReturnInFinally();
//        System.out.println("result:" + result);

//        result = this.tryReturnInCatchReturnInFinally();
//        System.out.println("result:" + result);

        result = this.returnInTryReturnInCatchReturnInFinally();
        System.out.println("result:" + result);
    }

    /**
     * 情况1：try{} catch(){}finally{} return;
     * 按正常顺序执行。
     */
    public String tryCatchFinallyReturn() {

        try {
            System.out.println("try body...");
        } catch (Exception e) {
            System.out.println("catch block ...");
            e.printStackTrace();
        } finally {

            System.out.println("finally block ...");

        }

        return this.lastReturnBlock();
    }

    /**
     * 情况2：try{ return; }catch(){} finally{} return;
     程序执行try块中return之前（包括return语句中的表达式运算）代码；
     再执行finally块，最后执行try中return;
     finally块后面的return语句不再执行。
     * @return
     *
     * 输出：
    try body...
    try return block....
    finally block ...
     **********************************特别注意******************************
     */
    public String tryReturnCatchFinallyReturn() {

        try {
            System.out.println("try body...");

            return this.tryReturnBlock();

        } catch (Exception e) {
            System.out.println("catch block ...");
        } finally {

            System.out.println("finally block ...");

        }

        return this.lastReturnBlock();
    }

    /**
     *
     情况3：try{ } catch(){return;} finally{} return;
     程序先执行try，如果遇到异常执行catch块，
     有异常：
     则执行catch中return之前（包括return语句中的表达式运算）代码，再执行finally语句中全部代码，
     最后执行catch块中return. finally块后面的return语句不再执行。
     无异常：
     执行完try再finally再执行最后的return语句.
     */
    public String tryCatchReturnFinallyReturn() {

        try {
            System.out.println("try block...");

//            如果这里抛异常，最后的return语句会报错
//            throw new RuntimeException("try block throw runtime exception...");

        } catch (Exception e) {
            System.out.println("catch block...");
            return this.catchReturnBlock();
        } finally {
            System.out.println("finally block .....");
        }

        return this.lastReturnBlock();
    }


    /**
     情况4：try{ return; }catch(){} finally{return;}
     程序执行try块中return之前（包括return语句中的表达式运算）代码；
     再执行finally块，因为finally块中有return所以提前退出。
     */
    public String returnInTryCatchReturnInFinally() {

        try {
            System.out.println("try block...");
            return this.tryReturnBlock();
        } catch (Exception e) {
            System.out.println("catch block...");
        } finally {

            return this.finallyReturnBlock();

        }

//        finally块中的return直接返回了，这里再写return会报错
//        return this.lastReturnBlock();
    }

    /**
     情况5：try{} catch(){return;}finally{return;}
     程序执行catch块中return之前（包括return语句中的表达式运算）代码；
     再执行finally块，因为finally块中有return所以提前退出。

     输出内容为：
     try block...
     catch block...
     catch return block....
     finally block...
     finally return block....
     result:hello finally return

     *********************************特别注意**********************************
     */
    public String tryReturnInCatchReturnInFinally() {

        System.out.println("method of tryReturnInCatchReturnInFinally");

        try {
            System.out.println("try block...");

            throw new RuntimeException("runtime exception...");

        } catch (Exception e) {
            System.out.println("catch block...");
            return this.catchReturnBlock();
        } finally {
            System.out.println("finally block...");
            return this.finallyReturnBlock();
        }

//        catch块、finally块中的return直接返回了，这里再写return会报错
//        return this.lastReturnBlock();

    }


    /**
     情况6：try{ return;}catch(){return;} finally{return;}
     程序执行try块中return之前（包括return语句中的表达式运算）代码；
     有异常：执行catch块中return之前（包括return语句中的表达式运算）代码；
     则再执行finally块，因为finally块中有return所以提前退出。
     无异常：则再执行finally块，因为finally块中有return所以提前退出。

     无异常时输出结果：
     try block.....
     try return block....
     finally block
     finally return block....
     result:hello finally return

     */
    public String returnInTryReturnInCatchReturnInFinally() {

        System.out.println("method of returnInTryReturnInCatchReturnInFinally");

        try {
            System.out.println("try block.....");

//            如何这里加throw则会报异常
//            throw new RuntimeException("runtime exception ");

            return this.tryReturnBlock();
            
        } catch (Exception e) {
            System.out.println("catch block");
            return this.catchReturnBlock();
        } finally {
            System.out.println("finally block");
            return this.finallyReturnBlock();
        }


    }



    public String tryReturnBlock() {
        System.out.println("try return block....");
        return "hello" + " try return";
    }

    public String catchReturnBlock() {
        System.out.println("catch return block....");
        return "hello" + " catch return";
    }

    public String finallyReturnBlock() {
        System.out.println("finally return block....");
        return "hello" + " finally return";
    }

    public String lastReturnBlock() {
        System.out.println("last return block....");
        return "hello" + " last return";
    }




}
