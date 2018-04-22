package com.teamjuno.truthtable2.services;

import com.teamjuno.truthtable2.model.Expression;

import java.util.ArrayList;
import java.util.Queue;

public interface PostFixCalculator {
    ArrayList<Expression> calculate(Queue<String> tokens);
}
