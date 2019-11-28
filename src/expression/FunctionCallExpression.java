package expression;

import program.IProgram;
import visitors.IExprVisitor;

import java.util.Set;

public class FunctionCall extends Expression {

    private String func_name;
    private Set<IExpression> args;

    public FunctionCall(IProgram program, String name, Set<IExpression> c_args) throws Exception {
        super(program, Type.FUNC);

        this.func_name = name;
        this.args = c_args;
    }

    public String getFunctionname(){
        return this.func_name;
    }

    public Set<IExpression> getArguements(){
        return this.args;
    }

    @Override
    public String toString() {
        return "function call expression " + this.func_name;
    }

    @Override
    public void accept(IExprVisitor<?> visitor) {

    }

}
