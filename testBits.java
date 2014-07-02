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
			long input = Long.parseLong(scan.nextLine(),10);
			Bit[] out1 = Bit.bitValue(input);
			Bit[] out2 = Bit.bitValue(input);
			out.println("Original:: "+Bit.inBinary(out1));
			//Bit.rotateRightNoCarry(out2);
			//out.println(Bit.inBinary(out2));
			//out.print("Enter an integer:: ");
			//input=Long.parseLong(scan.nextLine(),10);
			//Bit[] out2 = Bit.bitValue(input);
			//Bit[] out3 = Bit.AND(out1,out2);
			//out.println(Bit.inBinary(out1)+"\nAND\n"+Bit.inBinary(out2)+"\n------------\n"+Bit.inBinary(out3));
			//Bit[] out4 = Bit.special(out2,out3);
			//Bit[] out5 = Bit.special(out1,out3);
			//Bit[] out3 = out2;
			Bit.rotateLeftNoCarry(out2);
			out.println(Bit.inBinary(out2));
		//	if(Arrays.equals(out1,out2))
		//		out.println("Success");
		//	else
		//	{
		//		out.println("Failure");
		//		out.println(Bit.inBinary(out1)+"\n"+Bit.inBinary(out2));
		//	}
		//	out.println("Number:: "+input);
		//	out.println("Unsigned binary:: "+Long.toBinaryString(input));
		//	out.println("In binary:: "+Bit.inBinary(out1));
		//	try
		//	{
		//		out.println("Back to base 10:: "+Bit.byteValue(out1));
		//	}
		//	catch(NumberFormatException e)
		//	{
		//		try
		//		{
		//			out.println("Back to base 10:: "+Bit.shortValue(out1));
		//		}
		//		catch(NumberFormatException f)
		//		{
		//			try
		//			{
		//				out.println("Back to base 10:: "+Bit.intValue(out1));
		//			}
		//			catch(NumberFormatException g)
		//			{
		//				try
		//				{
		//					out.println("Back to base 10:: "+Bit.longValue(out1));
		//				}
		//				catch(NumberFormatException h)
		//				{
		//					out.println("This should never happen."); //overflow?
		//				}
		//			}
		//		}
		//	}
			out.print("Continue? (Y/N) ");
			try
			{
				char x = scan.nextLine().charAt(0);
				if(x!='Y'&&x!='y')
					return;
				out.println();
			}
			catch(StringIndexOutOfBoundsException e)
			{
				return;
			}
		}
	}
}