package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.RGBColor;
import model.Expression;

public class Xaverage implements Expression {
    private ArrayList<Expression> operands;

    public Xaverage(String cmd, ArrayList<Expression> ops) {
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
        float total_red = 0;
        float total_green = 0;
        float total_blue = 0;
        float length = expressions.size();
        for(RGBColor color: expressions)
        {
            total_red +=color.getRed();
            total_green +=color.getGreen();
            total_blue +=color.getBlue();
        }
        return new RGBColor((total_red/length), (total_green/length), (total_blue/length));
    }

    private Xaverage() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new Xaverage());
    }

    public boolean isThisKindofThing(String cmd) {
        return cmd.contentEquals("average");

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        return new Xaverage(cmd, ops);

    }
}
