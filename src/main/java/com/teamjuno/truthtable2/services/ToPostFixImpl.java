package com.teamjuno.truthtable2.services;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@Service
public class ToPostFixImpl implements ToPostFix {


    @Override
    public Queue<String> tokens(String Expression) {
        Stack<String> tokens = new Stack<>();
        Queue<String> outputs = new LinkedList<>();


        for (char ch : Expression.toCharArray()) {
            String token = String.valueOf(ch);
            if (token.equals("v") || token.equals("^")) {
                if (!tokens.isEmpty() && tokens.peek().equals("~")) {
                    outputs.add(tokens.peek());
                    tokens.pop();
                }
                tokens.push(token);
            } else if (token.equals("~")) {
                tokens.push(token);
            } else if (token.equals("(")) {
                tokens.push(token);
            } else if (token.equals(")")) {
                while (!tokens.peek().equals("(")) {
                    outputs.add(tokens.peek());
                    tokens.pop();
                }
                tokens.pop();
            } else {
                outputs.add(token);
            }
        }

        while (!tokens.isEmpty()) {
            outputs.add(tokens.peek());
            tokens.pop();
        }

        return outputs;
    }
}
