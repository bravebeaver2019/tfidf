package com.test3.tfidf.populator;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import static junit.framework.TestCase.assertTrue;

@Slf4j
public class FilePopulatorELTest {

    @Test
    public void testEL() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("new Double((T(java.lang.Math).random() + 1) * 5000).intValue()");
        // todo: depending on how critical this random is, maybe we should make better tests
        for (int i=0; i<10_000; i++) {
            int result = Integer.parseInt(expression.getValue(String.class));
            assertTrue("random within boundaries (5_000-10_000)", result<10_000 && result>5_000);
        }
    }
}
