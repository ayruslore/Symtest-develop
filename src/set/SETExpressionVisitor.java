<<<<<<< HEAD
package set;

import java.util.*;

import expression.*;
import functions.FunctionHandler;
import program.IProgram;
import see.SEE;
import summaries.SummaryHandler;
import visitors.IExprVisitor;

public class SETExpressionVisitor implements IExprVisitor<IExpression> {

	private SEE mSEE;
	private Stack<IExpression> mStack = new Stack<IExpression>();
	private final String mContextType; 

	public SETExpressionVisitor(SEE see, String type) {
		this.mSEE = see;
		this.mContextType = type;		
	}

	@Override
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
		else if(exp instanceof BooleanVariable) {
			this.visit((BooleanVariable)exp);
		}
		else if (exp instanceof Input) {
			this.visit((Input)exp);
		}
		else if(exp instanceof BooleanInput) {
			this.visit((BooleanInput)exp);
		}
		else if(exp instanceof AddExpression) {
			this.visit((AddExpression)exp);
		}

		else if(exp instanceof SubExpression) {
			this.visit((SubExpression)exp);
		}

		else if(exp instanceof MulExpression) {
			this.visit((MulExpression)exp);
		}

		else if(exp instanceof DivExpression) {
			this.visit((DivExpression)exp);
		}

		else if(exp instanceof GreaterThanEqualToExpression) {
			this.visit((GreaterThanEqualToExpression)exp);
		}

		else if(exp instanceof LesserThanExpression) {
			this.visit((LesserThanExpression)exp);
		}

		else if(exp instanceof LesserThanEqualToExpression) {
			this.visit((LesserThanEqualToExpression)exp);
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
		else if(exp instanceof FunctionCallExpression) {
		    this.visit((FunctionCallExpression)exp);
        }
		else if(exp == null) {
		}
		else {
			Exception e = new Exception("SETExpressionVisitor : Type '" + exp.getClass().getCanonicalName() + "' of expression not handled.");
			throw e;
		}
	}
	
	@Override
	public void visit(Input exp) {
		IProgram p = this.mSEE.getSET();
		Set<IIdentifier> variables = p.getVariables();
		Set<String> names = new LinkedHashSet<String>();
		for(IIdentifier v : variables) {
			names.add(v.getName());
		}
		String name = SETExpressionVisitor.generateNewVariableName(names);
		try {
			this.mStack.push(new Variable(name, this.mContextType, this.mSEE.getSET()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void visit(BooleanInput exp) {
		IProgram p = this.mSEE.getSET();
		Set<IIdentifier> variables = p.getVariables();
		Set<String> names = new LinkedHashSet<String>();
		for(IIdentifier v : variables) {
			names.add(v.getName());
		}
		String name = SETExpressionVisitor.generateNewVariableName(names);
		try {
			this.mStack.push(new BooleanVariable(name, p));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void visit(ConcreteConstant exp) throws Exception {
		this.mStack.push(new ConcreteConstant(exp.getValue(), this.mSEE.getSET()));
	}

	@Override
	public void visit(False exp) throws Exception {
		this.mStack.push(new False(this.mSEE.getSET()));
	}

	@Override
	public void visit(GreaterThanExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new GreaterThanExpression(this.mSEE.getSET(), lhs, rhs));
	}

	@Override
	public void visit(AddExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new AddExpression(this.mSEE.getSET(), lhs, rhs));
	}

	@Override
	public void visit(SubExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new SubExpression(this.mSEE.getSET(), lhs, rhs));
	}

	@Override
	public void visit(MulExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new MulExpression(this.mSEE.getSET(), lhs, rhs));
	}



	@Override
	public void visit(DivExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new DivExpression(this.mSEE.getSET(), lhs, rhs));
	}
	
	@Override
	public void visit(GreaterThanEqualToExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new GreaterThanEqualToExpression(this.mSEE.getSET(), lhs, rhs));
	}
	
