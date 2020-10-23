package summaries;

import expression.FunctionCallExpression;
import expression.IExpression;
import functions.Function;
import see.SEE;

public class SummaryHandler {

    SEE mSEE;
    FunctionCallExpression call_exp;
    Function function_obj;

    public SummaryHandler(SEE see, FunctionCallExpression exp) throws Exception {
        this.mSEE = see;
        this.call_exp = exp;

        this.function_obj = this.mSEE.getFunctionObject(exp);
    }

    public IExpression makeFunctionCall() throws Exception {
        return null;
    }
}
