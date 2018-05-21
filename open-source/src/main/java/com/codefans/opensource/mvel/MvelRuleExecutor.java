package com.codefans.opensource.mvel;

import org.mvel2.MVEL;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExpressionCompiler;

import java.util.Map;

/**
 * @author: caishengzhi
 * @date: 2018-05-17 18:22
 */
public class MvelRuleExecutor {

    public CompiledExpression compile(String ruleStr) {
        ExpressionCompiler compiler = new ExpressionCompiler(ruleStr);
        CompiledExpression compiledExpression = compiler.compile();
        return compiledExpression;
    }

    public boolean simpleEvaluate(Map<String, Object> mvelMap, CompiledExpression expression){
        return MVEL.executeExpression(expression, mvelMap, Boolean.class);
    }






}
