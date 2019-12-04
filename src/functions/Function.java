package functions;

import cfg.ICFEdge;
import cfg.ICFG;
import expression.IIdentifier;
import expression.Type;

import java.util.ArrayList;
import java.util.Set;

public class Function {
    private final String func_name;
    private Set<IIdentifier> parameters;
    private ICFG func_cfg;
    private Set<ICFEdge> targets_edges;
    private String return_type;

    public Set<ArrayList<ICFEdge>> paths = null;

    public Function(String name, ICFG mcfg, Set<IIdentifier> args, Set<ICFEdge> edges, String type) throws Exception {
        if(name.length() == 0) {
            Exception e = new Exception("Exception : Function Name is Empty");
            throw e;
        }
        else {
            this.func_name = name;
        }
        if(mcfg == null){
            Exception e = new Exception("Exception Occurred : Function " + name + " cfg cannot be null");
            throw e;
        }
        else {
            this.func_cfg = mcfg;
        }
        if(args == null){
            Exception e = new Exception("Exception Occurred : Function " + name + " arguments cannot be null");
            throw e;
        }
        else {
            this.parameters = args;
        }
        this.targets_edges = edges;
        if(Type.hasType(type))
            this.return_type = type;
        else
            throw new Exception("Type " + type + " is not present.");
    }

    public String getFunction_name(){
        return this.func_name;
    }

    public String getReturn_type() { return this.return_type; }

    public ICFG getFunction_cfg(){
        return this.func_cfg;
    }

    public Set<IIdentifier> getFormalParameters(){
        return this.parameters;
    }

    public Set<ICFEdge> getTargets_edges() { return this.targets_edges; }

}
