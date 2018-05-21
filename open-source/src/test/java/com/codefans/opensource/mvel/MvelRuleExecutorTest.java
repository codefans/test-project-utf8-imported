package com.codefans.opensource.mvel;

import org.junit.Test;
import org.mvel2.compiler.CompiledExpression;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: caishengzhi
 * @date: 2018-05-17 18:28
 */
public class MvelRuleExecutorTest {

    @Test
    public void mvelTest() {

        String ruleStr = "input.age > 5";
        Map<String, Object> mvelMap = new HashMap<String, Object>();

        /**
         * MvelBean不能是内部类,否则不能通过input.age访问。TODO 原因是什么？
         */
        MvelBean mvelBean = new MvelBean();
        mvelBean.setName("zhangsan");
        mvelBean.setPassword("12345");
        mvelBean.setAge(6);

        mvelMap.put("input", mvelBean);

        MvelRuleExecutor mvelRuleExecutor = new MvelRuleExecutor();
        CompiledExpression compiledExpression = mvelRuleExecutor.compile(ruleStr);

        boolean flag = mvelRuleExecutor.simpleEvaluate(mvelMap, compiledExpression);
        System.out.println("execute result:" + flag);

    }


}
