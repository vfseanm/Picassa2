package parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Expression;
import model.expressions.XX;
import model.expressions.XY;
import model.expressions.Xtime;

public class XYParser implements GenericParser {
    protected static final Pattern VARIABLE_REGEX = Pattern.compile("[xyt]");

    public boolean isThisThing(Parsehandler handler) {
        handler.skipWhiteSpace();
        Matcher expMatcher = VARIABLE_REGEX.matcher(handler.substring());
        return expMatcher.lookingAt();
    }

    public Expression parseThis(Parsehandler handler) {

        Matcher expMatcher = VARIABLE_REGEX.matcher(handler.getInput());

        expMatcher.find(handler.getPosition());
        String variableName = handler.getInput().substring(expMatcher.start(),
                expMatcher.end());
        // System.out.println(variableName);
        handler.incPosition();

        if (variableName.contentEquals("x"))
            return new XX();
        if (variableName.contentEquals("t")) 
            return new Xtime();
        		
        return new XY();
    }

    public XYParser() {
    };

}
