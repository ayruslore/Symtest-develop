package functiontestcases;

import cfg.ICFEdge;
import cfg.ICFG;
import cfg.ICFGBasicBlockNode;
import cfg.ICFGDecisionNode;
import expression.*;
import functions.Function;
import mycfg.CFEdge;
import mycfg.CFG;
import mycfg.CFGBasicBlockNode;
import mycfg.CFGDecisionNode;
import statement.Statement;
import tester.SymTest;
import tester.TestSequence;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CheckTriangle {
    public static int checkTriangleFormed(int a, int b, int c){
        //check all combinations,
        //sum of any 2 sides must be greater than the third side to form a triangle
        if(check(a, b, c) && check(a, c, b) && check(b, c, a))
            return 1;
        else
            return 0;
    }

    public static boolean check(int x, int y, int z){
        //check if sum of x and y is greater than z
        if((x+y)>z)
            return true;
        return false;
    }

    public static void main(String[] args) {
        int a = 2;
        int b = 3;
        int c = 5;
        if(a>=1 && b>=1 && c >= 1) {
            int formed = checkTriangleFormed(a, b, c);
            System.out.println(formed + "\n\n");
        }

        try{
            TestCheckTriangle();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        /*a = 1;
        b = 3;
        c = 5;
        formed = checkTriangleFormed(a, b, c);
        System.out.println(formed);*/
    }

    public static void TestCheckTriangle() throws Exception {

        System.out.println("Test Case Starts\n\n");
        ICFG mCFG = null;
        Set<ICFEdge> targets = new LinkedHashSet<ICFEdge>();
        ICFGBasicBlockNode start = new CFGBasicBlockNode("start", null);
        ICFGBasicBlockNode stop = new CFGBasicBlockNode("stop", null);
        mCFG = new CFG(start, stop);

        ICFGBasicBlockNode init = new CFGBasicBlockNode("init", mCFG);
        mCFG.addBasicBlockNode(init);
        Variable a = new Variable("a", mCFG);
        Variable b = new Variable("b", mCFG);
        Variable c = new Variable("c", mCFG);
        init.addStatement(new Statement(mCFG, a, new Input(mCFG)));
        init.addStatement(new Statement(mCFG, b, new Input(mCFG)));
        init.addStatement(new Statement(mCFG, c, new Input(mCFG)));

        GreaterThanExpression g1 = new GreaterThanExpression(mCFG, a, new ConcreteConstant(1, mCFG));
        GreaterThanExpression g2 = new GreaterThanExpression(mCFG, b, new ConcreteConstant(1, mCFG));
        GreaterThanExpression g3 = new GreaterThanExpression(mCFG, c, new ConcreteConstant(1, mCFG));
        AndExpression exp3 = new AndExpression(mCFG, g1, new AndExpression(mCFG, g2, g3));
        ICFGDecisionNode d1 = new CFGDecisionNode("d1", mCFG, exp3);
        mCFG.addDecisionNode(d1);

        ICFGBasicBlockNode call = new CFGBasicBlockNode("call", mCFG);
        mCFG.addBasicBlockNode(call);
        Variable formed = new Variable("formed", mCFG);
        Set<IExpression> actual_args = new LinkedHashSet<IExpression>();
        actual_args.add(a);
        actual_args.add(b);
        actual_args.add(c);
        call.addStatement(new Statement(mCFG, formed, new FunctionCallExpression(mCFG, "checkTriangleFormed", actual_args, Type.INT)));

        ICFEdge start_init = new CFEdge("start_init", mCFG, start, init);
        mCFG.addEdge(start_init);
        ICFEdge init_d1 = new CFEdge("init_d1", mCFG, init, d1);
        mCFG.addEdge(init_d1);
        ICFEdge d1_call = new CFEdge("d1_call", mCFG, d1, call);
        mCFG.addEdge(d1_call);
        ICFEdge d1_stop = new CFEdge("d1_stop", mCFG, d1, stop);
        mCFG.addEdge(d1_stop);
        d1.setThenEdge(d1_call);
        d1.setElseEdge(d1_stop);
        ICFEdge call_stop = new CFEdge("call_stop", mCFG, call, stop);
        mCFG.addEdge(call_stop);

        targets.add(d1_call);

        Set<Function> all_functions = new LinkedHashSet<Function>();

        ICFG check = null;
        Set<ICFEdge> check_targets = new LinkedHashSet<ICFEdge>();
        Set<IIdentifier> check_args = new LinkedHashSet<IIdentifier>();
        ICFGBasicBlockNode check_start = new CFGBasicBlockNode("check_start", null);
        ICFGBasicBlockNode check_stop = new CFGBasicBlockNode("check_stop", null);
        check = new CFG(check_start, check_stop);
        Variable check_x = new Variable("check_x", check);
        Variable check_y = new Variable("check_y", check);
        Variable check_z = new Variable("check_z", check);
        GreaterThanExpression exp = new GreaterThanExpression(mCFG, new AddExpression(mCFG, check_x, check_y), check_z);
        ICFGDecisionNode check_d1 = new CFGDecisionNode("check_d1", check, exp);
        check.addDecisionNode(check_d1);

        ICFGBasicBlockNode check_then = new CFGBasicBlockNode("check_then", check);
        mCFG.addBasicBlockNode(check_then);
        BooleanVariable check_return = new BooleanVariable("return_value", check);
        check_then.addStatement(new Statement(check, check_return, new True(check)));

        ICFGBasicBlockNode check_else = new CFGBasicBlockNode("check_else", check);
        mCFG.addBasicBlockNode(check_else);
        check_else.addStatement(new Statement(check, check_return, new False(check)));

        ICFEdge check_start_d1 = new CFEdge("check_start_d1", check, check_start, check_d1);
        check.addEdge(check_start_d1);
        ICFEdge check_d1_then = new CFEdge("check_d1_then", check, check_d1, check_then);
        check.addEdge(check_d1_then);
        check_d1.setThenEdge(check_d1_then);
        ICFEdge check_d1_else = new CFEdge("check_d1_else", check, check_d1, check_else);
        check.addEdge(check_d1_else);
        check_d1.setElseEdge(check_d1_else);
        ICFEdge check_then_stop = new CFEdge("check_then_stop", check, check_then, check_stop);
        check.addEdge(check_then_stop);
        ICFEdge check_else_stop = new CFEdge("check_else_stop", check, check_else, check_stop);
        check.addEdge(check_else_stop);

        check_targets.add(check_d1_then);
        //check_targets.add(check_d1_else);
        check_args.add(check_x);
        check_args.add(check_y);
        check_args.add(check_z);

        Function Check = new Function("check", check, check_args, check_targets, Type.BOOLEAN);
        all_functions.add(Check);

        ICFG checkTriangleFormed_ = null;
        Set<ICFEdge> checkTriangleFormed_targets = new LinkedHashSet<ICFEdge>();
        Set<IIdentifier> checkTriangleFormed_args = new LinkedHashSet<IIdentifier>();
        ICFGBasicBlockNode checkTriangleFormed_start = new CFGBasicBlockNode("checkTriangleFormed_start", null);
        ICFGBasicBlockNode checkTriangleFormed_stop = new CFGBasicBlockNode("checkTriangleFormed_stop", null);
        checkTriangleFormed_ = new CFG(checkTriangleFormed_start, checkTriangleFormed_stop);

        Variable checkTriangleFormed_a = new Variable("checkTriangleFormed_a", checkTriangleFormed_);
        Variable checkTriangleFormed_b = new Variable("checkTriangleFormed_b", checkTriangleFormed_);
        Variable checkTriangleFormed_c = new Variable("checkTriangleFormed_c", checkTriangleFormed_);
        checkTriangleFormed_args.add(checkTriangleFormed_a);
        checkTriangleFormed_args.add(checkTriangleFormed_b);
        checkTriangleFormed_args.add(checkTriangleFormed_c);

        ICFGBasicBlockNode checkTriangleFormed_init = new CFGBasicBlockNode("checkTriangleFormed_init", checkTriangleFormed_);
        checkTriangleFormed_.addBasicBlockNode(checkTriangleFormed_init);
        BooleanVariable dummy1 = new BooleanVariable("dummy1", checkTriangleFormed_);
        BooleanVariable dummy2 = new BooleanVariable("dummy2", checkTriangleFormed_);
        BooleanVariable dummy3 = new BooleanVariable("dummy3", checkTriangleFormed_);
        Set<IExpression> call_args = new LinkedHashSet<IExpression>();
        call_args.add(checkTriangleFormed_a);
        call_args.add(checkTriangleFormed_b);
        call_args.add(checkTriangleFormed_c);
        checkTriangleFormed_init.addStatement(new Statement(checkTriangleFormed_, dummy1, new FunctionCallExpression(checkTriangleFormed_, "check", call_args, Type.BOOLEAN)));
        call_args.clear();
        call_args.add(checkTriangleFormed_a);
        call_args.add(checkTriangleFormed_c);
        call_args.add(checkTriangleFormed_b);
        checkTriangleFormed_init.addStatement(new Statement(checkTriangleFormed_, dummy2, new FunctionCallExpression(checkTriangleFormed_, "check", call_args, Type.BOOLEAN)));
        call_args.clear();
        call_args.add(checkTriangleFormed_b);
        call_args.add(checkTriangleFormed_c);
        call_args.add(checkTriangleFormed_a);
        checkTriangleFormed_init.addStatement(new Statement(checkTriangleFormed_, dummy3, new FunctionCallExpression(checkTriangleFormed_, "check", call_args, Type.BOOLEAN)));

        AndExpression exp2 = new AndExpression(checkTriangleFormed_, dummy1, new AndExpression(checkTriangleFormed_, dummy2, dummy3));
        ICFGDecisionNode checkTriangleFormed_d1 = new CFGDecisionNode("checkTriangleFormed_d1", checkTriangleFormed_, exp2);
        checkTriangleFormed_.addDecisionNode(checkTriangleFormed_d1);

        Variable return_value = new Variable("return_value", checkTriangleFormed_);
        ICFGBasicBlockNode checkTriangleFormed_then = new CFGBasicBlockNode("checkTriangleFormed_then", checkTriangleFormed_);
        checkTriangleFormed_.addBasicBlockNode(checkTriangleFormed_then);
        checkTriangleFormed_then.addStatement(new Statement(checkTriangleFormed_, return_value, new ConcreteConstant(1, checkTriangleFormed_)));

        ICFGBasicBlockNode checkTriangleFormed_else = new CFGBasicBlockNode("checkTriangleFormed_else", checkTriangleFormed_);
        checkTriangleFormed_.addBasicBlockNode(checkTriangleFormed_else);
        checkTriangleFormed_else.addStatement(new Statement(checkTriangleFormed_, return_value, new ConcreteConstant(0, checkTriangleFormed_)));

        ICFEdge checkTriangleFormed_start_init = new CFEdge("checkTriangleFormed_start_init", checkTriangleFormed_, checkTriangleFormed_start, checkTriangleFormed_init);
        checkTriangleFormed_.addEdge(checkTriangleFormed_start_init);
        ICFEdge checkTriangleFormed_init_d1 = new CFEdge("checkTriangleFormed_init_d1", checkTriangleFormed_, checkTriangleFormed_init, checkTriangleFormed_d1);
        checkTriangleFormed_.addEdge(checkTriangleFormed_init_d1);
        ICFEdge checkTriangleFormed_d1_then = new CFEdge("checkTriangleFormed_d1_then", checkTriangleFormed_, checkTriangleFormed_d1, checkTriangleFormed_then);
        checkTriangleFormed_.addEdge(checkTriangleFormed_d1_then);
        ICFEdge checkTriangleFormed_d1_else = new CFEdge("checkTriangleFormed_d1_else", checkTriangleFormed_, checkTriangleFormed_d1, checkTriangleFormed_else);
        checkTriangleFormed_.addEdge(checkTriangleFormed_d1_else);
        checkTriangleFormed_d1.setThenEdge(checkTriangleFormed_d1_then);
        checkTriangleFormed_d1.setElseEdge(checkTriangleFormed_d1_else);

        ICFEdge checkTriangleFormed_then_stop = new CFEdge("checkTriangleFormed_then_stop", checkTriangleFormed_, checkTriangleFormed_then, checkTriangleFormed_stop);
        checkTriangleFormed_.addEdge(checkTriangleFormed_then_stop);
        ICFEdge checkTriangleFormed_else_stop = new CFEdge("checkTriangleFormed_else_stop", checkTriangleFormed_, checkTriangleFormed_else, checkTriangleFormed_stop);
        checkTriangleFormed_.addEdge(checkTriangleFormed_else_stop);

        checkTriangleFormed_targets.add(checkTriangleFormed_d1_then);
        //checkTriangleFormed_targets.add(checkTriangleFormed_d1_else);

        Function checkTriangleFormed_f = new Function("checkTriangleFormed", checkTriangleFormed_, checkTriangleFormed_args, checkTriangleFormed_targets, Type.INT);
        all_functions.add(checkTriangleFormed_f);

        SymTest tester = new SymTest(mCFG, targets, all_functions);
        TestSequence seq = tester.generateTestSequence();
        System.out.println(seq);
        //Map<IIdentifier, List<Object>> test_seq = seq.getTestSequence();
        //System.out.println(test_seq);
    }
}
