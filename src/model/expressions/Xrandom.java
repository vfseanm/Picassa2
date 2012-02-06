package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import model.ExpressionFactory;
import model.RGBColor;
import model.Expression;

public class Xrandom implements Expression {
    private ArrayList<Expression> randomColors;

    public Xrandom(String cmd, ArrayList<Expression> ops) {
        randomColors = new ArrayList<Expression>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            double r1 = (random.nextDouble() * 2) - 1;
            RGBColor c = new RGBColor(r1);
            Expression toAdd = new Xnumber(c);
            randomColors.add(toAdd);
        }

    }

    public RGBColor evaluate(float imageX, float imageY,
            HashMap<String, Expression> myMap, double myCurrentTime) {
        Xcolor color = new Xcolor("color", randomColors);
        return color.evaluate(imageX, imageY, myMap, myCurrentTime);
    }

    private Xrandom() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new Xrandom());
    }

    public boolean isThisKindofThing(String cmd) {
        return (cmd.contentEquals("random"));

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        return new Xrandom(cmd, ops);
    }
}
