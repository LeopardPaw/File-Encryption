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
			out.print("Enter a character:: ");
			char input = scan.next().charAt(0);
			Bit[] _value = Bit.bitValue(input);
			out.print("Enter an integer:: ");
			Bit[] key1 = Bit.bitValue(scan.nextShort());
			out.print("Enter an integer:: ");
			Bit[] key2 = Bit.bitValue(scan.nextShort());
			out.print("Enter an integer:: ");
			Bit[] key3 = Bit.bitValue(scan.nextShort());
			encipher(_value,key1,key2,key3);
			decipher(_value,key1,key2,key3);
			out.println("\t"+Bit.charValue(key1));
		/*	out.print("Enter an integer:: ");
		//	long input = Long.parseLong(scan.nextLine(),10);
		//	Bit[] key1 = Bit.bitValue(input);
		//	Bit[] _key1 = Bit.bitValue(input);
		//	out.print("Enter an integer:: ");
		//	input = Long.parseLong(scan.nextLine(),10);
		//	Bit[] key2 = Bit.bitValue(input);
		//	Bit[] _key2 = Bit.bitValue(input);
		//	out.print("Enter an integer:: ");
		//	input = Long.parseLong(scan.nextLine(),10);
		//	Bit[] key3 = Bit.bitValue(input);
		//	Bit[] _key3 = Bit.bitValue(input);
		//	encipher(key1,key2,key3);
		//	decipher(key1,key2,key3);
		//	if(Arrays.equals(key1,_key1) && Arrays.equals(key2,_key2) && Arrays.equals(key3,_key3))
		//		out.println("Success");
		//	else
		//	{
		//		out.println("Failure");
		//		out.println(Bit.inBinary(key1)+"---->"+Bit.inBinary(_key1));
		//		out.println(Bit.inBinary(key2)+"---->"+Bit.inBinary(_key2));
		//		out.println(Bit.inBinary(key3)+"---->"+Bit.inBinary(_key3));
		//	
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
			}*/
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
	public static void encipher(Bit[] _value,Bit[] key1,Bit[] key2, Bit[] key3)
	{
		Bit[] temp2 = Bit.XOR(key2,key3);
		_value = Bit.AND(Bit.XOR(_value,key1),temp2);
		Bit.rotateRightNoCarry(key2);
		key1 = Bit.AND(Bit.XOR(key1,key2),key3);
		Bit.rotateRightNoCarry(key3);
		key3 = Bit.XOR(key2,key3);
		Bit.rotateRightNoCarry(key3);
		
	}
	public static void decipher(Bit[] _value,Bit[] key1, Bit[] key2,Bit[] key3)
	{
		Bit.rotateLeftNoCarry(key2);
		Bit.rotateLeftNoCarry(key3);
		key3 = Bit.XOR(key2,key3);
		Bit.rotateLeftNoCarry(key3);
		key1 = Bit.special(Bit.XOR(key1,key2),key3);
		Bit[] temp1 = Bit.XOR(key2,key3);
		_value = Bit.special(Bit.XOR(_value,key1),temp1);
	}
}