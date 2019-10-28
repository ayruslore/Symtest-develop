package visitors;

import expression.AddExpression;
import expression.AndExpression;
import expression.BooleanVariable;
import expression.ConcreteConstant;
import expression.DivExpression;
import expression.EqualsExpression;
import expression.False;
import expression.GreaterThanEqualToExpression;
import expression.GreaterThanExpression;
import expression.IExpression;
import expression.IIdentifier;
import expression.Input;
import expression.LesserThanEqualToExpression;
import expression.LesserThanExpression;
import expression.MulExpression;
import expression.NotExpression;
import expression.OrExpression;
import expression.SubExpression;
import expression.True;
import expression.Variable;

public interface IExprVisitor<T> {
	void visit(Input exp);

	void visit(ConcreteConstant exp) throws Exception;

	void visit(False exp) throws Exception;

	void visit(GreaterThanExpression exp) throws Exception;

	void visit(AddExpression exp) throws Exception;

	void visit(SubExpression exp) throws Exception;

	void visit(MulExpression exp) throws Exception;

	void visit(DivExpression exp) throws Exception;

	void visit(LesserThanExpression exp) throws Exception;

	void visit(LesserThanEqualToExpression exp) throws Exception;

	void visit(GreaterThanEqualToExpression exp) throws Exception;

	void visit(AndExpression exp) throws Exception;

	void visit(OrExpression exp) throws Exception;

	void visit(True exp) throws Exception;

	void visit(Variable exp) throws Exception;

	void visit(BooleanVariable exp) throws Exception;

	void visit(NotExpression exp) throws Exception;

	void visit(EqualsExpression exp) throws Exception;

	void visit(IExpression exp) throws Exception;

	public T getValue();

}
