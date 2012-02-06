package parsers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Expression;
import model.ParserException;


public class ParenParser implements GenericParser {

    private static final Pattern EXPRESSION_BEGIN_REGEX =
            Pattern.compile("\\(([A-Za-z+-/%*^!]+)");
    
    public ParenParser() {};

    public boolean isThisThing(Parsehandler handler) {
        handler.skipWhiteSpace();
        Matcher expMatcher = EXPRESSION_BEGIN_REGEX
                .matcher(handler.substring());
        return expMatcher.lookingAt();
    }

    public Expression parseThis(Parsehandler handler) {
        Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(handler.getInput());
        expMatcher.find(handler.getPosition());
        String commandName = expMatcher.group(1);
        handler.setPosition(expMatcher.end());

        Expression currentExpression;
        handler.skipWhiteSpace();

        ArrayList<Expression> operators = new ArrayList<Expression>();

        while (handler.currentCharacter() != ')') {
            Parser p = new Parser();
            currentExpression = p.parseExpression(handler);
            handler.skipWhiteSpace();
            operators.add(currentExpression);
            
            if(!handler.notAtEndOfString())
                throw new ParserException("Missing ')'");
        }
        handler.incPosition();

        Expression expression = new ExpressionFinder().makeExpression(
                commandName, operators);
        return expression;

    }

}
