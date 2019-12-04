package testcases;

import com.sun.deploy.util.SyncAccess;

import java.util.Scanner;

public class CruiseControl {

    public static void control() {
        //CFGBasicBlockNode start, stop
        //CFGDecisionNode

        Scanner sc = new Scanner(System.in);

        //variables
        boolean x3, enable, brake, set, x1, x8, x2;
        int x6, PIC_DTI_x3, speed, inc, dec, x9, x10, x7, x4, x5, error, PIC_x1, PIC_x3, throttle;

        while(true) {

            //statements in init
            x3 = false;
            x6 = 0;
            PIC_DTI_x3 = 0;

            //statements in mainloop

            //statements in inputs
            enable = sc.nextBoolean();
            brake = sc.nextBoolean();
            speed = ExampleTry.example();
            set = sc.nextBoolean();
            inc = sc.nextInt();
            dec = sc.nextInt();

            //statements in fp1
            x1 = !brake;
            x8 = set || x3;
            x2 = enable && ( x1 && x8 );
            x9 = x6 + 1;
            x10 = x6 + 1;

            //decision in d1
            if( dec != 0 ){
                //statements in d1then
                x7 = x10;
            }
            else{
                //statements in d1else
                x7 = x6;
            }

            //decision in d2
            if( inc != 0 ){
                //statements in d2then
                x4 = x9;
            }
            else{
                //statements in d2else
                x4 = x7;
            }

            //decision in d3
            if( set != false ){
                //statements in d3then
                x5 = speed;
            }
            else{
                //statements in d3else
                x5 = x4;
            }

            //statements in fp2
            error = x5 - speed;
            PIC_x1 = 2 * error;
            PIC_x3 = error + PIC_DTI_x3;
            throttle = PIC_x1 + PIC_x3;

            //statements in delay
            PIC_DTI_x3 = PIC_x3;
            x6 = x5;
            x3 = x2;

        }
    }

    public static void main(String[] args) {
        control();
    }

}
