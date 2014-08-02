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
			out.print("Enter a string:: ");
			String a=s.nextLine();
			String b=Bit.runlengthEncode(a);
			//Bit[] b=Bit.bitValue(a);
			//out.println("In binary:: "+Bit.inBinary(b));
			//out.println("Compressed:: "+Bit.inBinary((Bit.compressString(Bit.stringValue(b)))));
			//out.println("Your String:: "+Bit.stringValue(b));
			out.println("Encode:: "+b);
			out.println("Decode:: "+Bit.runlengthDecode(b));
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