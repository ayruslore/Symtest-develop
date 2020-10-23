package program;

import java.util.List;

import statement.IStatement;

public interface IBasicBlockNode extends IProgramNode {
	public List<IStatement> getStatements();
	public void addStatement(IStatement statement);
}
