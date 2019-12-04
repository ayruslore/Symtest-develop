package statement;

import program.IProgram;
import expression.IExpression;
import expression.Variable;

public class Statement implements IStatement {

	private Variable mLHS;
	private IExpression mRHS;
	private IProgram mProgram;
	
	public Statement(IProgram p, Variable lhs, IExpression rhs) {
		this.mProgram = p;
		this.mLHS = lhs;
		this.mRHS = rhs;
	}
	@Override
	public Variable getLHS() {
		return this.mLHS;
	}

	@Override
	public IExpression getRHS() {
		return this.mRHS;
	}
	
	@Override
	public IProgram getProgram() {
		return this.mProgram;
	}

	@Override
	public String toString() {
		return this.mLHS.toString() + " = " + this.mRHS.toString();
	}
	
}
