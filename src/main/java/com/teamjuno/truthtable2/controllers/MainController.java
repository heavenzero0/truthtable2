package com.teamjuno.truthtable2.controllers;


import com.teamjuno.truthtable2.Utils.StringSplitter;
import com.teamjuno.truthtable2.model.Expression;
import com.teamjuno.truthtable2.services.PostFixCalculator;
import com.teamjuno.truthtable2.services.ToPostFix;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
public class MainController {


    private final ToPostFix toPostFix;
    private final PostFixCalculator postFixCalculator;

    public MainController(ToPostFix toPostFix, PostFixCalculator postFixCalculator) {
        this.toPostFix = toPostFix;
        this.postFixCalculator = postFixCalculator;
    }

    public void show() {
        String ex = "p^(pvq)v(p^q)";
        String ex2 = "(pvq)^((p^q)^(pvq))";
        String ex3 = "(pvq)^(pvq)";
        String ex4 = "(pvq)^((~p)^(~q))";

        Queue<String> val = toPostFix.tokens( ex);
        System.out.println();
        val.forEach(System.out::print);
        System.out.println();


        System.out.println("Problem: " +ex);
        for(Expression expression : postFixCalculator.calculate(val)) {
            System.out.print(expression.getVariable() + " ");
            for(String exp: expression.getValue()) {
                System.out.print(exp);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Result: " + StringSplitter.splitter("p"));

    }


}
