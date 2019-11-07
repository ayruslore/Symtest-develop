package functions;

import cfg.ICFG;
import expression.Variable;

import java.util.Set;

public class Function {
    private final String func_name;
    private Set<Variable> parameters;
    private ICFG func_cfg;

    public Function(String name, ICFG mcfg, Set<Variable> args) throws Exception {
        this.func_name = name;
        this.func_cfg = mcfg;
        this.parameters = args;
    }

    public String getFunctionname(){
        return this.func_name;
    }

    public ICFG getFunctioncfg(){
        return this.func_cfg;
    }

    public Set<Variable> getParameters(){
        return this.parameters;
    }
}
