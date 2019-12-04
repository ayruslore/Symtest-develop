<<<<<<< HEAD
package expression;

import program.IProgram;
import visitors.IAcceptor;

public interface IExpression extends IAcceptor {
	public IProgram getProgram();
	public void setProgram(IProgram program);
	public String getType();
	public String toString();
}
=======
package expression;

import program.IProgram;
import visitors.IAcceptor;

public interface IExpression extends IAcceptor {
	public IProgram getProgram();
	public void setProgram(IProgram program);
	public String getType();
	public String toString();
}
>>>>>>> 25ebf124fd450adaed57ee1006dedb1a7734bcf9
