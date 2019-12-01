package see;

import java.util.*;

import expression.FunctionCallExpression;
import functions.Function;
import mycfg.CFGBasicBlockNode;
import mycfg.CFGDecisionNode;
import set.SET;
import set.SETBasicBlockNode;
import set.SETDecisionNode;
import set.SETEdge;
import set.SETExpressionVisitor;
import set.SETNode;
import statement.IStatement;
import cfg.ICFEdge;
import cfg.ICFG;
import cfg.ICFGBasicBlockNode;
import cfg.ICFGNode;
import expression.IExpression;
import expression.IIdentifier;
import expression.Type;

public class SEE {

	private SET mSET;
	private Set<Function> mfunctions;

	public SEE(ICFG cfg, Set<Function> functions) throws Exception {
		if (cfg != null) {
			this.mSET = new SET(cfg);
		} else {
			throw new Exception("Null CFG");
		}
		if (functions != null) {
			this.mfunctions = functions;
		} else {
			throw new Exception("Null function list");
		}
	}

	public Function getFunctionObject(FunctionCallExpression exp) throws Exception{
		for(Function func : this.mfunctions) {
			if(func.getFunction_name() == exp.getFunc_name()) {
				Set<IIdentifier> formal_args = func.getFormalParameters();
				Iterator iter = formal_args.iterator();
				if(formal_args.size() == exp.get_Number_of_Args()) {
					//Type Checking
					for(IExpression arg : exp.getArgs()) {
						IIdentifier var = (IIdentifier) iter.next();
						if(var.getType() != arg.getType()) {
							throw new Exception("Type Mismatch in the function call expression " + exp.toString());
						}
					}
					return func;
				}
				else {
					throw new Exception("Argument Number not matching.\n" +
							"Function " + exp.getFunc_name() + " has " + formal_args.size() + " given only " + exp.get_Number_of_Args() + "in the expression " + exp.toString());
				}
			}
		}
		throw new Exception("No Function found with the name" + exp.getFunc_name());
	}

	public SET getSET() {
		return mSET;
	}

	/**
	 * The function expands the SET along the specified set of CFG edges
	 *
	 * @param cfgEdges
	 * @throws Exception
	 */
	public void expandSET(List<ICFEdge> cfgEdges) throws Exception {
		for (ICFEdge edge : cfgEdges) {
			singlestep(edge);
		}
	}

	/**
	 * The function performs the addition of a single node to the SET. 
	 * Cases handled: 
	 * case 1 : Leaf node - Basic Block Node & New Node - Basic Block Node 
	 * case 2 : Leaf node - Basic Block Node & New Node - Decision Node
	 * case 3 : Leaf node - Decision Node & New Node - Basic Block Node (3a. New
	 * node belongs to 'Then' Edge 3b. New node belongs to 'Else' edge) 
	 * case 4 : Leaf node - Decision Node & New Node - Decision Node (4a. New node
	 * belongs to 'Then' Edge 4b. New node belongs to 'Else' edge)
	 *
	 * @param edge
	 */

