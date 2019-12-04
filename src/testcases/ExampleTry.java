package testcases;

import cfg.ICFEdge;
import cfg.ICFG;
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

public class ExampleTry {

    public static int example() {
        Scanner sc = new Scanner(System.in);

        int x, z, r;

        x = sc.nextInt();
        z = sc.nextInt();
        r = sc.nextInt();

        x = 2 * x;

        if( x > 100 ) {
            if( z == 1 ) {
                r = 3;
            }
        }
        if( r > 1 ) {
            z = r - 1;
        }

        return z;

    }

    public static void testexample1() throws Exception {
        ICFG mCFG = null;
        CFGBasicBlockNode start = new CFGBasicBlockNode("start", null);
        CFGBasicBlockNode stop = new CFGBasicBlockNode("stop", null);
        mCFG = new CFG(start, stop);

        Variable x = new Variable("x", mCFG);
        Variable z = new Variable("z", mCFG);
        Variable r = new Variable("r", mCFG);

        CFGBasicBlockNode init = new CFGBasicBlockNode("init", mCFG);
        Input i1 = new Input(mCFG);
        init.addStatement(new Statement(mCFG, x, i1));
        Input i2 = new Input(mCFG);
        init.addStatement(new Statement(mCFG, z, i2));
        Input i3 = new Input(mCFG);
        init.addStatement(new Statement(mCFG, r, i3));
        mCFG.addBasicBlockNode(init);

        CFGBasicBlockNode basic = new CFGBasicBlockNode("basic", mCFG);
        basic.addStatement(new Statement(mCFG, x, new MulExpression(mCFG, x, new ConcreteConstant(2, mCFG))));
        mCFG.addBasicBlockNode(basic);

        GreaterThanExpression exp1 = new GreaterThanExpression(mCFG, x, new ConcreteConstant(100, mCFG));
        CFGDecisionNode d1 = new CFGDecisionNode("d1", mCFG, exp1);
        mCFG.addDecisionNode(d1);

        EqualsExpression exp2 = new EqualsExpression(mCFG, z, new ConcreteConstant(1, mCFG));
        CFGDecisionNode d2 = new CFGDecisionNode("d2", mCFG, exp2);
        mCFG.addDecisionNode(d2);

        CFGBasicBlockNode d2then = new CFGBasicBlockNode("d2then", mCFG);
        d2then.addStatement(new Statement(mCFG, r, new ConcreteConstant(3, mCFG)));
        mCFG.addBasicBlockNode(d2then);

        GreaterThanExpression exp3 = new GreaterThanExpression(mCFG, r, new ConcreteConstant(1, mCFG));
        CFGDecisionNode d3 = new CFGDecisionNode("d3", mCFG, exp3);
        mCFG.addDecisionNode(d3);

        CFGBasicBlockNode d3then = new CFGBasicBlockNode("d3then", mCFG);
        d3then.addStatement(new Statement(mCFG, z, new SubExpression(mCFG, r, new ConcreteConstant(1, mCFG))));
        mCFG.addBasicBlockNode(d3then);

        ICFEdge start_init = new CFEdge("start_init", mCFG, start, init);
        ICFEdge init_basic = new CFEdge("init_basic", mCFG, init, basic);
        ICFEdge basic_d1 = new CFEdge("basic_d1", mCFG, basic, d1);
        ICFEdge d1_d2_then = new CFEdge("d1_d2_then", mCFG, d1, d2);
        ICFEdge d2_d2then_then = new CFEdge("d2_d2then_then", mCFG, d2, d2then);
        ICFEdge d1_d3_else = new CFEdge("d1_d3_else", mCFG, d1, d3);
        ICFEdge d2_d3_else = new CFEdge("d2_d3_else", mCFG, d2, d3);
        ICFEdge d2then_d3 = new CFEdge("d2then_d3", mCFG, d2then, d3);
        ICFEdge d3_d3then_then = new CFEdge("d3_d3then_then", mCFG, d3, d3then);
        ICFEdge d3then_stop = new CFEdge("d3then_stop", mCFG, d3then, stop);
        ICFEdge d3_stop_else = new CFEdge("d3_stop_else", mCFG, d3, stop);

        //ICFEdge stop_start = new CFEdge("stop_start", mCFG, stop, start);

        mCFG.addEdge(start_init);
        mCFG.addEdge(init_basic);
        mCFG.addEdge(basic_d1);
        mCFG.addEdge(d1_d2_then);
        mCFG.addEdge(d2_d2then_then);
        mCFG.addEdge(d1_d3_else);
        mCFG.addEdge(d2_d3_else);
        mCFG.addEdge(d2then_d3);
        mCFG.addEdge(d3_d3then_then);
        mCFG.addEdge(d3then_stop);
        mCFG.addEdge(d3_stop_else);
        //mCFG.addEdge(stop_start);

        d1.setThenEdge(d1_d2_then);
        d1.setElseEdge(d1_d3_else);
        d2.setThenEdge(d2_d2then_then);
        d2.setElseEdge(d2_d3_else);
        d3.setThenEdge(d3_d3then_then);
        d3.setElseEdge(d3_stop_else);

        Set<ICFEdge> targets = new LinkedHashSet<ICFEdge>();//mCFG.getEdgeSet();
        targets.add(init_basic);
        /*targets.remove(d1_d3_else);
        targets.remove(d3_stop_else);
        targets.remove(d2_d2then_then);
        targets.remove(d2then_d3);*/

        SymTest st = new SymTest(mCFG, targets, new LinkedHashSet<Function>());
        TestSequence seq = st.generateTestSequence();
        System.out.println(seq);
        Map<IIdentifier, List<Object>> testseq = seq.getTestSequence();

    }

    public static void main(String[] args) {
        //int a = example();
        //System.out.println(a);

        try{
            testexample1();
        }
        catch (Exception e){
            System.out.println("Exception Occurred");
        }

    }
}
