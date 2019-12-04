package mygraph;

import java.util.LinkedHashSet;
import java.util.Set;

import graph.IEdge;
import graph.IGraph;
import graph.INode;

public class GraphtoStringVisitor {
	private IGraph mGraph;
	private String mOutputString;
	private Set<INode> mVisited = new LinkedHashSet<INode>();
	public GraphtoStringVisitor(IGraph graph) {
		this.mGraph = graph;
	}
	
	public void visit() {
		this.mOutputString = "Graph = " + this.mGraph.getId();
		this.mOutputString = this.mOutputString + "\n";
		this.visit(this.mGraph.getRoot());
	}
	
	private void visit(INode node) {
		if(this.mVisited.contains(node)) {
			return;
		}
		this.mVisited.add(node);
		this.mOutputString = this.mOutputString + "\nnode = " + node.getId() + "\n";
		for(IEdge e : node.getOutgoingEdgeList()) {
				this.visit(e);
		}
		for(IEdge e : node.getOutgoingEdgeList()) {
			this.visit(e.getHead());
		}
	}
	
	private void visit(IEdge e) {
		this.mOutputString = this.mOutputString + "\n";
		this.mOutputString = this.mOutputString + "edge " + e.getId() + " = (";
		this.mOutputString = this.mOutputString + e.getTail().getId() + ", ";
		this.mOutputString = this.mOutputString + e.getHead().getId() + ")";
	}
	
	public String getOutputString() {
		return this.mOutputString;
	}
}
