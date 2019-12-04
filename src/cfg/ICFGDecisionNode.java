<<<<<<< HEAD
package cfg;

import program.IDecisionNode;
import expression.IExpression;

public interface ICFGDecisionNode extends ICFGNode, IDecisionNode {

	public IExpression getCondition();
	public ICFEdge getThenEdge();
	public ICFEdge getElseEdge();
	public ICFGNode getThenSuccessorNode();
	public ICFGNode getElseSuccessorNode();
	public ICFEdge setThenEdge(ICFEdge edge);
	public ICFEdge setElseEdge(ICFEdge edge);
	public ICFEdge deleteThenEdge();
	public ICFEdge deleteElseEdge();
}
=======
package cfg;

import program.IDecisionNode;
import expression.IExpression;

public interface ICFGDecisionNode extends ICFGNode, IDecisionNode {

	public IExpression getCondition();
	public ICFEdge getThenEdge();
	public ICFEdge getElseEdge();
	public ICFGNode getThenSuccessorNode();
	public ICFGNode getElseSuccessorNode();
	public ICFEdge setThenEdge(ICFEdge edge);
	public ICFEdge setElseEdge(ICFEdge edge);
	public ICFEdge deleteThenEdge();
	public ICFEdge deleteElseEdge();
}
>>>>>>> 25ebf124fd450adaed57ee1006dedb1a7734bcf9
