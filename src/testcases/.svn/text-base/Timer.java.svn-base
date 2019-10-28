package testcases;

import java.util.Scanner;

public class Timer {

	public static void timer() {

		int start = 0;
		int x5, prev_start, prev_out, out, t_on;
		int c = 10;
		prev_start = 0;
		prev_out = 0;
		out = 0;
		Scanner sc = new Scanner(System.in);
		while (true) {
			start = sc.nextInt();
			if (prev_start - start == -1) {
				x5 = 1;
			} else {
				x5 = 0;
			}

			if (x5 == 1) {
				out = c;
			} else if (prev_out != 0) {
				out = prev_out - 1;
			} else {
				out = prev_out;
			}
			if (prev_out != out) {
				t_on = 1;
			} else {
				t_on = 0;
			}
			System.out.println(x5 + "  " + out + "  " + t_on);
			prev_start = start;
			prev_out = out;
		}
	}

	public static void main(String[] args) {

		timer();
	}

}
