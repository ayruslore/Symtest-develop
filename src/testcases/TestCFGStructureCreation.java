package testcases;

import java.util.LinkedHashSet;
import java.util.Set;

import mycfg.CFEdge;
import mycfg.CFG;
import mycfg.CFGBasicBlockNode;
import mycfg.CFGDecisionNode;

import cfg.ICFEdge;
import cfg.ICFG;
import cfg.ICFGBasicBlockNode;
import cfg.ICFGDecisionNode;
import cfg.ICFGNode;

/**
 * Test cases for different types of basic constructs of CFG
 *
 * @author pavithra
 *
 */

public class TestCFGStructureCreation {

	private ICFG mCFG;
	private ICFGBasicBlockNode mStart, mEnd;

	public static void main(String[] args){
		try{
			TestCFGStructureCreation testing = new TestCFGStructureCreation();
			testing.testCFG();
			testing.testAddBasicBlockNode();
			testing.testIfElseCFG();
			testing.testLoopCFG();
			testing.testAddDecisionNode();
		}
		catch(Exception e){
			System.out.println("Exception occurred");
		}
	}

	/*
	 * Creates an CFG with only start,end nodes
	 */
	//@Test
	public final void testCFG() throws Exception{
		this.mStart = new CFGBasicBlockNode(this.mCFG);
		this.mEnd = new CFGBasicBlockNode(this.mCFG);
		this.mCFG = new CFG(this.mStart, this.mEnd);
	}

	/*
	 * Tests a CFG with a single decision block
	 */

	//@Test
	public final void testAddDecisionNode() throws Exception{
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		ICFGDecisionNode node = new CFGDecisionNode(this.mCFG, null);
		this.mCFG.addDecisionNode(node);
	}

	/*
	 * Tests a CFG with a single basic block
	 */
	//@Test
	public final void testAddBasicBlockNode() throws Exception{
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		this.mCFG = new CFG(this.mStart, this.mEnd);
		ICFGBasicBlockNode node = new CFGBasicBlockNode(null);
		this.mCFG.addBasicBlockNode(node);
	}

	/*
	 * Simulates ifelse_construct.odg in /resources
	 */

	//@Test
	public final void testIfElseCFG() throws Exception{

		CFGBasicBlockNode A = new CFGBasicBlockNode(null);
		CFGBasicBlockNode F = new CFGBasicBlockNode(null);
		Set<ICFGNode> nodeSet = new LinkedHashSet<ICFGNode>();
		this.mCFG = new CFG(A, F);
		ICFGBasicBlockNode B = new CFGBasicBlockNode(mCFG);
		this.mCFG.addBasicBlockNode(B);

		ICFGDecisionNode C = new CFGDecisionNode(this.mCFG, null);
		this.mCFG.addDecisionNode(C);

		ICFGBasicBlockNode D = new CFGBasicBlockNode(mCFG);
		this.mCFG.addBasicBlockNode(D);

		ICFGBasicBlockNode E = new CFGBasicBlockNode(mCFG);
		this.mCFG.addBasicBlockNode(E);

		CFEdge e1 = new CFEdge(this.mCFG, A, B);
		CFEdge e2 = new CFEdge(this.mCFG, B, C);
		CFEdge e3 = new CFEdge(this.mCFG, C, D);
		CFEdge e4 = new CFEdge(this.mCFG, C, E);
		CFEdge e5 = new CFEdge(this.mCFG, D, F);
		CFEdge e6 = new CFEdge(this.mCFG, E, F);

		this.mCFG.addEdge(e1);
		this.mCFG.addEdge(e2);
		this.mCFG.addEdge(e3);
		this.mCFG.addEdge(e4);
		this.mCFG.addEdge(e5);
		this.mCFG.addEdge(e6);

		nodeSet.add(A);
		nodeSet.add(B);
		nodeSet.add(C);
		nodeSet.add(D);
		nodeSet.add(E);
		nodeSet.add(F);

		ICFEdge edge = new CFEdge(this.mCFG, D, C);
	}

	/*
	 * Simulates Loop.odg in /resources
	 */

	//@Test
	public final void testLoopCFG() throws Exception{

		CFGBasicBlockNode A = new CFGBasicBlockNode(null);
		CFGBasicBlockNode E = new CFGBasicBlockNode(null);
		Set<ICFGNode> nodeSet = new LinkedHashSet<ICFGNode>();
		this.mCFG = new CFG(A, E);
		ICFGBasicBlockNode B = new CFGBasicBlockNode(mCFG);
		this.mCFG.addBasicBlockNode(B);

		ICFGDecisionNode C = new CFGDecisionNode(this.mCFG, null);
		this.mCFG.addDecisionNode(C);

		ICFGBasicBlockNode D = new CFGBasicBlockNode(mCFG);
		this.mCFG.addBasicBlockNode(D);

		CFEdge e1 = new CFEdge(this.mCFG, A, B);
		CFEdge e2 = new CFEdge(this.mCFG, B, C);
		CFEdge e3 = new CFEdge(this.mCFG, C, D);
		CFEdge e4 = new CFEdge(this.mCFG, C, B);
		CFEdge e5 = new CFEdge(this.mCFG, D, E);

		this.mCFG.addEdge(e1);
		this.mCFG.addEdge(e2);
		this.mCFG.addEdge(e3);
		this.mCFG.addEdge(e4);
		this.mCFG.addEdge(e5);

		nodeSet.add(A);
		nodeSet.add(B);
		nodeSet.add(C);
		nodeSet.add(D);
		nodeSet.add(E);

		ICFEdge edge = new CFEdge(this.mCFG, D, C);
	}

}
