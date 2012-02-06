package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.RGBColor;
import model.Expression;

public class Xproduct implements Expression {
    private ArrayList<Expression> operands;

    public Xproduct(String cmd, ArrayList<Expression> ops) {
        operands = ops;
    }

    public RGBColor evaluate(float imageX, float imageY,
            HashMap<String, Expression> myMap, double myCurrentTime) {
        ArrayList<RGBColor> expressions = new ArrayList<RGBColor>();
        for(Expression exp: operands)
            expressions.add(exp.evaluate(imageX, imageY, myMap, myCurrentTime));
        return operate(expressions);
    }

    private static RGBColor operate(ArrayList<RGBColor> expressions) {
        double red = 1;
        double green = 1;
        double blue = 1;
        for(RGBColor color: expressions)
        {
            red *=color.getRed();
            green *=color.getGreen();
            blue *=color.getBlue();
        }
        return new RGBColor(red, green, blue);
    }

    private Xproduct() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new Xproduct());
    }

    public boolean isThisKindofThing(String cmd) {
        return cmd.contentEquals("product");

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        return new Xproduct(cmd, ops);

    }
}
