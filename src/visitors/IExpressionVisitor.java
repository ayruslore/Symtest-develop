<<<<<<< HEAD
package visitors;

import expression.*;

public interface IExpressionVisitor {
	public IExpression visit(ConcreteConstant exp);
	public IExpression visit(False exp);
	public IExpression visit(GreaterThanExpression exp);
	public IExpression visit(True exp);
	public IExpression visit(Variable exp);
	public void push(IExpression lhs);
	public IExpression visit(IExpression exp) throws Exception;
	public IExpression visit(AddExpression exp);
	public IExpression visit(SubExpression exp);
	public IExpression visit(MulExpression exp);
	public IExpression visit(DivExpression exp);
	public IExpression visit(GreaterThanEqualToExpression exp);
	public IExpression visit(LesserThanExpression exp);
	public IExpression visit(LesserThanEqualToExpression exp);
	public IExpression visit(FunctionCallExpression exp);
=======
package visitors;

import expression.*;

public interface IExpressionVisitor {
	public IExpression visit(ConcreteConstant exp);
	public IExpression visit(False exp);
	public IExpression visit(GreaterThanExpression exp);
	public IExpression visit(True exp);
	public IExpression visit(Variable exp);
	public void push(IExpression lhs);
	public IExpression visit(IExpression exp) throws Exception;
	public IExpression visit(AddExpression exp);
	public IExpression visit(SubExpression exp);
	public IExpression visit(MulExpression exp);
	public IExpression visit(DivExpression exp);
	public IExpression visit(GreaterThanEqualToExpression exp);
	public IExpression visit(LesserThanExpression exp);
	public IExpression visit(LesserThanEqualToExpression exp);
	public IExpression visit(FunctionCallExpression exp);
>>>>>>> 25ebf124fd450adaed57ee1006dedb1a7734bcf9
}