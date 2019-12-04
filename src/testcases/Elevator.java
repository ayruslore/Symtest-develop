package testcases;

import java.util.Scanner;

public class Elevator {

    public static void elevator() {
        //BasicBlockNode A, W, B, J, G, I, M, O, P, R, T, V
        //Decision Node C, E, F, H, K, L, N, Q, S, U

        int constant1, constant2, constant3, floorinput, shouldStop, direction, checkMax, checkMin, Request, position, min, max;

        Scanner sc = new Scanner(System.in);

        // statements in B
        constant1 = -1; //concrete constant
        constant2 = 0; //concrete constant
        constant3 = 1; //concrete constant
        floorinput = sc.nextInt(); //floorInput, i1 , stmt1
        shouldStop = sc.nextInt(); //shouldStop, i2, stmt2
        direction = sc.nextInt(); //direction, i3, stmt4
        checkMax = sc.nextInt(); //checkMax, i4, stmt6
        checkMin = sc.nextInt(); //checkMin, i5, stmt7
        Request = sc.nextInt(); //req1, i6, stmt19
        position = sc.nextInt(); //position, i7, stmt21
        min = 10; //min, stmt22
        max = constant3; //max, stmt23

        //decision in C
        if( floorinput > constant1 && 11 > floorinput ) { //exp1 = gtexp and gtexp2
            //decision in E
            if( shouldStop == constant2 ) { //exp2
                //decision in F
                if( floorinput > 5 ) { //exp3
                    // statements in G
                    direction = constant3; //stmt8
                    checkMax = constant3; //stmt9
                    max = floorinput; //stmt10
                }
                else {
                    //decision in H
                    if( 5 > floorinput ) { //exp4
                        // statements in J
                        shouldStop = constant1; //stmt3
                        direction = constant2; //stmt5
                    }
                    else {
                        // statements in I
                        direction = constant1; //stmt11
                        checkMin = constant3; //stmt12
                        min = floorinput; //stmt13
                    }
                }
            }
            else{
                //decision in K
                if( shouldStop > 2 ) { //exp5
                    //decision in L
                    if( floorinput > position ) { //exp6
                        //statements in P
                        checkMin = constant3; //stmt16
                        //decision in Q
                        if( min > floorinput ) { //exp8
                            //statements in R
                            min = floorinput; //stmt17
                        }
                    }
                    else {
                        // statements in M
                        checkMax = constant3; //stmt14
                        //decision in N
                        if( floorinput > max ) { //exp7
                            //statements in O
                            max = floorinput; //stmt15
                        }
                    }
                }
            }
            //decision in S
            if( Request == constant2 ) { //exp9
                //statements in T
                shouldStop = shouldStop + constant3;// exp, stmt18
                //decision in U
                if( shouldStop == constant2 ) { //exp10
                    //statements in V
                    Request = constant3; //stmt20
                }
            }
        }
        //end node W
    }


    public static void main(String[] args) {

        elevator();
    }

}
