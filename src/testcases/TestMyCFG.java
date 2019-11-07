package testcases;

import java.util.List;
import java.util.Set;

//import junit.framework.Assert;
import mycfg.CFEdge;
import mycfg.CFG;
import mycfg.CFGBasicBlockNode;
import mycfg.CFGDecisionNode;

//import org.junit.Test;

import cfg.ICFEdge;
import cfg.ICFG;
import cfg.ICFGBasicBlockNode;
import cfg.ICFGDecisionNode;
import cfg.ICFGNode;
import expression.Variable;

/**
 * Consists of test cases that would ensure the structure establishment of a
 * graph
 *
 * @author pavithra
 *
 */
public class TestMyCFG {

	private ICFG mCFG;
	private ICFGBasicBlockNode mStart, mEnd;

	public static void main(String[] args) {
		try{
			TestMyCFG testing = new TestMyCFG();
			testing.testCFG();
			testing.testAddDecisionNode();
			testing.testAddBasicBlockNode();
			testing.testDeleteBasicBlockNode();
			testing.testGetNumberOfBasicBlockNodes();
			testing.testGetNumberOfCFGDecisionNodes();
			testing.testHasNode();
			testing.testGetNumberOfNodes();
			testing.testAddEdge();
			testing.testDeleteEdge();
			testing.testGetNumberOfEdges();
			testing.testGetBasicBlockNodeSet();
			testing.testGetNodeSet();
			testing.testGetEdgeSet();
			testing.testAddVariable();
		}
		catch(Exception e){
			System.out.println("Exception occurred");
		}
	}

	/*
	 * Creates an CFG with only start,end nodes
	 */
	//@Test
	public final void testCFG() throws Exception {
		this.mStart = new CFGBasicBlockNode(this.mCFG);
		this.mEnd = new CFGBasicBlockNode(this.mCFG);
		this.mCFG = new CFG(this.mStart, this.mEnd);

		if(this.mStart.getCFG() == this.mCFG) { System.out.println(1); }
		else { System.out.println(0); }
		if(this.mEnd.getCFG() == this.mCFG) { System.out.println(1); }
		else { System.out.println(0); }
		if(this.mStart == this.mCFG.getStartNode()) { System.out.println(1); }
		else { System.out.println(0); }
		if(this.mEnd == this.mCFG.getStopNode()) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/*
	 * Tests a CFG with a single decision block
	 */

	//@Test
	public final void testAddDecisionNode() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);

		ICFGDecisionNode node = new CFGDecisionNode(this.mCFG, null);
		this.mCFG.addDecisionNode(node);
		if(this.mCFG.hasDecisionNode(node) == true) { System.out.println(1); }
		else { System.out.println(0); }
		if(this.mCFG.hasNode(node) == true) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/*
	 * Tests a CFG with a single basic block
	 */
	//@Test
	public final void testAddBasicBlockNode() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		ICFGBasicBlockNode node = new CFGBasicBlockNode(null);
		this.mCFG.addBasicBlockNode(node);
		if(this.mCFG.hasBasicBlockNode(node) == true) { System.out.println(1); }
		else { System.out.println(0); }
		if(this.mCFG.hasNode(node) == true) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 * Checks the process of deleting a Basic Block
	 */
	//@Test
	public final void testDeleteBasicBlockNode() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		ICFGBasicBlockNode node = new CFGBasicBlockNode(null);
		this.mCFG.addBasicBlockNode(node);
		if(this.mCFG.hasBasicBlockNode(node) == true) { System.out.println(1); }
		else { System.out.println(0); }
		if(this.mCFG.hasNode(node) == true) { System.out.println(1); }
		else { System.out.println(0); }
		this.mCFG.deleteBasicBlockNode(node);
		if(this.mCFG.hasNode(node) == false) { System.out.println(1); }
		else { System.out.println(0); }
		// Added this test case to check if it is deleted.
		if(this.mCFG.getNumberOfBasicBlockNodes() == 2) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 * Checks the addition of Basic blocks by confirming the total number.
	 */
	//@Test
	public final void testGetNumberOfBasicBlockNodes() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		ICFGBasicBlockNode node = new CFGBasicBlockNode(null);
		this.mCFG.addBasicBlockNode(node);
		if(this.mCFG.getNumberOfBasicBlockNodes() == 3) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 * Checks the addition of Decision blocks by confirming the total number.
	 */

	//@Test
	public final void testGetNumberOfCFGDecisionNodes() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		ICFGDecisionNode node = new CFGDecisionNode(this.mCFG, null);
		this.mCFG.addDecisionNode(node);
		if(this.mCFG.getNumberOfDecisionNodes() == 1) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 * Checks the existence of a node.
	 */
	//@Test
	public final void testHasNode() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		ICFGDecisionNode dnode = new CFGDecisionNode(this.mCFG, null);
		this.mCFG.addDecisionNode(dnode);
		ICFGBasicBlockNode bbnode = new CFGBasicBlockNode(null);
		this.mCFG.addBasicBlockNode(bbnode);

