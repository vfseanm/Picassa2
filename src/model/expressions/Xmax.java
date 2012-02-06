package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.RGBColor;
import model.Expression;

public class Xmax implements Expression {
    private ArrayList<Expression> operands;

    public Xmax(String cmd, ArrayList<Expression> ops) {
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
        double red = -2;
        double green = -2;
        double blue = -2;
        for(RGBColor color: expressions)
        {
            if(color.getRed()>red)
                red = color.getRed();
            if(color.getGreen()>green)
                green = color.getGreen();
            if(color.getBlue()>blue)
                blue = color.getBlue();
        }
        return new RGBColor(red, green, blue);
    }

    private Xmax() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new Xmax());
    }

    public boolean isThisKindofThing(String cmd) {
        return cmd.contentEquals("max");

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        return new Xmax(cmd, ops);

    }
}
