package parsers;

import java.util.ArrayList;
import model.Expression;
import model.ParserException;

/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser is
 * used http://en.wikipedia.org/wiki/Recursive_descent_parser
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser {

    private ArrayList<GenericParser> kindsOfthings;

    public Parser() {
        initialize();

    }

    public Expression makeExpression(String input) {
        Parsehandler handler = new Parsehandler(input);
        Expression result = parseExpression(handler);
        handler.skipWhiteSpace();
        if (handler.notAtEndOfString()) {
            throw new ParserException(
                    "Unexpected characters at end of the string: "
                            + ParserException.Type.EXTRA_CHARACTERS);
        }
        return result;
    }

    public Expression parseExpression(Parsehandler handler) {
        for (GenericParser expKind : kindsOfthings) {
            if (expKind.isThisThing(handler)) {
                return expKind.parseThis(handler);
            }
        }
        throw new ParserException("Unexpected expression type ");
    }

    private void initialize() {
        kindsOfthings = new ArrayList<GenericParser>();

        kindsOfthings.add(new ConstantParser());
        kindsOfthings.add(new ParenParser());
        kindsOfthings.add(new XYParser());
        kindsOfthings.add(new LetVariableParser());
    }
}