	@Override
	public void visit(LesserThanExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new LesserThanExpression(this.mSEE.getSET(), lhs, rhs));
	}
	
	@Override
	public void visit(LesserThanEqualToExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new LesserThanEqualToExpression(this.mSEE.getSET(), lhs, rhs));
	}

	@Override
	public void visit(True exp) throws Exception {
		this.mStack.push(new True(this.mSEE.getSET()));
	}

	@Override
	public void visit(Variable exp) {
		SET trace = this.mSEE.getSET();
		trace.updateLeafNodeSet();
		SETNode node = (SETNode) trace.getLeafNodes().iterator().next();
		this.mStack.push(node.getLatestValue(exp));
	}

	@Override
	public void visit(BooleanVariable exp) {
		SET trace = this.mSEE.getSET();
		trace.updateLeafNodeSet();
		SETNode node = (SETNode) trace.getLeafNodes().iterator().next();
		this.mStack.push(node.getLatestValue(exp));
	}

	private static String generateNewVariableName (Set<String> names) {
		
		while (true) {
			Random random = new Random ();
			int integer = random.nextInt();
			if (integer < 0) {
				integer = -1 * integer;
			}
			String name = "symvar" + Integer.toString(integer);
			if (!names.contains(name)) {
				return name;
			}
		}
	}

	@Override
	public void visit(AndExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new AndExpression(this.mSEE.getSET(), lhs, rhs));
	}


	@Override
	public void visit(OrExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new OrExpression(this.mSEE.getSET(), lhs, rhs));
	}

	@Override
	public void visit(NotExpression exp) throws Exception {
		exp.accept(this);
		this.mStack.push(new NotExpression(this.mSEE.getSET(), this.mStack.pop()));
	}

	@Override
	public void visit(EqualsExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new EqualsExpression(this.mSEE.getSET(), lhs, rhs));
	}

	@Override
	public void visit(FunctionCallExpression exp) throws Exception {
		//FunctionHandler handler = new FunctionHandler(this.mSEE, exp);
		//IExpression return_value = handler.makeFunctionCall();
		//this.mStack.push(return_value);
		SummaryHandler handler = new SummaryHandler(this.mSEE, exp);
		IExpression return_value = handler.makeFunctionCall();
		this.mStack.push(return_value);
	}

	@Override
	public IExpression getValue() {
		return this.mStack.peek();
	}
}
=======
package set;

import java.util.*;

import expression.*;
import functions.FunctionHandler;
import program.IProgram;
import see.SEE;
import visitors.IExprVisitor;

public class SETExpressionVisitor implements IExprVisitor<IExpression> {

	private SEE mSEE;
	private Stack<IExpression> mStack = new Stack<IExpression>();
	private final String mContextType; 

	public SETExpressionVisitor(SEE see, String type) {
		this.mSEE = see;
		this.mContextType = type;		
	}

	@Override
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
		else if(exp instanceof BooleanVariable) {
			this.visit((BooleanVariable)exp);
		}
		else if (exp instanceof Input) {
			this.visit((Input)exp);
		}
		else if(exp instanceof BooleanInput) {
			this.visit((BooleanInput)exp);
		}
		else if(exp instanceof AddExpression) {
			this.visit((AddExpression)exp);
		}

		else if(exp instanceof SubExpression) {
			this.visit((SubExpression)exp);
		}

		else if(exp instanceof MulExpression) {
			this.visit((MulExpression)exp);
		}

		else if(exp instanceof DivExpression) {
			this.visit((DivExpression)exp);
		}

		else if(exp instanceof GreaterThanEqualToExpression) {
			this.visit((GreaterThanEqualToExpression)exp);
		}

		else if(exp instanceof LesserThanExpression) {
			this.visit((LesserThanExpression)exp);
		}

		else if(exp instanceof LesserThanEqualToExpression) {
			this.visit((LesserThanEqualToExpression)exp);
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
		else if(exp instanceof FunctionCallExpression) {
		    this.visit((FunctionCallExpression)exp);
        }
		else if(exp == null) {
		}
		else {
			Exception e = new Exception("SETExpressionVisitor : Type '" + exp.getClass().getCanonicalName() + "' of expression not handled.");
			throw e;
		}
	}
	
