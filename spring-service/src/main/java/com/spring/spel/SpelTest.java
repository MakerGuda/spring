package com.spring.spel;

import com.google.common.collect.Lists;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * spel表达式测试类
 */
public class SpelTest {

    private static final ExpressionParser _PARSER = new SpelExpressionParser();

    private static final EvaluationContext _CONTEXT = new StandardEvaluationContext();

    /**
     * Literal Expressions
     */
    public static void testLiteralExpression() {
        //字符串
        String value = _PARSER.parseExpression("'hello world'").getValue(String.class);
        System.out.println(value);
        //数字
        Integer value1 = _PARSER.parseExpression("1000").getValue(Integer.class);
        System.out.println(value1);
        //布尔值
        Boolean flag = _PARSER.parseExpression("true").getValue(Boolean.class);
        System.out.println(flag);
        //null
        Object aNull = _PARSER.parseExpression("null").getValue();
        System.out.println(aNull == null ? "这是空的" : "这不是空的");
    }

    /**
     * Inline Lists
     */
    public static void testInlineListExpression() {
        List<?> list = _PARSER.parseExpression("{1,2,3,4,5}").getValue(List.class);
        list.forEach(System.out::println);
    }

    /**
     * Inline Maps
     */
    public static void testInlineMapExpression() {
        Map<?, ?> value = _PARSER.parseExpression("{id:1, name:'spring'}").getValue(Map.class);
        System.out.println(value);
    }

    /**
     * Array Construction
     */
    public static void testArrayConstructionExpression() {
        Integer[] value = _PARSER.parseExpression("new int[5]").getValue(Integer[].class);
        Arrays.asList(value).forEach(System.out::print);
        System.out.println();
        Integer[] value1 = _PARSER.parseExpression("new int[]{1,2,3}").getValue(Integer[].class);
        Arrays.asList(value1).forEach(System.out::print);
    }

    /**
     * Methods
     */
    public static void testMethodExpression() {
        String value = _PARSER.parseExpression("'hello world'.substring(0,5)").getValue(String.class);
        System.out.println(value);
    }

    /**
     * Relational Operators
     */
    public static void testRelationalOperatorExpression() {
        //relational operators
        Boolean value = _PARSER.parseExpression("2 == 2").getValue(Boolean.class);
        System.out.println(value);
        //统一规则，任何数都大于null，任何数都不小于null
        Boolean value1 = _PARSER.parseExpression("1 < null").getValue(Boolean.class);
        System.out.println(value1);
        Boolean value2 = _PARSER.parseExpression("1 between {1, 5}").getValue(Boolean.class);
        System.out.println(value2);
        Boolean value3 = _PARSER.parseExpression("2 between {1, 5}").getValue(Boolean.class);
        System.out.println(value3);
        Boolean value4 = _PARSER.parseExpression("2 between {5, 10}").getValue(Boolean.class);
        System.out.println(value4);
    }

    /**
     * Logical Operators
     */
    public static void testLogicalOperatorExpression() {
        Boolean value = _PARSER.parseExpression("true and false").getValue(Boolean.class);
        System.out.println(value);
        Boolean value1 = _PARSER.parseExpression("true or false").getValue(Boolean.class);
        System.out.println(value1);
        Boolean value2 = _PARSER.parseExpression("!false").getValue(Boolean.class);
        System.out.println(value2);
    }

    /**
     * String Operators
     */
    public static void testStringExpression() {
        String value = _PARSER.parseExpression("'hello' +' '+ 'world'").getValue(String.class);
        System.out.println(value);
        String value1 = _PARSER.parseExpression("'abc' * 3").getValue(String.class);
        System.out.println(value1);
    }

    /**
     * Types
     */
    public static void testTypeExpression() {
        Class value = _PARSER.parseExpression("T(java.lang.Integer)").getValue(Class.class);
        System.out.println(value);
        Class value1 = _PARSER.parseExpression("T(java.util.Date)").getValue(Class.class);
        System.out.println(value1);
    }

    /**
     * 变量表达式 使用 #variableName的语法，通过context.setVariable()设置表达式里变量的值
     */
    public static void testVariablesExpression() {
        _CONTEXT.setVariable("num", 10);
        Boolean value = _PARSER.parseExpression("#num >= 10").getValue(_CONTEXT, Boolean.class);
        System.out.println(value);

        List<Integer> list = Lists.newArrayList(2, 3, 5, 7, 11, 13, 17);
        _CONTEXT.setVariable("list", list);
        //筛选出列表中大于10的元素
        List<?> list1 = _PARSER.parseExpression("#list.?[#this > 10]").getValue(_CONTEXT, List.class);
        list1.forEach(System.out::println);
    }

    /**
     * Expression template
     */
    public static void testTemplateExpression() {
        //自定义变量的前缀和后缀，默认为#{}
        ParserContext context = new TemplateParserContext("@", "@");
        String value = _PARSER.parseExpression("random number is @T(java.lang.Math).random()@", context).getValue(String.class);
        System.out.println(value);

        _CONTEXT.setVariable("code", "10086");
        String value1 = _PARSER.parseExpression("the message code is #{#code}", new TemplateParserContext()).getValue(_CONTEXT, String.class);
        System.out.println(value1);
    }

    public static void main(String[] args) {
//        testLiteralExpression();
//        testInlineListExpression();
//        testInlineMapExpression();
//        testArrayConstructionExpression();
//        testMethodExpression();
//        testRelationalOperatorExpression();
//        testLogicalOperatorExpression();
//        testStringExpression();
//        testTypeExpression();
//        testVariablesExpression();
        testTemplateExpression();
    }


}