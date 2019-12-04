<<<<<<< HEAD
package statement;

import program.IProgram;
import expression.IExpression;
import expression.IIdentifier;

public class Statement implements IStatement {

	private IIdentifier mLHS;
	private IExpression mRHS;
	private IProgram mProgram;
	
	public Statement(IProgram p, IIdentifier lhs, IExpression rhs) {
		this.mProgram = p;
		this.mLHS = lhs;
		this.mRHS = rhs;
	}
	@Override
	public IIdentifier getLHS() {
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
=======
package statement;

import program.IProgram;
import expression.IExpression;
import expression.IIdentifier;

public class Statement implements IStatement {

	private IIdentifier mLHS;
	private IExpression mRHS;
	private IProgram mProgram;
	
	public Statement(IProgram p, IIdentifier lhs, IExpression rhs) {
		this.mProgram = p;
		this.mLHS = lhs;
		this.mRHS = rhs;
	}
	@Override
	public IIdentifier getLHS() {
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
>>>>>>> 25ebf124fd450adaed57ee1006dedb1a7734bcf9
