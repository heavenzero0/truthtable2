package com.teamjuno.truthtable2.services;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

public class ToPostFixImplTest {

    ToPostFixImpl toPostFix;

    @Before
    public void setUp() throws Exception {
        toPostFix = new ToPostFixImpl();
    }

    @Test
    public void tokens() {
        Queue<String> expected = new LinkedList<>();
        expected.add("p");
        expected.add("p");
        expected.add("q");
        expected.add("v");
        expected.add("~");
        expected.add("p");
        expected.add("q");
        expected.add("~");
        expected.add("v");
        expected.add("^");
        expected.add("v");



        assertEquals(expected, toPostFix.tokens("pv~(pvq)^(pv~q)"));
        assertEquals(11, toPostFix.tokens("pv~(pvq)^(pv~q)").size());
    }
}