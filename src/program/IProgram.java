package program;

import java.util.Set;

import expression.IIdentifier;

public interface IProgram {
	public IIdentifier addVariable(IIdentifier var);
	public Set<IIdentifier> getVariables();
	boolean hasVariable(IIdentifier var);
}
