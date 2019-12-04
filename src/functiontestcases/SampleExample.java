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

import java.util.*;

public class SampleExample {

    public static void main(String[] args) {
        try {
            SampleCodeTest();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sample() {
        Scanner sc = new Scanner(System.in);
        int n, newval, sum, i;
        n = sc.nextInt();
        i = sc.nextInt();
        sum = 0;
        if(i < n) {
            newval = calc(i);
            sum = sum + newval;
        }
        newval = calc(n + 2);
        sum = sum + newval;
    }

    public static int calc(int i) {
        if(i <= 10) {
            return i + 1;
        }
        else {
            return i - 1;
        }
    }

    public static void SampleCodeTest() throws Exception{
        //create Main CFG
        System.out.println("Test Case Start\n\n");

        ICFG mCFG = null;
        Set<ICFEdge> targets = new LinkedHashSet<ICFEdge>();
        CFGBasicBlockNode start = new CFGBasicBlockNode("start",null);
        CFGBasicBlockNode stop = new CFGBasicBlockNode("stop", null);
        mCFG = new CFG(start, stop);

        ICFGBasicBlockNode init = new CFGBasicBlockNode("init", mCFG);
        Variable n = new Variable("n", mCFG);
        Variable i = new Variable("i", mCFG);
        Variable sum = new Variable("sum", mCFG);
        init.addStatement(new Statement(mCFG, n, new Input(mCFG)));
        init.addStatement(new Statement(mCFG, i, new Input(mCFG)));
        init.addStatement(new Statement(mCFG, sum, new ConcreteConstant(0, mCFG)));
        mCFG.addBasicBlockNode(init);

        LesserThanExpression exp = new LesserThanExpression(mCFG, i, n);
        ICFGDecisionNode D1 = new CFGDecisionNode("D1",mCFG, exp);
        mCFG.addDecisionNode(D1);

        ICFGBasicBlockNode d1then = new CFGBasicBlockNode("d1then", mCFG);
        Variable newval = new Variable("newval", mCFG);
        Set<IExpression> actual_args = new LinkedHashSet<IExpression>();
        actual_args.add(i);
        d1then.addStatement(new Statement(mCFG, newval, new FunctionCallExpression(mCFG, "calc", actual_args, Type.INT)));
        d1then.addStatement(new Statement(mCFG, sum, new AddExpression(mCFG, sum, newval)));
        mCFG.addBasicBlockNode(d1then);

        ICFGBasicBlockNode basic = new CFGBasicBlockNode("basic", mCFG);
        Set<IExpression> actual_args2 = new LinkedHashSet<IExpression>();
        actual_args2.add(new AddExpression(mCFG, n, new ConcreteConstant(2, mCFG)));
        basic.addStatement(new Statement(mCFG, newval, new FunctionCallExpression(mCFG, "calc", actual_args2, Type.INT)));
        basic.addStatement(new Statement(mCFG, sum, new AddExpression(mCFG, sum, newval)));
        mCFG.addBasicBlockNode(basic);

        ICFEdge start_init = new CFEdge("start_init", mCFG, start, init);
        mCFG.addEdge(start_init);
        ICFEdge init_D1 = new CFEdge("init_D1", mCFG, init, D1);
        mCFG.addEdge(init_D1);
        ICFEdge D1_d1then = new CFEdge("D1_d1then", mCFG, D1, d1then);
        mCFG.addEdge(D1_d1then);
        ICFEdge D1_basic = new CFEdge("D1_basic", mCFG, D1, basic);
        mCFG.addEdge(D1_basic);
        ICFEdge d1then_basic = new CFEdge("d1then_basic", mCFG, d1then, basic);
        mCFG.addEdge(d1then_basic);
        D1.setThenEdge(D1_d1then);
        D1.setElseEdge(D1_basic);
        ICFEdge basic_stop = new CFEdge("basic_stop", mCFG, basic, stop);
        mCFG.addEdge(basic_stop);

        //ICFEdge loop = new CFEdge("stop_start", mCFG, stop, start);
        //mCFG.addEdge(loop);

        targets.add(D1_d1then);
        //targets.add(D1_basic);

        //calc CFG creating
        ICFG calc_CFG = null;
        Set<ICFEdge> calc_targets = new LinkedHashSet<ICFEdge>();
        Set<IIdentifier> formal_args = new LinkedHashSet<IIdentifier>();
        ICFGBasicBlockNode calc_start = new CFGBasicBlockNode("calc_start", null);
        ICFGBasicBlockNode calc_stop = new CFGBasicBlockNode("calc_stop", null);
        calc_CFG = new CFG(calc_start, calc_stop);

        Variable calc_i = new Variable("calc_i", calc_CFG);
        Variable calc_a = new Variable("calc_a", calc_CFG);
        BooleanVariable calc_b = new BooleanVariable("calc_b", calc_CFG);
        Variable return_value = new Variable("return_value", calc_CFG);
        LesserThanEqualToExpression exp2 = new LesserThanEqualToExpression(calc_CFG, calc_i, new ConcreteConstant(10, calc_CFG));
        ICFGDecisionNode calc_d1 = new CFGDecisionNode("d1", calc_CFG, exp2);
        calc_CFG.addDecisionNode(calc_d1);

        ICFGBasicBlockNode then = new CFGBasicBlockNode("then", calc_CFG);
        then.addStatement(new Statement(calc_CFG, return_value, new AddExpression(calc_CFG, calc_i, new ConcreteConstant(1, calc_CFG))));
        calc_CFG.addBasicBlockNode(then);

        ICFGBasicBlockNode else_node = new CFGBasicBlockNode("else", calc_CFG);
        else_node.addStatement(new Statement(calc_CFG, return_value, new SubExpression(calc_CFG, i, new ConcreteConstant(1, calc_CFG))));
        calc_CFG.addBasicBlockNode(else_node);

        ICFEdge start_d1 = new CFEdge("start_d1", calc_CFG, calc_start, calc_d1);
        calc_CFG.addEdge(start_d1);
        ICFEdge d1_then = new CFEdge("d1_then", calc_CFG, calc_d1, then);
        mCFG.addEdge(d1_then);
        ICFEdge d1_else = new CFEdge("d1_else", calc_CFG, calc_d1, else_node);
        mCFG.addEdge(d1_else);
        calc_d1.setThenEdge(d1_then);
        calc_d1.setElseEdge(d1_else);
        ICFEdge then_stop = new CFEdge("then_stop", calc_CFG, then, calc_stop);
        mCFG.addEdge(then_stop);
        ICFEdge else_stop = new CFEdge("else_stop", calc_CFG, else_node, calc_stop);
        mCFG.addEdge(else_stop);

        calc_targets.add(d1_then);
        calc_targets.add(d1_else);

        formal_args.add(calc_i);
        Function calc = new Function("calc", calc_CFG, formal_args, calc_targets, Type.INT);
        Set<Function> all_functions = new LinkedHashSet<Function>();
        all_functions.add(calc);
        SymTest tester = new SymTest(mCFG, targets, all_functions);
        TestSequence seq = tester.generateTestSequence();
        System.out.println(seq);
        Map<IIdentifier, List<Object>> test_seq = seq.getTestSequence();
        System.out.println(test_seq);

    }
}
