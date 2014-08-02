package com.tigerwolf.encryption;
import static java.lang.System.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

class FileTest
{
	public static void main(String[] args)
	{
		Scanner fix=new Scanner(in); 
		String input="";
		while(true)
		{
			out.print("Enter file name:: ");
			input=fix.nextLine();
			try
			{
				Bit[] key1,key2,key3; //declares keys
				out.print("Enter new file name:: ");
				String newFile=fix.nextLine();
				out.print("Enter key1:: ");
				key1=Bit.bitValue(fix.next().charAt(0));
				out.print("Enter key2:: ");
				key2=Bit.bitValue(fix.next().charAt(0));
				out.print("Enter key3:: ");
				key3=Bit.bitValue(fix.next().charAt(0));
				//encipher(data,input,key1,key2,key3); 			//creates file 
				decipher(encipher(input,newFile,key1,key2,key3));			
			}
	
			catch(NumberFormatException e)
			{
				out.println("Incorrect number format");
			}
			
			catch(IOException e)
			{
				out.println("File:: "+input+" not found.");
			}
			finally
			{
				out.print("Continue? (Y/N) ");
				fix.nextLine();
				try
				{
					char x = fix.nextLine().charAt(0);
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

	public static File encipher(String inputFile,String newFile,Bit[] key1,Bit[] key2,Bit[] key3) throws IOException
	{
		ArrayList<Bit[]> buff=new ArrayList<>();
		File output = new File(newFile); //input == name of file
		//Original algorithm: (Input XOR key1) AND (key2 XOR key3)
		//New algorithm:: rotateRightNoCarry(input);XOR(input,key1);rotateLeftNoCarry(input);
		//			   XOR(input,key2);AND(input,key3);rotateRightNoCarry(input);
		//			   rotateRightNoCarry(input); 
		try(BufferedWriter outputWriter = new BufferedWriter(new FileWriter(output));BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile))))
		{
			while(true)
			{
				int tester = data.read();
				if(tester == -1)
					break;
				buff.add(Bit.bitValue(tester));
			}
			for(int x=0;x<buff.size();x++) 									//encrypts input
			{
				Bit[] bitArray = buff.get(x);
				Bit.rotateRightNoCarry(bitArray);
				bitArray = Bit.XOR(bitArray,key1);
				Bit.rotateLeftNoCarry(bitArray);
				bitArray = Bit.XOR(bitArray,key2);
				bitArray = Bit.AND(bitArray,key3);
				Bit.rotateRightNoCarry(bitArray);
				Bit.rotateRightNoCarry(bitArray);	
				buff.set(x,bitArray);
			}
			Bit.rotateRightNoCarry(key2);										//rotates key2 one position right
			outputWriter.write(Bit.charValue(key2));							//writes hidden key2 to output
			outputWriter.write(Bit.charValue(Bit.AND(Bit.XOR(key1,key2),key3)));	//writes hidden key1 to output
			Bit.rotateRightNoCarry(key3);										//rotates key3 one position right
			key3 = Bit.XOR(key2,key3);										//creates dependency on key2 for decryption
			Bit.rotateRightNoCarry(key3);										//rotates hidden key3 one position right
			outputWriter.write(Bit.charValue(key3));							//writes hidden key3 to output	
			for(Bit[] x : buff)												//writes to file
			{
				outputWriter.write(Bit.charValue(x));
			}
			return output;	
		}
		catch(FileNotFoundException e)
		{
			return null;
		}	
	}
	
	public static File decipher(File input)
	{
		File output=new File(input.getPath()+input.getName().substring(0,input.getName().lastIndexOf(".")+1)+"Decrypted"+input.getName().substring(input.lastIndexOf(File.PathSepastIndexOf(".")));
		ArrayList<Bit[]> buff=new ArrayList<>();
		try(BufferedReader outputTester=new BufferedReader(new InputStreamReader(new FileInputStream(input)));DataOutputStream outputWriter=new DataOutputStream(new FileOutputStream(output)))
		{
			Bit[] key2 = Bit.bitValue((short)outputTester.read());
			Bit.rotateLeftNoCarry(key2);
			Bit[] key1 = Bit.bitValue((short)outputTester.read());
			Bit[] key3 = Bit.bitValue((short)outputTester.read());
			Bit.rotateLeftNoCarry(key3);
			key3 = Bit.XOR(key2,key3);
			Bit.rotateLeftNoCarry(key3);
			key1 = Bit.special(Bit.XOR(key1,key2),key3);
			while(true)
			{
				try
				{
					buff.add(Bit.bitValue(outputTester.read()));
				}
				catch(EOFException e)
				{
					break;
				}
			}
			for(int x=0;x<buff.size();x++)
			{
				Bit[] bitArray = buff.get(x);
				Bit.rotateLeftNoCarry(bitArray);
				Bit.rotateLeftNoCarry(bitArray);
				bitArray = Bit.special(bitArray,key3);
				bitArray = Bit.XOR(bitArray,key2);
				Bit.rotateRightNoCarry(bitArray);
				bitArray = Bit.XOR(bitArray,key1);
				Bit.rotateLeftNoCarry(bitArray);
				outputWriter.writeChar(Bit.charValue(bitArray));
			}
		}
		catch(IOException e)
		{
			return output;
		}
		return output;
	}
}