package com.tigerwolf.encryption;
import java.util.Scanner;
import static java.lang.System.*;
class testBits
{
	public static void main(String[] args) throws java.io.IOException
	{
		Scanner s=new Scanner(in);
		while(true)
		{
			out.print("Enter an integer:: ");
			//String a=s.nextLine();
			//String b=Bit.runlengthEncode(a);
			Bit[] key1 = Bit.bitValue(s.nextLong());
			out.print("Enter an integer:: ");
			Bit[] key2 = Bit.bitValue(s.nextLong());
			Bit[] key3 = Bit.AND(key1,key2);
			//Bit[] b=Bit.bitValue(a);
			//out.println("In binary:: "+Bit.inBinary(b));
			//out.println("Compressed:: "+Bit.inBinary((Bit.compressString(Bit.stringValue(b)))));
			//out.println("Your String:: "+Bit.stringValue(b));
		//	out.println("Key3:: "+Bit.longValue(key3));
		//	out.println("Key2:: "+Bit.longValue(key2));
			out.println("key1:: "+Bit.longValue(key1));
			int y = 0;
			while(y++<32)
			{
				Bit.rotateLeftNoCarry(key1);
				Bit.rotateLeftNoCarry(key2);
				Bit.rotateLeftNoCarry(key3);
			//	out.println("Key3:: "+Bit.inBinary(key3));
			//	out.println("Key2:: "+Bit.inBinary(key2));
				out.println("key1:: "+Bit.inBinary(key1));
			}			
		//	out.println("Key3:: "+Bit.longValue(key3));
		//	out.println("Key2:: "+Bit.longValue(key2));
			out.println("key1:: "+Bit.longValue(key1));
			//out.println("Predicted key1:: "+Bit.longValue(Bit.special(key2,key3)));
			s.nextLine();
			out.print("Continue:: (Y/N) ");
			try
			{
				char x = s.nextLine().charAt(0);
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