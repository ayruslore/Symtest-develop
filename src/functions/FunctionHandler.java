package functions;

import cfg.ICFEdge;
import cfg.ICFG;
import cfg.ICFGNode;
import expression.FunctionCallExpression;
import expression.IExpression;
import expression.IIdentifier;
import graph.IEdge;
import graph.IGraph;
import graph.IPath;
import mycfg.CFGBasicBlockNode;
import see.SEE;
import set.*;
import tester.CFGToGraphConvertor;
import tester.FindCFPathAlgorithm;

import java.util.*;

public class FunctionHandler {

    private SEE mSEE;
    private FunctionCallExpression call_exp;
    private Function mfunction;

    private ICFG mCFG;
    private CFGToGraphConvertor mConvertor;
    private IGraph mGraph;
    private Set<ICFEdge> mTargets;
    private ICFGNode mTarget;


    public FunctionHandler(SEE see, FunctionCallExpression exp) throws Exception {
        this.mSEE = see;
        this.call_exp = exp;

        this.mfunction = this.mSEE.getFunctionObject(this.call_exp);

        this.mCFG = this.mfunction.getFunction_cfg();
        this.mTargets = this.mfunction.getTargets_edges();
        this.mConvertor = new CFGToGraphConvertor(this.mCFG);
        this.mTarget = this.mCFG.getStopNode();
    }

    public IExpression makeFunctionCall() throws Exception {
        SET trace = this.mSEE.getSET();
        trace.updateLeafNodeSet();
        ICFGNode call_node = ( (SETNode) trace.getLeafNodes().iterator().next()).getCFGNode();

        if(this.mfunction.paths == null) {
            calculate_paths();
        }
        //Now choose a path for SE. As of now just pick the first path.
        ArrayList<ICFEdge> selected_path = (ArrayList<ICFEdge>) this.mfunction.paths.iterator().next();

        addStartNodetoSET();
        this.mSEE.expandSET(selected_path);
        //now we have reached the stop node of the function
        return addDummyCallNode(call_node);
    }

    private void calculate_paths() throws Exception {
        this.mfunction.paths = new LinkedHashSet<ArrayList<ICFEdge>>();
        if(this.mTargets.size() == 0) {
            //If no target edge is given. Just pick one random path for now.
            this.mTargets.add(this.mCFG.getStartNode().getOutgoingEdgeList().get(0));
        }
        Set<IEdge> targets = convertTargetEdgesToGraphEdges(this.mTargets);
        Set<IEdge> currentTargets = new LinkedHashSet<IEdge>(targets);
        while(currentTargets.size() != 0) {
            FindCFPathAlgorithm algorithm = new FindCFPathAlgorithm(this.mGraph, currentTargets, this.mConvertor.getGraphNode(this.mTarget));
            IPath path = algorithm.findCFPath(this.mConvertor.getGraphNode(this.mCFG.getStartNode()), currentTargets);
            ArrayList<ICFEdge> cfPath = convertPathEdgesToCFGEdges(path);
            for( ICFEdge edge : cfPath ){
                currentTargets.remove(this.mConvertor.getGraphEdge(edge));
            }
            this.mfunction.paths.add(cfPath);
        }
    }

    private void addStartNodetoSET() throws Exception {
        SET trace = this.mSEE.getSET();
        trace.updateLeafNodeSet();
        if(trace.getLeafNodes().size() != 1) {
            throw new Exception("SET has " + trace.getLeafNodes().size() + " number of leaf nodes.\n Supposed to have only one leaf.");
        }
        SETNode leaf = (SETNode) trace.getLeafNodes().iterator().next();
        SETEdge newSETEdge = new SETEdge(trace, leaf, null);
        ((SETBasicBlockNode) leaf).setOutgoingEdge(newSETEdge);
        SETBasicBlockNode newSETStartNode = new SETBasicBlockNode(trace, this.mCFG.getStartNode());
        trace.addBasicBlockNode(newSETStartNode);
        newSETEdge.setHead(newSETStartNode);
        newSETStartNode.setIncomingEdge(newSETEdge);
        trace.addEdge(newSETEdge);

        addArgumentInfo(newSETStartNode);
    }

    private void addArgumentInfo(SETBasicBlockNode node) throws Exception {
        Set<IIdentifier> formal_args = this.mfunction.getFormalParameters();
        Iterator iterator = formal_args.iterator();
        Set<IExpression> actual_args = this.call_exp.getArgs();
        for(IExpression exp : actual_args ) {
            IIdentifier arg = (IIdentifier) iterator.next();
            SETExpressionVisitor visitor = new SETExpressionVisitor(this.mSEE, exp.getType());
            visitor.visit(exp);
            IExpression value = visitor.getValue();
            node.setValue(arg, value);
        }
    }

    private IExpression addDummyCallNode(ICFGNode node) throws Exception {
        SET trace = this.mSEE.getSET();
        trace.updateLeafNodeSet();
        if(trace.getLeafNodes().size() != 1) {
            throw new Exception("SET has " + trace.getLeafNodes().size() + " number of leaf nodes.\n Supposed to have only one leaf.");
        }
        SETNode leaf = (SETNode) trace.getLeafNodes().iterator().next();
        SETEdge newSETEdge = new SETEdge(trace, leaf, null);
        ((SETBasicBlockNode) leaf).setOutgoingEdge(newSETEdge);
        SETBasicBlockNode newSETStartNode = new SETBasicBlockNode(trace, (CFGBasicBlockNode) node);
        trace.addBasicBlockNode(newSETStartNode);
        newSETEdge.setHead(newSETStartNode);
        newSETStartNode.setIncomingEdge(newSETEdge);
        trace.addEdge(newSETEdge);
        return getReturnValue((SETBasicBlockNode) leaf);
    }

    private IExpression getReturnValue(SETBasicBlockNode stop_node) {
        Map<IIdentifier, IExpression> values = ((SETBasicBlockNode) stop_node.getPredecessorNode()).getValues();
        for(Map.Entry<IIdentifier, IExpression> entry : values.entrySet()) {
            if(entry.getKey().getName() == "return_value") {
                return entry.getValue();
            }
        }
        return null;
    }

    private Set<IEdge> convertTargetEdgesToGraphEdges(Set<ICFEdge> targetEdges) throws Exception {
        this.mGraph = this.mConvertor.getGraph();
        Set<IEdge> targets = new LinkedHashSet<IEdge>();
        for (ICFEdge edge : targetEdges) {
            targets.add(this.mConvertor.getGraphEdge(edge));
        }
        return targets;
    }

    private ArrayList<ICFEdge> convertPathEdgesToCFGEdges(IPath path) {
        ArrayList<IEdge> list = path.getPath();
        ArrayList<ICFEdge> cfPath = new ArrayList<ICFEdge>();
        // Convert the graph edges to cfg edges.
        for (IEdge e : list) {
            cfPath.add(this.mConvertor.getCFEdge(e));
        }
        return cfPath;
    }
}
