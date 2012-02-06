package parsers;

import model.Expression;
import java.util.ArrayList;

import model.ExpressionFactory;
import model.ParserException;
import model.expressions.Xabs;
import model.expressions.Xatan;
import model.expressions.Xaverage;
import model.expressions.Xceil;
import model.expressions.Xclamp;
import model.expressions.Xcolor;
import model.expressions.Xcos;
import model.expressions.Xdiv;
import model.expressions.Xexp;
import model.expressions.Xfloor;
import model.expressions.Xif;
import model.expressions.Xlet;
import model.expressions.XletVariable;
import model.expressions.Xlog;
import model.expressions.Xmax;
import model.expressions.Xmin;
import model.expressions.Xminus;
import model.expressions.Xmod;
import model.expressions.Xmul;
import model.expressions.Xneg;
import model.expressions.XperlinBW;
import model.expressions.XperlinColor;
import model.expressions.Xplus;
import model.expressions.Xproduct;
import model.expressions.Xrandom;
import model.expressions.XrgbToYCrCb;
import model.expressions.Xsin;
import model.expressions.Xsum;
import model.expressions.Xtan;
import model.expressions.Xwrap;
import model.expressions.XyCrCbToRGB;

public class ExpressionFinder {
    private ArrayList<ExpressionFactory> kindsOfExpressions;

    public ExpressionFinder() {
        initialize();
    }

    public Expression makeExpression(String commandName,
            ArrayList<Expression> operators) {
        for (ExpressionFactory expKind : kindsOfExpressions) {
            if (expKind.isThisKindofThing(commandName))
                return expKind.createExp(commandName, operators);
        }
        throw new ParserException("Unrecognized Expression");
    }

    private void initialize() {

        kindsOfExpressions = new ArrayList<ExpressionFactory>();

        kindsOfExpressions.add(Xplus.getFactory());
        kindsOfExpressions.add(Xdiv.getFactory());
        kindsOfExpressions.add(Xexp.getFactory());
        kindsOfExpressions.add(Xminus.getFactory());
        kindsOfExpressions.add(Xmod.getFactory());
        kindsOfExpressions.add(Xmul.getFactory());
        kindsOfExpressions.add(Xneg.getFactory());
        kindsOfExpressions.add(Xplus.getFactory());
        kindsOfExpressions.add(Xcolor.getFactory());
        kindsOfExpressions.add(Xrandom.getFactory());
        kindsOfExpressions.add(Xfloor.getFactory());
        kindsOfExpressions.add(Xceil.getFactory());
        kindsOfExpressions.add(Xabs.getFactory());
        kindsOfExpressions.add(Xclamp.getFactory());
        kindsOfExpressions.add(Xwrap.getFactory());
        kindsOfExpressions.add(Xsin.getFactory());
        kindsOfExpressions.add(Xcos.getFactory());
        kindsOfExpressions.add(Xtan.getFactory());
        kindsOfExpressions.add(Xatan.getFactory());
        kindsOfExpressions.add(Xlog.getFactory());
        kindsOfExpressions.add(XrgbToYCrCb.getFactory());
        kindsOfExpressions.add(XyCrCbToRGB.getFactory());
        kindsOfExpressions.add(XperlinColor.getFactory());
        kindsOfExpressions.add(XperlinBW.getFactory());
        kindsOfExpressions.add(Xlet.getFactory());
        kindsOfExpressions.add(Xsum.getFactory());
        kindsOfExpressions.add(Xproduct.getFactory());
        kindsOfExpressions.add(Xaverage.getFactory());
        kindsOfExpressions.add(Xmin.getFactory());
        kindsOfExpressions.add(Xmax.getFactory());
        kindsOfExpressions.add(Xif.getFactory());
        
    }

}
