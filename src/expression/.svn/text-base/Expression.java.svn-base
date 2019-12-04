<<<<<<< HEAD
package expression;

import program.IProgram;

public abstract class Expression implements IExpression {

	protected IProgram mProgram = null;
	protected final String mType;

	public Expression(IProgram program, String type) throws Exception {
		this.mProgram = program;
		this.mType = type;
		if(!Type.hasType(type)) {
			Exception e = new Exception("Expression : Type " + type + " not found.");
			throw e;
		}
	}
	@Override
	public IProgram getProgram() {
		return this.mProgram;
	}

	@Override
	public void setProgram(IProgram program) {
		this.mProgram = program;
	}

	public final String getType() {
		return this.mType;
	}
}
=======
package expression;

import program.IProgram;

public abstract class Expression implements IExpression {

	protected IProgram mProgram = null;
	protected final String mType;

	public Expression(IProgram program, String type) throws Exception {
		this.mProgram = program;
		this.mType = type;
		if(!Type.hasType(type)) {
			Exception e = new Exception("Expression : Type " + type + " not found.");
			throw e;
		}
	}
	@Override
	public IProgram getProgram() {
		return this.mProgram;
	}

	@Override
	public void setProgram(IProgram program) {
		this.mProgram = program;
	}

	public final String getType() {
		return this.mType;
	}
}
>>>>>>> 25ebf124fd450adaed57ee1006dedb1a7734bcf9
