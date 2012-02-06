package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;

public class Xneg implements Expression {
    private Expression myOperand1;

    public Xneg(String cmd, ArrayList<Expression> ops) {
        if  (ops.size()!=1)
            throw new ParserException("Incorrect number of operands");
        myOperand1 = ops.get(0);
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
        return operate(myOperand1.evaluate(imageX, imageY, myMap, myCurrentTime));
    }

    private static RGBColor operate(RGBColor left) {
        return new RGBColor(-left.getRed(), -left.getGreen(), -left.getBlue());
    }

    private Xneg() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new Xneg());
    }

    public boolean isThisKindofThing(String cmd) {
        if (cmd.contentEquals("!") || cmd.contentEquals("neg") )
            return true;
        return false;

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        return new Xneg(cmd, ops);

    }
}
