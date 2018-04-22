package com.teamjuno.truthtable2.services;


import com.teamjuno.truthtable2.model.Expression;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostFixCalculatorImpl implements PostFixCalculator {

    private final Expression q = new Expression();
    private final Expression p = new Expression();

    @Override
    public ArrayList<Expression> calculate(Queue<String> tokens) {
        Stack<String> variable = new Stack<>();
        ArrayList<Expression> outputs = new ArrayList<>();
        ArrayList<Expression> finalOutputs = new ArrayList<>();
        setValue();
        outputs.add(p);
        outputs.add(q);
        finalOutputs.add(p);
        outputs.add(q);

        for (String token : tokens) {
            if (token.equals("v")) {
                String secondValue = variable.peek();
                variable.pop();
                String firstValue = variable.peek();
                variable.pop();
                Expression expression = or(secondValue, firstValue, outputs);
                variable.push(expression.getVariable());
            } else if (token.equals("^")) {
                String secondValue = variable.peek();
                variable.pop();
                String firstValue = variable.peek();
                variable.pop();
                Expression expression = and(secondValue, firstValue, outputs);
                variable.push(expression.getVariable());
            } else {
                variable.push(token);
            }
        }
        return outputs;
    }

    private void setValue() {
        q.setVariable("q");
        q.setValue("T");
        q.setValue("F");
        q.setValue("T");
        q.setValue("F");
        p.setVariable("p");
        p.setValue("T");
        p.setValue("T");
        p.setValue("F");
        p.setValue("F");
    }

    private Expression or(String secondValue, String firstValue, ArrayList<Expression> outputs) {
        Expression expressionOne = new Expression();
        Expression expressionTwo = new Expression();
        Expression newExpression = new Expression();
        newExpression.setVariable(firstValue + "v" + secondValue);

        for (Expression expression : outputs) {
            if (expression.getVariable().equals(firstValue)) {
                expressionOne = expression;
            }
            if (expression.getVariable().equals(secondValue)) {
                expressionTwo = expression;
            }
        }

        for (int i = 0; i < expressionOne.getValue().size(); i++) {
            if (expressionOne.getValue().get(i).equals("F") && expressionTwo.getValue().get(i).equals("F")) {
                newExpression.setValue("F");
            } else {
                newExpression.setValue("T");
            }
        }
        outputs.add(newExpression);
        return newExpression;
    }

    private Expression and(String secondValue, String firstValue, ArrayList<Expression> outputs) {
        Expression expressionOne = new Expression();
        Expression expressionTwo = new Expression();
        Expression newExpression = new Expression();
        newExpression.setVariable(firstValue + "^" + secondValue);

        for (Expression expression : outputs) {
            if (expression.getVariable().equals(firstValue)) {
                expressionOne = expression;
            }
            if (expression.getVariable().equals(secondValue)) {
                expressionTwo = expression;
            }
        }

        for (int i = 0; i < expressionOne.getValue().size(); i++) {
            if (expressionOne.getValue().get(i).equals(expressionTwo.getValue().get(i))) {
                newExpression.setValue("T");
            } else {
                newExpression.setValue("F");
            }
        }
        outputs.add(newExpression);

        return newExpression;
    }

}
