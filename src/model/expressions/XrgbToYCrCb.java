package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;

public class XrgbToYCrCb implements Expression {
    private Expression myOperand1;

    public XrgbToYCrCb(String cmd, ArrayList<Expression> ops) {
        if  (ops.size()!=1)
            throw new ParserException("Incorrect number of operands");
        myOperand1 = ops.get(0);
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
        return operate(myOperand1.evaluate(imageX, imageY, myMap, myCurrentTime));
    }

    public static RGBColor operate(RGBColor c) {
        return new RGBColor(c.getRed() * 0.2989 + c.getGreen() * 0.5866
                + c.getBlue() * 0.1145, c.getRed() * -0.1687 + c.getGreen()
                * -0.3312 + c.getBlue() * 0.5, c.getRed() * 0.5000
                + c.getGreen() * -0.4183 + c.getBlue() * -0.0816);
    }

    private XrgbToYCrCb() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new XrgbToYCrCb());
    }

    public boolean isThisKindofThing(String cmd) {
        return (cmd.contentEquals("rgbToYCrCb"));

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        return new XrgbToYCrCb(cmd, ops);

    }
}
