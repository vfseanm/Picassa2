package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;

public class Xwrap implements Expression {
    public static final double COLOR_MIN = -1;
    public static final double COLOR_MAX = 1;
    private Expression myOperand1;

    public Xwrap(String cmd, ArrayList<Expression> ops) {
        if  (ops.size()!=1)
            throw new ParserException("Incorrect number of operands");
        myOperand1 = ops.get(0);
        
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
        return operate(myOperand1.evaluate(imageX, imageY, myMap, myCurrentTime));
    }

    private static RGBColor operate(RGBColor left) {
        double red = left.getRed();
        double green = left.getGreen();
        double blue = left.getBlue();

        return new RGBColor(wrap(red), wrap(green), wrap(blue));
    }

    private static double wrap(double value) {
        double range = COLOR_MAX - COLOR_MIN;
        value %= range;
        if (value > COLOR_MAX)
            return value - range;
        else if (value < COLOR_MIN)
            return value + range;
        else
            return value;
    }

    private Xwrap() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new Xwrap());
    }

    public boolean isThisKindofThing(String cmd) {
        return (cmd.contentEquals("wrap"));

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        return new Xwrap(cmd, ops);

    }
}
