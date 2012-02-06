package parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Expression;
import model.expressions.XletVariable;
import model.expressions.Xnumber;

public class LetVariableParser implements GenericParser {
    protected static final Pattern VARIABLE_NAME = Pattern.compile("([a-z]+)");

    public boolean isThisThing(Parsehandler handler) {
        // System.out.println("testing to see if it is a let variable");
        handler.skipWhiteSpace();
        // System.out.println("checking:"+handler.substring());
        Matcher doubleMatcher = VARIABLE_NAME.matcher(handler.substring());
        return doubleMatcher.lookingAt();
    }

    public Expression parseThis(Parsehandler handler) {
        //System.out.println("parsing let variable");
        Matcher doubleMatcher = VARIABLE_NAME.matcher(handler.getInput());
        doubleMatcher.find(handler.getPosition());
        String numberMatch = handler.getInput().substring(
                doubleMatcher.start(), doubleMatcher.end());
        handler.setPosition(doubleMatcher.end());

        String variable = numberMatch;
        return new XletVariable(variable);
    }

    public LetVariableParser() {
    };

}
