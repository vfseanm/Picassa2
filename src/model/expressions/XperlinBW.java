package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;
import model.util.PerlinNoise;

public class XperlinBW implements Expression {
    private Expression myOperand1;
    private Expression myOperand2;

    public XperlinBW(String cmd, ArrayList<Expression> ops) {
        if  (ops.size()!=2)
            throw new ParserException("Incorrect number of operands");
        myOperand1 = ops.get(0);
        myOperand2 = ops.get(1);
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
        return operate(myOperand1.evaluate(imageX, imageY, myMap, myCurrentTime),
                myOperand2.evaluate(imageX, imageY, myMap, myCurrentTime));
    }

    public static RGBColor operate(RGBColor left, RGBColor right) {
        return PerlinNoise.greyNoise(left, right);

    }

    private XperlinBW() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new XperlinBW());
    }

    public boolean isThisKindofThing(String cmd) {
        return (cmd.contentEquals("perlinBW"));

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        return new XperlinBW(cmd, ops);

    }
}