	@Override
	public void visit(Input exp) {
		IProgram p = this.mSEE.getSET();
		Set<IIdentifier> variables = p.getVariables();
		Set<String> names = new LinkedHashSet<String>();
		for(IIdentifier v : variables) {
			names.add(v.getName());
		}
		String name = SETExpressionVisitor.generateNewVariableName(names);
		try {
			this.mStack.push(new Variable(name, this.mContextType, this.mSEE.getSET()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void visit(BooleanInput exp) {
		IProgram p = this.mSEE.getSET();
		Set<IIdentifier> variables = p.getVariables();
		Set<String> names = new LinkedHashSet<String>();
		for(IIdentifier v : variables) {
			names.add(v.getName());
		}
		String name = SETExpressionVisitor.generateNewVariableName(names);
		try {
			this.mStack.push(new BooleanVariable(name, p));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void visit(ConcreteConstant exp) throws Exception {
		this.mStack.push(new ConcreteConstant(exp.getValue(), this.mSEE.getSET()));
	}

	@Override
	public void visit(False exp) throws Exception {
		this.mStack.push(new False(this.mSEE.getSET()));
	}

	@Override
	public void visit(GreaterThanExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new GreaterThanExpression(this.mSEE.getSET(), lhs, rhs));
	}

	@Override
	public void visit(AddExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new AddExpression(this.mSEE.getSET(), lhs, rhs));
	}

	@Override
	public void visit(SubExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new SubExpression(this.mSEE.getSET(), lhs, rhs));
	}

	@Override
	public void visit(MulExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new MulExpression(this.mSEE.getSET(), lhs, rhs));
	}



	@Override
	public void visit(DivExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new DivExpression(this.mSEE.getSET(), lhs, rhs));
	}
	
	@Override
	public void visit(GreaterThanEqualToExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new GreaterThanEqualToExpression(this.mSEE.getSET(), lhs, rhs));
	}
	
	@Override
	public void visit(LesserThanExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new LesserThanExpression(this.mSEE.getSET(), lhs, rhs));
	}
	
	@Override
	public void visit(LesserThanEqualToExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new LesserThanEqualToExpression(this.mSEE.getSET(), lhs, rhs));
	}

	@Override
	public void visit(True exp) throws Exception {
		this.mStack.push(new True(this.mSEE.getSET()));
	}

	@Override
	public void visit(Variable exp) {
		SET trace = this.mSEE.getSET();
		trace.updateLeafNodeSet();
		SETNode node = (SETNode) trace.getLeafNodes().iterator().next();
		this.mStack.push(node.getLatestValue(exp));
	}

	@Override
	public void visit(BooleanVariable exp) {
		SET trace = this.mSEE.getSET();
		trace.updateLeafNodeSet();
		SETNode node = (SETNode) trace.getLeafNodes().iterator().next();
		this.mStack.push(node.getLatestValue(exp));
	}

	private static String generateNewVariableName (Set<String> names) {
		
		while (true) {
			Random random = new Random ();
			int integer = random.nextInt();
			if (integer < 0) {
				integer = -1 * integer;
			}
			String name = "symvar" + Integer.toString(integer);
			if (!names.contains(name)) {
				return name;
			}
		}
	}

	@Override
	public void visit(AndExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new AndExpression(this.mSEE.getSET(), lhs, rhs));
	}


	@Override
	public void visit(OrExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new OrExpression(this.mSEE.getSET(), lhs, rhs));
	}

	@Override
	public void visit(NotExpression exp) throws Exception {
		exp.accept(this);
		this.mStack.push(new NotExpression(this.mSEE.getSET(), this.mStack.pop()));
	}

	@Override
	public void visit(EqualsExpression exp) throws Exception {
		exp.accept(this);
		IExpression lhs = this.mStack.pop();
		IExpression rhs = this.mStack.pop();
		this.mStack.push(new EqualsExpression(this.mSEE.getSET(), lhs, rhs));
	}

	@Override
	public void visit(FunctionCallExpression exp) throws Exception {
		FunctionHandler handler = new FunctionHandler(this.mSEE, exp);
		IExpression return_value = handler.makeFunctionCall();
		this.mStack.push(return_value);
	}

	@Override
	public IExpression getValue() {
		return this.mStack.peek();
	}
}
>>>>>>> 25ebf124fd450adaed57ee1006dedb1a7734bcf9
