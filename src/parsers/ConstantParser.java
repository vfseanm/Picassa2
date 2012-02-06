package parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Expression;
import model.RGBColor;
import model.expressions.Xnumber;

public class ConstantParser implements GenericParser {
    protected static final Pattern DOUBLE_REGEX = Pattern
            .compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\-?.[0-9]+)");

    public boolean isThisThing(Parsehandler handler) {
        // System.out.println("testing to see if it is a constant");
        handler.skipWhiteSpace();
        // System.out.println("checking:"+handler.substring());
        Matcher doubleMatcher = DOUBLE_REGEX.matcher(handler.substring());
        return doubleMatcher.lookingAt();
    }

    public Expression parseThis(Parsehandler handler) {
        // System.out.println("parsing constant");
        Matcher doubleMatcher = DOUBLE_REGEX.matcher(handler.getInput());
        doubleMatcher.find(handler.getPosition());
        String numberMatch = handler.getInput().substring(
                doubleMatcher.start(), doubleMatcher.end());
        handler.setPosition(doubleMatcher.end());
        // this represents the color gray of the given intensity
        double value = Double.parseDouble(numberMatch);
        RGBColor gray = new RGBColor(value);
        return new Xnumber(gray);
    }

    public ConstantParser() {
    };

}
