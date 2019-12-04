package testcases;

import java.util.Scanner;

public class MatlabExample {
    public static void Matlab(){
        //CFGBasicBlockNode A, Y, C, X, W, U, S, R, Q, O, N, L, K, I, H, F, E
        //CFGDecisionNode D, G, J, M, P, T, V

        Scanner sc = new Scanner(System.in);
        int constant1, constant2, constant3, constant4, constant5, constant7, constant8, trueconstant, falseconstant;
        constant1 = 5;
        constant2 = 10;
        constant3 = 15;
        constant4 = 20;
        constant5 = 25;
        constant7 = 35;
        constant8 = 40;
        trueconstant = 1;
        falseconstant = 0;

        //variables
        int v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v14;
        int i1, i2, i3, i4;


        //statements in C
        i1 = sc.nextInt(); //ip1
        i2 = sc.nextInt(); //ip2
        i3 = sc.nextInt(); //ip3
        i4 = sc.nextInt(); //ip4

        //decision in D
        if( i1 == constant1 ){ //exp1
            //statements in E
            v1 = trueconstant;
        }
        else{
            //statements in F
            v1 = falseconstant;
        }

        //decision in G
        if( i3 == constant2 ){ //exp2
            //statements in H
            v3 = trueconstant;
        }
        else{
            //statements in I
            v3 = falseconstant;
        }

        //decision in J
        if( i2 == constant3 ){ //exp3
            //statements in K
            v4 = trueconstant;
        }
        else{
            //statements in L
            v4 = falseconstant;
        }

        //decision in M
        if( i2 == constant4 ){ //exp4
            //statements in N
            v5 = trueconstant;
        }
        else{
            //statements in O
            v5 = falseconstant;
        }

        //decision in P
        if( i3 == i4 ){ //exp5
            //statements in R
            v2 = falseconstant;
        }
        else{
            //statements in Q
            v2 = trueconstant;
        }

        //statements in S
        v6 = v4 + v5;
        v7 = v2 + v3 + v6;
        v9 = v6 + trueconstant;
        v10 = v1 + v7;

        //decision in T
        if( v10 > trueconstant ){ //exp6
            //statements in U
            v14 = constant5;
        }
        else{
            //decision in V
            if( v9 > trueconstant ){ //exp7
                //statements in W
                v14 = constant7;
            }
            else{
                //statements in X
                v14 = constant8;
            }
        }

    }

    public static void main(String[] args) {
        Matlab();
    }
}
