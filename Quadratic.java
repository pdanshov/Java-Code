/*
 *  ==========================================================================
 *  This Java Program prompts a user for coefficients in a quadratic equation,
 *  and computes and prints the roots of the equation.
 *
 *  Written By : Nathan Blattau and Mark Austin                     July, 1997
 *  ==========================================================================
 */

import java.lang.Math;
import java.util.*;
import java.io.*;

class Quadratic {

    public static void main( String args[] ) {

    float fA,fB,fC;
    float fRoot1, fRoot2;
    float fDiscriminant;
    String sLine;

        // Print welcome message.

        System.out.println("Welcome to The Quadratic Equation Solver");
        System.out.println("----------------------------------------");

        // Prompt user for coefficients in quadratic equation.

        System.out.println("Please Enter coefficients for equation");
        System.out.println("a.x^2 + b.x + c");

        System.out.println("Coefficent a : ");
        sLine = keyboardInput();
        fA    = Float.valueOf(sLine).floatValue();

        System.out.println("Coefficent b : ");
        sLine = keyboardInput();
        fB    = Float.valueOf(sLine).floatValue();

        System.out.println("Coefficent c : ");
        sLine = keyboardInput();
        fC    = Float.valueOf(sLine).floatValue();

        // Print details of quadratic equation to screen.

        System.out.println("The equation you have entered is : ");
        System.out.println(+fA+".x^2 + "+fB+".x + "+fC);
    
        // Check for degenerate roots (i.e., fA = fB = zero).

        if ( fA==0 && fB==0 ) {
            System.out.println("Cannot solve " + fC +" = 0.0");
            return;
        } 

        if ( fA==0 && fB !=0 ) {
            fRoot1 = -fC/fB;
            System.out.println("Degenerate root : Root = "+ fRoot1);
            return;
        }

        // Compute discriminant of quadratic equation.

        fDiscriminant = fdiscriminant(fA,fB,fC);

        // Case for two real roots.

        if ( fDiscriminant >= 0.0 ) {

            fRoot1 = (float)(-fB/2.0/fA-(float)Math.sqrt(fDiscriminant) /
                              2.0 / fA );
            fRoot2 = (float)(-fB/2.0/fA+(float)Math.sqrt(fDiscriminant) /
                              2.0 / fA);

            System.out.println("Two real roots : Root1 : " + fRoot1);
            System.out.println("                 Root2 : " + fRoot2);
            return;
         }

         // Two complex roots

         fRoot1 = (float) (-fB/2.0/fA);
         fRoot2 = (float) (Math.sqrt(-fDiscriminant)/2.0/fA);

         System.out.println("Two complex roots");
         System.out.println("Root1 : " + fRoot1 + "+" + fRoot2 + "i");
         System.out.println("Root2 : " + fRoot1 + "-" + fRoot2 + "i");
    }

    /* 
     *  =============================================================
     *  Method fdiscriminant() : compute discriminant of quadratic
     *
     *  Input  : fA, fB, and fC -- coefficients in quadratic equation
     *  Output : float fReturn  -- discriminant of quadratic equation
     *  =============================================================
     */ 

    static float fdiscriminant(float fA, float fB, float fC) {
    float fReturn;

       fReturn= (float)(fB*fB-4.0*fA*fC);
       return fReturn;
    }

    /* 
     *  ===========================================================
     *  Method keyboardInput() : Get line of input from keyboard
     * 
     *  Input  : None.
     *  Output : String sLine -- character string of keyboard input
     *  ===========================================================
     */

    static String keyboardInput() {
    String sLine;

        DataInputStream in = new DataInputStream(System.in);
        try{
            sLine = in.readLine();
            return sLine;
        }
        catch (Exception e){
            return "error";
        }
    }
}
