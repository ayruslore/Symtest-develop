<<<<<<< HEAD
package expression;

import program.IProgram;
import visitors.IExprVisitor;

public class True extends Expression {

	public True(IProgram program) throws Exception {
		super(program, Type.BOOLEAN);
	}

	@Override
	public String toString() {
		return "true";
	}

	@Override
	public void accept(IExprVisitor<?> visitor) {
	}
}
=======
package expression;

import program.IProgram;
import visitors.IExprVisitor;

public class True extends Expression {

	public True(IProgram program) throws Exception {
		super(program, Type.BOOLEAN);
	}

	@Override
	public String toString() {
		return "true";
	}

	@Override
	public void accept(IExprVisitor<?> visitor) {
	}
}
>>>>>>> 25ebf124fd450adaed57ee1006dedb1a7734bcf9
