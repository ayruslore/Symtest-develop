package expression;

import program.IProgram;
import visitors.IExprVisitor;

import java.util.Set;

public class FunctionCallExpression extends Expression {

    private String func_name;
    private Set<IExpression> args;

    public FunctionCallExpression(IProgram program, String name, Set<IExpression> c_args) throws Exception {
        super(program, Type.FUNC);
        this.func_name = name;
        this.args = c_args;
    }

    public String getFunc_name(){
        return this.func_name;
    }

    public Set<IExpression> getArgs(){
        return this.args;
    }

    public int get_Number_of_Args(){
        return this.args.size();
    }

    @Override
    public String toString() {
        String s = this.func_name + "( ";
        for (IExpression exp : this.args) {
            s += exp.toString() + ", ";
        }
        s = s.substring(0, s.length() - 2);
        return s + " )";
    }

    @Override
    public void accept(IExprVisitor<?> visitor){
        try{
            for(IExpression exp : this.args){
                visitor.visit(exp);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
