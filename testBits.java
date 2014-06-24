package com.tigerwolf.encryption;
import java.util.Scanner;
import static java.lang.System.in;
import static java.lang.System.out;
import java.util.InputMismatchException;
import java.lang.Boolean;
import java.util.Arrays;
import java.lang.Integer;

class Test
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(in);
		while(true)
		{
			out.print("Enter an integer:: ");
			long input = scan.nextLong();
			Bit[] out1 = Bit.bitValue(input);
			out.println("Number:: "+input);
			out.println("In binary:: "+Bit.inBinary(out1));
			out.println("Back to base 10:: "+Bit.intValue(out1));
			scan.nextLine();
			out.print("Continue? (Y/N) ");
			char x = scan.nextLine().charAt(0);
			if(x!='Y'&&x!='y')
				return;
			out.println();
			
		}
	}
}
