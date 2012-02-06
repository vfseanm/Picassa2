package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;

public class Xminus implements Expression {
    private Expression myOperand1;
    private Expression myOperand2;

    public Xminus(String cmd, ArrayList<Expression> ops) {
        if  (ops.size()!=2)
            throw new ParserException("Incorrect number of operands");
        myOperand1 = ops.get(0);
        myOperand2 = ops.get(1);
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
        return operate(myOperand1.evaluate(imageX, imageY, myMap, myCurrentTime),
                myOperand2.evaluate(imageX, imageY, myMap, myCurrentTime));
    }

    private static RGBColor operate(RGBColor left, RGBColor right) {
        return new RGBColor(left.getRed() - right.getRed(), left.getGreen()
                - right.getGreen(), left.getBlue() - right.getBlue());
    }

    private Xminus() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new Xminus());
    }

    public boolean isThisKindofThing(String cmd) {
        if (cmd.contentEquals("-") || cmd.contentEquals("minus") )
            return true;
        return false;

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        
        return new Xminus(cmd, ops);

    }
}
