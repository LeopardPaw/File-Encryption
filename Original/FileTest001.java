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
		//BufferedReader data;
		Scanner fix=new Scanner(in); 
		String input="";
		while(true)
		{
			input="";
			out.print("Enter file name:: ");
			input=fix.nextLine();
			try(BufferedReader data=new BufferedReader(new InputStreamReader(new FileInputStream(input))))
			{
				Bit[] key1,key2,key3;
				String holder=""; //used to hold key values
				//input="";
				//out.print("Enter file name:: ");
				//input=fix.nextLine();
				//data=new BufferedReader(new InputStreamReader(new FileInputStream(input)));
				out.print("Enter new file name:: ");
				input=fix.nextLine();
				out.print("Enter key1:: ");
				holder=Integer.toBinaryString(fix.nextInt());
				key1=assign(holder);
				out.print("Enter key2:: ");
				holder=Integer.toBinaryString(fix.nextInt());
				key2=assign(holder);
				out.print("Enter key3:: ");
				holder=Integer.toBinaryString(fix.nextInt());
				key3=assign(holder);
				//encipher(data,input,key1,key2,key3); //creates file 
				//decipher(encipher(data,input,key1,key2,key3));			
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
				char x = fix.next().charAt(0);
				fix.nextLine();
				if(x!='Y'&&x!='y')
					return;
				out.println();
			}
		}
	}

	public static File encipher(BufferedReader data,String input,Bit[] key1,Bit[] key2,Bit[] key3)
	{
		ArrayList<Bit> buff=new ArrayList<>();
		File output = new File(input); //input == name of file
		DataOutputStream outputWriter;
		try
		{
			outputWriter = new DataOutputStream(new FileOutputStream(output));
		}
		catch(FileNotFoundException e)
		{
			return null;
		} 
		try
		{
			Bit[] key4=Bit.or(key3;	//convenience
			//key4.or(key2);		//"	"
			while(true)
			{
				BitSet key5=(BitSet)key1.clone();
				key5.xor(key2);											//used to hide key1
				key3.xor(key5);											//used to hide key3
				outputWriter.writeByte((int)(key2.toLongArray())[0]);	//writes key2 to output
				outputWriter.writeByte((int)(key1.toLongArray())[0]);	//writes hidden key1 to output
				outputWriter.writeByte((int)(key3.toLongArray())[0]);	//writes hidden key3 to output	
				break;
			}
			while(true)
			{
				buff.add(BitSet.valueOf(new byte[]{new Integer(data.read()).byteValue()}));
				if (buff.get(buff.size()-1)==(BitSet.valueOf(new byte[]{new Integer(-1).byteValue()})));
				{
					buff.remove(buff.size()-1);
					break;
				}
			}

			for(BitSet x:buff)
			{
				x.xor(key1);
				x.and(key4);	
				outputWriter.writeByte((int)(x.toLongArray())[0]);
			}
		}
		catch(IOException e)
		{
			return output;
		}
		return output;
	}
	
	public static File decipher(File input)
	{
		File output=new File(input.getPath()+input.getName().substring(0,input.getName().lastIndexOf("."))+"Decrypted");
		ArrayList<BitSet> buff=new ArrayList<>();
		BufferedReader outputTester;
		DataOutputStream outputWriter;
		BitSet key1=new BitSet(8),key2=new BitSet(8),key4=new BitSet(8);
		try
		{
			outputTester=new BufferedReader(new InputStreamReader(new FileInputStream(input)));
			outputWriter=new DataOutputStream(new FileOutputStream(output));
		}
		catch(FileNotFoundException e)
		{
			out.println("This should never happen...");
			return null;
		}
		try
		{
			key2=BitSet.valueOf(new byte[] {new Integer(outputTester.read()).byteValue()});
			key1=BitSet.valueOf(new byte[] {new Integer(outputTester.read()).byteValue()});
			key4=BitSet.valueOf(new byte[] {new Integer(outputTester.read()).byteValue()});
			key4.xor(key1);
			key1.xor(key2);
			key4.or(key2);
			while(true)
			{
				buff.add(BitSet.valueOf(new byte[]{new Integer(outputTester.read()).byteValue()}));
				if (buff.get(buff.size()-1)==(BitSet.valueOf(new byte[]{new Integer(-1).byteValue()})));
				{
					buff.remove(buff.size()-1);
					break;
				}
			}
			for(BitSet x:buff)
			{
				BitSet fix=(BitSet)x.clone();
				BitSet fix2=(BitSet)x.clone();
				int inc=0;
				while(inc<x.size())
				{
					if(!x.get(inc) && !fix.get(inc))
						fix2.set(inc,false);
					fix2.set(inc,!(x.get(inc)^fix.get(inc)));
					inc++;
				}
				outputWriter.writeByte((int)(x.toLongArray())[0]);
			}
		}
		catch(IOException e)
		{
			return output;
		}
		return output;
	}
	private static Bit[] assign (String s)
	{
		Bit[] key;
		int index=s.indexOf("1");
		if(index==-1)
			return key= new Bit[]{new Bit(false)};
		s=s.substring(index,s.length());
		key=new Bit[s.length()];
		for(index=0;index<key.length;index++)
		{
			key[index]= (s.substring(index,index+1).equals("1")) ? new Bit(true) : new Bit(false);
		}
		return key;
	}
}
