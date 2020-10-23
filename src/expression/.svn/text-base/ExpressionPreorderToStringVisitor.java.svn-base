package expression;

import java.util.Stack;

import visitors.IExprVisitor;

public class ExpressionPreorderToStringVisitor implements IExprVisitor<String> {

	Stack<String> mStack = new Stack<String>();
	
	@Override
	public void visit(ConcreteConstant exp)  throws Exception {
		this.mStack.push(exp.toString());
	}

	@Override
	public void visit(False exp)  throws Exception {
		this.mStack.push(exp.toString());
	}

	@Override
	public void visit(GreaterThanExpression exp) throws Exception  {
		exp.accept(this);
		String s = "( > " + this.mStack.pop() + " " + this.mStack.pop() + ")";
		this.mStack.push(s);
	}
	
	@Override
	public void visit(AddExpression exp)  throws Exception {
		exp.accept(this);
		String s = "( + " + this.mStack.pop() + " " + this.mStack.pop() + ")";
		this.mStack.push(s);
	}

	@Override
	public void visit(AndExpression exp) throws Exception {
		exp.accept(this);
		String s = "( and " + this.mStack.pop() + " " + this.mStack.pop() + ")";
		this.mStack.push(s);
	}

	@Override
	public void visit(OrExpression exp) throws Exception {
		exp.accept(this);
		String s = "( or " + this.mStack.pop() + " " + this.mStack.pop() + ")";
		this.mStack.push(s);
	}
	
	@Override
	public void visit(True exp) throws Exception {
		this.mStack.push(exp.toString());
	}
	
	@Override
	public void visit(Variable exp) throws Exception {
		this.mStack.push(exp.toString());
	}

	@Override
	public void visit(NotExpression exp) throws Exception {
		exp.accept(this);
		String s = "( not " + this.mStack.pop() + ")";
		this.mStack.push(s);
	}

	@Override
	public void visit(EqualsExpression exp) throws Exception {
		exp.accept(this);
		String s = "( = " + this.mStack.pop() + " " + this.mStack.pop() + ")";
		this.mStack.push(s);
	}

	public void visit(IExpression exp) throws Exception {
		if(exp instanceof ConcreteConstant) {
			this.visit((ConcreteConstant)exp);
		}
		else if(exp instanceof False) {
			this.visit((False)exp);
		}
		else if(exp instanceof GreaterThanExpression) {
			this.visit((GreaterThanExpression)exp);
		}
		else if(exp instanceof True) {
			this.visit((True)exp);
		}
		else if(exp instanceof Variable) {
			this.visit((Variable)exp);
		}
		else if (exp instanceof Input) {
			this.visit((Input)exp);
		}
		else if(exp instanceof AddExpression) {
			this.visit((AddExpression)exp);
		}
		else if(exp instanceof AndExpression) {
			this.visit((AndExpression)exp);
		}
		else if(exp instanceof OrExpression) {
			this.visit((OrExpression)exp);
		}
		else if(exp instanceof NotExpression) {
			this.visit((NotExpression)exp);
		}
		else if(exp instanceof EqualsExpression) {
			this.visit((EqualsExpression)exp);
		}
		else if(exp == null) {
		}
		else {
			Exception e = new Exception("ExpressionPreorderToStringVisitor : Type '" + exp.getClass().getCanonicalName() + "' of expression not handled.");
			throw e;
		}
	}
	
	public String getValue() {
		return this.mStack.peek();
	}
}
