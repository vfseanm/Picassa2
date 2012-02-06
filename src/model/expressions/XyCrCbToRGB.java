package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;

public class XyCrCbToRGB implements Expression {
    private Expression myOperand1;

    public XyCrCbToRGB(String cmd, ArrayList<Expression> ops) {
        if  (ops.size()!=1)
            throw new ParserException("Incorrect number of operands");
        myOperand1 = ops.get(0);
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
        return operate(myOperand1.evaluate(imageX, imageY, myMap, myCurrentTime));
    }

    public static RGBColor operate(RGBColor c) {
        return new RGBColor(c.getRed() + c.getBlue() * 1.4022, c.getRed()
                + c.getGreen() * -0.3456 + c.getBlue() * -0.7145, c.getRed()
                + c.getGreen() * 1.7710);
    }

    private XyCrCbToRGB() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new XyCrCbToRGB());
    }

    public boolean isThisKindofThing(String cmd) {
        return (cmd.contentEquals("yCrCbToRGB"));

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        return new XyCrCbToRGB(cmd, ops);

    }
}
