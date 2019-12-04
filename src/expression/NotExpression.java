<<<<<<< HEAD
package expression;

import program.IProgram;
import visitors.IExprVisitor;

public class NotExpression extends Expression {

	private IExpression mExpression;

	public NotExpression(IProgram program, IExpression exp) throws Exception {
		super(program, Type.BOOLEAN);
		if(exp.getType() != Type.BOOLEAN) {
			Exception e = new Exception("NotExpression : Type error in " + exp.toString());
			throw e;
		}
		this.mExpression = exp;
	}

	public IExpression getExpression() {
		return this.mExpression;
	}
	
	@Override
	public void accept(IExprVisitor<?> visitor) throws Exception {
		visitor.visit(this.mExpression);
	}
	
	@Override
	public String toString() {
		return "(" + " not " + this.mExpression.toString() + ")";
	}

}
=======
package expression;

import program.IProgram;
import visitors.IExprVisitor;

public class NotExpression extends Expression {

	private IExpression mExpression;

	public NotExpression(IProgram program, IExpression exp) throws Exception {
		super(program, Type.BOOLEAN);
		if(exp.getType() != Type.BOOLEAN) {
			Exception e = new Exception("NotExpression : Type error in " + exp.toString());
			throw e;
		}
		this.mExpression = exp;
	}

	public IExpression getExpression() {
		return this.mExpression;
	}
	
	@Override
	public void accept(IExprVisitor<?> visitor) throws Exception {
		visitor.visit(this.mExpression);
	}
	
	@Override
	public String toString() {
		return "(" + " not " + this.mExpression.toString() + ")";
	}

}
>>>>>>> 25ebf124fd450adaed57ee1006dedb1a7734bcf9
