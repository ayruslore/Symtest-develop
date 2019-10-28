package statement;

import program.IProgram;
import expression.IExpression;
import expression.IIdentifier;

public interface IStatement {
	public IIdentifier getLHS();
	public IExpression getRHS();
	public IProgram getProgram();
}
