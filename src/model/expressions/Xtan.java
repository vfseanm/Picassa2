package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;

public class Xtan implements Expression {
    private Expression myOperand1;

    public Xtan(String cmd, ArrayList<Expression> ops) {
        if  (ops.size()!=1)
            throw new ParserException("Incorrect number of operands");
        myOperand1 = ops.get(0);
        
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
        return operate(myOperand1.evaluate(imageX, imageY, myMap, myCurrentTime));
    }

    private static RGBColor operate(RGBColor left) {
        return new RGBColor(Math.tan(left.getRed()), Math.tan(left.getGreen()),
                Math.tan(left.getBlue()));
    }

    private Xtan() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new Xtan());
    }

    public boolean isThisKindofThing(String cmd) {
        return (cmd.contentEquals("tan"));

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        return new Xtan(cmd, ops);

    }
}
