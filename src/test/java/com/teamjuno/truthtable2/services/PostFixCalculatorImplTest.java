package com.teamjuno.truthtable2.services;

import com.teamjuno.truthtable2.model.Expression;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.*;

public class PostFixCalculatorImplTest {

    PostFixCalculatorImpl postFixCalculator;

    @Before
    public void setUp() throws Exception {
        postFixCalculator = new PostFixCalculatorImpl();
    }

    @Test
    public void calculate() {
        Queue<String> tokens = new LinkedList<>();
        tokens.add("p");
        tokens.add("q");
        tokens.add("v");
        tokens.add("p");
        tokens.add("q");
        tokens.add("^");
        tokens.add("p");
        tokens.add("q");
        tokens.add("v");
        tokens.add("^");
        tokens.add("^");

        List<String> expect = new ArrayList<>();
        expect.add("T");
        expect.add("F");
        expect.add("F");
        expect.add("T");

        Expression expression = postFixCalculator.calculate(tokens).get(7);
        List<String> gg =expression.getValue();


        assertEquals("pvq^p^q^pvq", expression.getVariable());
        assertArrayEquals(expect.toArray(),gg.toArray());
    }
}