		if(this.mCFG.hasNode(this.mStart) == true) { System.out.println(1); }
		else { System.out.println(0); }
		if(this.mCFG.hasNode(this.mEnd) == true) { System.out.println(1); }
		else { System.out.println(0); }
		if(this.mCFG.hasNode(dnode) == true) { System.out.println(1); }
		else { System.out.println(0); }
		if(this.mCFG.hasNode(bbnode) == true) { System.out.println(1); }
		else { System.out.println(0); }
		ICFGBasicBlockNode bbnode1 = new CFGBasicBlockNode(null);
		if(this.mCFG.hasNode(bbnode1) == false) { System.out.println(1); }
		else { System.out.println(0); }
		ICFGDecisionNode dnode1 = new CFGDecisionNode(null, null);
		if(this.mCFG.hasNode(dnode1) == false) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 * Checks the total number of nodes
	 */
	//@Test
	public final void testGetNumberOfNodes() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		ICFGDecisionNode dnode = new CFGDecisionNode(this.mCFG, null);
		this.mCFG.addDecisionNode(dnode);
		ICFGBasicBlockNode bbnode = new CFGBasicBlockNode(null);
		this.mCFG.addBasicBlockNode(bbnode);
		if(this.mCFG.getNumberOfNodes() == 4) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 * Checks the addition of an edge
	 */
	//@Test
	public final void testAddEdge() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		ICFEdge edge = new CFEdge(this.mCFG, this.mStart, this.mEnd);
		if(edge.getHead() == this.mEnd) { System.out.println(1); }
		else { System.out.println(0); }
		if(edge.getTail() == this.mStart) { System.out.println(1); }
		else { System.out.println(0); }
		this.mCFG.addEdge(edge);
		if(this.mStart.getOutgoingEdge() == edge) { System.out.println(1); }
		else { System.out.println(0); }
		List<ICFEdge> list = this.mEnd.getIncomingEdgeList();
		if(list.size() == 1) { System.out.println(1); }
		else { System.out.println(0); }
		if(list.get(0) == edge) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 * Checks deletion of an edge
	 */
	//@Test
	public final void testDeleteEdge() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		ICFEdge edge = new CFEdge(this.mCFG, this.mStart, this.mEnd);
		this.mCFG.addEdge(edge);
		this.mCFG.deleteEdge(edge);
		if(this.mCFG.hasEdge(edge) == false) { System.out.println(1); }
		else { System.out.println(0); }
		List<ICFEdge> list = this.mEnd.getIncomingEdgeList();
		if(list.size() == 0) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 * Tests the total number of edges in the graph
	 */
	//@Test
	public final void testGetNumberOfEdges() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		ICFEdge edge = new CFEdge(this.mCFG, this.mStart, this.mEnd);
		this.mCFG.addEdge(edge);
		if(this.mCFG.getNumberOfEdges() == 1) { System.out.println(1); }
		else { System.out.println(0); }
		this.mCFG.deleteEdge(edge);
		if(this.mCFG.getNumberOfEdges() == 0) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 * Validates the nodes present in the graph
	 */
	//@Test
	public final void testGetNodeSet() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		Set<ICFGNode> set = this.mCFG.getNodeSet();
		if(set.size() == 2) { System.out.println(1); }
		else { System.out.println(0); }
		if(set.contains(this.mStart) == true) { System.out.println(1); }
		else { System.out.println(0); }
		if(set.contains(this.mEnd) == true) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 * Validates the edges in the graph
	 */
	//@Test
	public final void testGetEdgeSet() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		Set<ICFEdge> set = this.mCFG.getEdgeSet();
		if(set.size() == 0) { System.out.println(1); }
		else { System.out.println(0); }
		ICFEdge edge = new CFEdge(this.mCFG, this.mStart, this.mEnd);
		this.mCFG.addEdge(edge);
		if(set.size() == 1) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 * Validates the decision blocks in the graph
	 */
	//@Test
	public final void testGetDecisionNodeSet() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		Set<ICFGDecisionNode> set = this.mCFG.getDecisionNodeSet();
		if(set.size() == 0) { System.out.println(1); }
		else { System.out.println(0); }
		if(set.contains(this.mStart) == false) { System.out.println(1); }
		else { System.out.println(0); }
		if(set.contains(this.mEnd) == false) { System.out.println(1); }
		else { System.out.println(0); }
		ICFGDecisionNode dnode = new CFGDecisionNode(this.mCFG, null);
		this.mCFG.addDecisionNode(dnode);
		set = this.mCFG.getDecisionNodeSet();
		if(set.size() == 1) { System.out.println(1); }
		else { System.out.println(0); }
		if(set.contains(dnode) == true) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 * Validates the basic blocks in the graph
	 */
	//@Test
	public final void testGetBasicBlockNodeSet() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		Set<ICFGBasicBlockNode> set = this.mCFG.getBasicBlockNodeSet();
		if(set.size() == 2) { System.out.println(1); }
		else { System.out.println(0); }
		if(set.contains(this.mStart) == true) { System.out.println(1); }
		else { System.out.println(0); }
		if(set.contains(this.mEnd) == true) { System.out.println(1); }
		else { System.out.println(0); }
	}

	/**
	 *
	 * Validates the variables in the graph
	 */
	//@Test
	public final void testAddVariable() throws Exception {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);

		Variable v = null;

		v = new Variable("var1", this.mCFG);
		this.mCFG.addVariable(v);
		if(this.mCFG.hasVariable(v) == true) { System.out.println(1); }
		else { System.out.println(0); }
	}
}
