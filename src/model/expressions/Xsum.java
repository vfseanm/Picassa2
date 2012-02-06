package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.RGBColor;
import model.Expression;

public class Xsum implements Expression {
    private ArrayList<Expression> operands;

    public Xsum(String cmd, ArrayList<Expression> ops) {
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
        double red = 0;
        double green = 0;
        double blue = 0;
        for(RGBColor color: expressions)
        {
            red +=color.getRed();
            green +=color.getGreen();
            blue +=color.getBlue();
        }
        return new RGBColor(red, green, blue);
    }

    private Xsum() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new Xsum());
    }

    public boolean isThisKindofThing(String cmd) {
        return cmd.contentEquals("sum");

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        return new Xsum(cmd, ops);

    }
}
