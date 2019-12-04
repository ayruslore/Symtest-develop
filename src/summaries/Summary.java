package summaries;

import cfg.ICFG;
import expression.IExpression;
import expression.IIdentifier;
import program.IProgram;
import utilities.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Summary implements IProgram {

    private ICFG mCFG;
    private Map<IIdentifier, Set<Pair<IExpression, IExpression>>> summary;
    // variable -> set of (path predicate, value)

    public Summary(ICFG cfg) throws Exception {
        if(cfg != null) {
            this.mCFG = cfg;
        }
        else {
            throw new Exception("CFG cannot be null to create the summary.");
        }
        this.summary = new HashMap<IIdentifier, Set<Pair<IExpression, IExpression>>>();
    }

    @Override
    public IIdentifier addVariable(IIdentifier var) {
        return null;
    }

    @Override
    public Set<IIdentifier> getVariables() {
        return null;
    }

    @Override
    public boolean hasVariable(IIdentifier var) {
        return false;
    }
}
