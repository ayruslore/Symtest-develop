package visitors;

import expression.AddExpression;
import expression.AndExpression;
import expression.ConcreteConstant;
import expression.EqualsExpression;
import expression.False;
import expression.GreaterThanExpression;
import expression.IExpression;
import expression.NotExpression;
import expression.OrExpression;
import expression.True;
import expression.Variable;

public interface IExprVisitor<T> {
	
	public void visit(ConcreteConstant exp) throws Exception;

	public void visit(False exp) throws Exception;

	public void visit(GreaterThanExpression exp) throws Exception;
	
	public void visit(AddExpression exp) throws Exception;

	public void visit(AndExpression exp) throws Exception;
	
	public void visit(OrExpression exp) throws Exception;
	
	public void visit(True exp) throws Exception;

	public void visit(Variable exp) throws Exception;
	
	public void visit(NotExpression exp) throws Exception;
	
	public void visit(EqualsExpression exp) throws Exception;
	
	public void visit(IExpression exp) throws Exception;
	
	public T getValue();

}