	private void singlestep(ICFEdge edge) throws Exception {
		boolean valid = false;
		this.mSET.updateLeafNodeSet();
		Set<SETNode> newLeafNodes = new LinkedHashSet<SETNode>();
		newLeafNodes = this.mSET.getLeafNodes();
		// check for null edge
		if (edge == null) {
			throw new Exception("Null Edge");
		}
		for (SETNode leaf : newLeafNodes) {
			ICFGNode corrCFGNode = leaf.getCFGNode();
			List<ICFEdge> outCFEdges = corrCFGNode.getOutgoingEdgeList();
			if (outCFEdges.contains(edge)) {
				valid = true;
				ICFGNode newNode = edge.getHead();
				// check for dangling edge
				if (newNode == null) {
					throw new Exception("Dangling Edge");
				}
				SETEdge newSETEdge = new SETEdge(mSET, leaf, null);

				if (leaf instanceof SETBasicBlockNode) {
					((SETBasicBlockNode) leaf).setOutgoingEdge(newSETEdge);
					// case 1
					if (newNode instanceof CFGBasicBlockNode) {
						addNewSETBasicBlockNode(newNode, newSETEdge);
					}
					// case 2
					else if (newNode instanceof CFGDecisionNode) {
						addNewSETDecisionNode(newNode, newSETEdge);
					}
				}

				else if (leaf instanceof SETDecisionNode) {
					CFGDecisionNode corrDecisionNode = (CFGDecisionNode) corrCFGNode;
					if (edge.equals(corrDecisionNode.getThenEdge())) {
						// case 3a
						if (newNode instanceof CFGBasicBlockNode) {
							addNewSETBasicBlockNode(newNode, newSETEdge);
							((SETDecisionNode) leaf).setThenEdge(newSETEdge);
						}
						// case 4a
						else if (newNode instanceof CFGDecisionNode) {
							addNewSETDecisionNode(newNode, newSETEdge);
							((SETDecisionNode) leaf).setThenEdge(newSETEdge);
						}
					} else if (edge.equals(corrDecisionNode.getElseEdge())) {
						// case 3b
						if (newNode instanceof CFGBasicBlockNode) {
							addNewSETBasicBlockNode(newNode, newSETEdge);
							((SETDecisionNode) leaf).setElseEdge(newSETEdge);
						}
						// case 4b
						else if (newNode instanceof CFGDecisionNode) {
							addNewSETDecisionNode(newNode, newSETEdge);
							((SETDecisionNode) leaf).setElseEdge(newSETEdge);
						}
					}
				}
			}
			else {
				System.out.println("Invalid : edge = " + edge + " node = " + corrCFGNode);
			}
		}
		if (!valid) {
			throw new Exception("New Node not connected  to Leaf ");
		}
	}

	public void addNewSETDecisionNode(ICFGNode newNode, SETEdge newSETEdge)
			throws Exception {
		CFGDecisionNode decisionNode = (CFGDecisionNode) newNode;
		SETDecisionNode newSETNode = new SETDecisionNode(
				decisionNode.getCondition(), mSET, decisionNode);
		this.mSET.addDecisionNode(newSETNode);
		newSETEdge.setHead(newSETNode);
		newSETEdge.setHead(newSETNode);

		newSETNode.setIncomingEdge(newSETEdge);
		this.mSET.addEdge(newSETEdge);
		this.computeExpression(newSETNode);
	}

	private void addNewSETBasicBlockNode(ICFGNode newNode, SETEdge newSETEdge) throws Exception {
		SETBasicBlockNode newSETNode = new SETBasicBlockNode(mSET,
				(CFGBasicBlockNode) newNode);
		this.mSET.addBasicBlockNode(newSETNode);
		newSETEdge.setHead(newSETNode);
		newSETNode.setIncomingEdge(newSETEdge);
		this.mSET.addEdge(newSETEdge);
		this.computeStatementList(newSETNode);
	}

	private void computeStatementList(SETBasicBlockNode node) throws Exception {
		ICFGBasicBlockNode cfgBasicBlockNode = (ICFGBasicBlockNode) node
				.getCFGNode();
		List<IStatement> statements = cfgBasicBlockNode.getStatements();

		for (IStatement statement : statements) {
			SETExpressionVisitor visitor = new SETExpressionVisitor(this, node,
					statement.getLHS().getType());
			IExpression value = null;

			visitor.visit(statement.getRHS());
			value = visitor.getValue();

			IIdentifier var = statement.getLHS();
			node.setValue(var, value);
		}
	}

	private void computeExpression(SETDecisionNode node) throws Exception {
		SETExpressionVisitor visitor = new SETExpressionVisitor(this, node,
				Type.BOOLEAN);
		CFGDecisionNode cfgNode = (CFGDecisionNode) node.getCFGNode();
		if (node.getCondition() == null) {
			throw new Exception("Null Expression");
		} else {
			visitor.visit(cfgNode.getCondition());
			IExpression value = visitor.getValue();
			node.setCondition(value);
		}
	}
}
