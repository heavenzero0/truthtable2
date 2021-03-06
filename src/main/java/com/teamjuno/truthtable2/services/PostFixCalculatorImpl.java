package com.teamjuno.truthtable2.services;


import com.teamjuno.truthtable2.Utils.StringSplitter;
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
       // ArrayList<Expression> finalOutputs = new ArrayList<>();
        setValue();
        outputs.add(p);
        outputs.add(q);


        for (String token : tokens) {
            switch (token) {
                case "v": {
                    String secondValue = variable.peek();
                    variable.pop();
                    String firstValue = variable.peek();
                    variable.pop();
                    Expression expression = or(secondValue, firstValue, outputs);
                    variable.push(StringSplitter.splitter(expression.getVariable()));

                    break;
                }
                case "^": {
                    String secondValue = variable.peek();
                    variable.pop();
                    String firstValue = variable.peek();
                    variable.pop();
                    Expression expression = and(secondValue, firstValue, outputs);
                    variable.push(StringSplitter.splitter(expression.getVariable()));
                    break;
                }
                case "~": {
                    String value = variable.peek();
                    variable.pop();
                    Expression expression = not(value, outputs);
                    variable.push(StringSplitter.splitter(expression.getVariable()));

                    break;
                }
                default:
                    variable.push(token);
                    break;
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

        for (Expression expression : outputs) {
            String variable = StringSplitter.splitter(expression.getVariable());

            if (variable.equals(firstValue)) {
                expressionOne = expression;
            }
            if (variable.equals(secondValue)) {
                expressionTwo = expression;
            }
        }
        newExpression.setVariable("("+expressionOne.getVariable() + "v" + expressionTwo.getVariable()+")");

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

        for (Expression expression : outputs) {
            String variable = StringSplitter.splitter(expression.getVariable());
            if (variable.equals(firstValue)) {
                expressionOne = expression;
            }
            if (variable.equals(secondValue)) {
                expressionTwo = expression;
            }
        }

        newExpression.setVariable("("+expressionOne.getVariable() + "^" + expressionTwo.getVariable()+")");

        for (int i = 0; i < expressionOne.getValue().size(); i++) {
            if (expressionOne.getValue().get(i).equals("T")&&expressionTwo.getValue().get(i).equals("T")) {
                newExpression.setValue("T");
            } else {
                newExpression.setValue("F");
            }
        }
        outputs.add(newExpression);

        return newExpression;
    }

    private Expression not(String value, ArrayList<Expression> outputs) {

        Expression expression = new Expression();
        Expression newExpression = new Expression();

        for (Expression exp : outputs) {
            String variable = StringSplitter.splitter(exp.getVariable());
            if (variable.equals(value))
                expression = exp;
        }
        newExpression.setVariable("(~" + expression.getVariable() +")");
        for (String v : expression.getValue()) {
            if (v.equals("T")) {
                newExpression.setValue("F");
            } else {
                newExpression.setValue("T");
            }
        }
        outputs.add(newExpression);
        return newExpression;
    }
}
