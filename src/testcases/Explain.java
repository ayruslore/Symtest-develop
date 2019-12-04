package testcases;

import java.util.Scanner;

public class Explain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int newval;
        int sum = 0;
        int i = sc.nextInt();
        if( i < n ) {
            newval = calc(i);
            sum = sum + newval;
        }
        newval = calc(i);
        sum = sum + newval;
        System.out.println(sum);
    }

    public static int calc(int i) {
        if (i <= 10) {
            return i + 1;
        }
        else {
            return i - 1;
        }
    }
}
