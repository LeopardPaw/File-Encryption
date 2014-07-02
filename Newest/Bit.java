package com.tigerwolf.encryption;
import static java.lang.Math.*;
import java.lang.Comparable;
import java.lang.String;
import java.lang.Class;
import java.lang.Boolean;
import java.lang.System;
import java.lang.SecurityException;
import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;
import java.lang.NumberFormatException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

//This class is used to redefine Boolean indirectly
//Adds Bitwise operations to class
public final class Bit implements Serializable, Comparable<Bit>
{
	public static final Class<Boolean> TYPE = Boolean.TYPE;
	public static final Bit TRUE = new Bit(true);
	public static final Bit FALSE = new Bit(false);
	private final boolean value;
	public Bit(boolean value)
	{
		this.value = value;
	}
	public Bit(String s)
	{
		this(toBoolean(s));
	}
	public Bit(Boolean b)
	{
		value=b.booleanValue();
	}
	public static boolean parseBoolean(String s)
	{
		return toBoolean(s);
	}
	public boolean booleanValue()
	{
		return value;
	}
	public static Bit valueOf(boolean b)
	{
		return (b ? TRUE : FALSE);
	}
	public static Bit valueOf(String s)
	{
		return toBoolean(s) ? TRUE : FALSE;
	}
	public static String toString(boolean b)
	{
		return b ? "true" : "false";
	}
	public static String inBinary(Bit[] b)
	{
		return toBinaryString(b);
	}
	public String toString()
	{
		return value ? "true" : "false";
	}
	public int hashCode()
	{
		return value ? 1231 : 1237;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof Bit)
		{
			return value == ((Bit)obj).booleanValue();
		}
		if(obj instanceof Boolean)
		{
			return value == ((Bit)obj).booleanValue();
		}
		return false;
	}
	public static boolean getBoolean(String name)
	{
		boolean result = false;
		try
		{
			result = toBoolean(System.getProperty(name));
		}
		catch(SecurityException e)
		{
		}
		catch(NullPointerException e)
		{
		}
		catch(IllegalArgumentException e)
		{
		}
		return result;
	}
	public int compareTo(Bit b)
	{
		return compare(this.value,b.value);
	}
	public int compareTo(Boolean b)
	{
		return compare(this.value,b.booleanValue());
	}
	public static int compare(boolean x,boolean y)
	{
		return (x == y) ? 0 : (x ? 1 : -1);
	}
	public static int compare(Bit[] x, Bit[] y)
	{
		return (Arrays.hashCode(x) == Arrays.hashCode(y)) ? 0 : ((Arrays.hashCode(x) > Arrays.hashCode(y)) ? 1 : -1);
	}
	private static boolean toBoolean(String name)
	{
		return (name.equalsIgnoreCase("true")) ? true : false;
	}
	public static Bit AND(Bit x, Bit y)
	{
		return AND(x.booleanValue(),y.booleanValue());
	}
	public static Bit AND(Bit x, boolean y)
	{
		return AND(x.booleanValue(),y);
	}
	public static Bit AND(boolean x, Bit y)
	{
		return AND(x,y.booleanValue());
	}
	public static Bit AND(boolean x, boolean y)
	{
		return (x & y) ? TRUE : FALSE;
	}
	public static Bit[] AND(Bit[] x, Bit[] y)
	{
		if(x.length<y.length)
			SignExtension(x,y.length);
		if(y.length<x.length)
			SignExtension(y,x.length);
		Bit[] output = new Bit[x.length];
		for(int bit = 0; bit<output.length;bit++)
		{
			output[bit] = AND(x[bit],y[bit]);
		}
		return output;
	}
	public static Bit XOR(Bit x, Bit y)
	{
		return XOR(x.booleanValue(),y.booleanValue());
	}
	public static Bit XOR(Bit x, boolean y)
	{
		return XOR(x.booleanValue(),y);
	}
	public static Bit XOR(boolean x, Bit y)
	{
		return XOR(x,y.booleanValue());
	}
	public static Bit XOR(boolean x, boolean y)
	{
		return (x ^ y) ? TRUE : FALSE;
	}
	public static Bit[] XOR (Bit[] x, Bit[] y)
	{
		if(x.length<y.length)
			SignExtension(x,y.length);
		if(y.length<x.length)
			SignExtension(y,x.length);
		Bit[] output = new Bit[x.length];
		for(int bit = 0; bit<output.length;bit++)
		{
			output[bit] = XOR(x[bit],y[bit]);
		}
		return output;
	}
	public static Bit OR(Bit x, Bit y)
	{
		return (x.booleanValue() | y.booleanValue()) ? TRUE : FALSE;
	}
	public static Bit OR(Bit x, boolean y)
	{
		return (x.booleanValue() | y) ? TRUE : FALSE;
	}
	public static Bit OR(boolean x, Bit y)
	{
		return (x | y.booleanValue()) ? TRUE : FALSE;
	}
	public static Bit OR(boolean x, boolean y)
	{
		return (x | y) ? TRUE : FALSE;
	}
	public static Bit[] OR(Bit[] x, Bit[] y)
	{
		if(x.length<y.length)
			SignExtension(x,y.length);
		if(y.length<x.length)
			SignExtension(y,x.length);
		Bit[] output = new Bit[x.length];
		for(int bit = 0; bit<output.length;bit++)
		{
			output[bit] = OR(x[bit],y[bit]);
		}
		return output;
	}
	public static Bit NOT(Bit x)
	{
		return x.booleanValue() ? FALSE : TRUE;
	}
	public static Bit NOT(boolean x)
	{
		return x ? FALSE : TRUE;
	}
	public static Bit[] NOT(Bit[] x)		//Complement(~) of bit array
	{
		Bit[] output = new Bit[x.length];
		for(int bit = 0; bit<output.length;bit++)
		{
			output[bit] = NOT(x[bit]);
		}
		return output;
	}
	public static Bit NAND(Bit x, Bit y)
	{
		return NAND(x.booleanValue(),y.booleanValue());
	}
	public static Bit NAND(Bit x, boolean y)
	{
		return NAND(x.booleanValue(),y);
	}
	public static Bit NAND(boolean x, Bit y)
	{
		return NAND(x,y.booleanValue());
	}
	public static Bit NAND(boolean x, boolean y)
	{
		return NOT(x&y);
	}
	public static Bit[] NAND(Bit[] x,Bit[] y)
	{
		if(x.length<y.length)
			SignExtension(x,y.length);
		if(y.length<x.length)
			SignExtension(y,x.length);
		Bit[] output = new Bit[x.length];
		for(int bit = 0; bit<output.length;bit++)
		{
			output[bit] = NAND(x[bit],y[bit]);
		}
		return output;
	}
	public static Bit NXOR(Bit x, Bit y)
	{
		return NXOR(x.booleanValue(),y.booleanValue());
	}
	public static Bit NXOR(Bit x, boolean y)
	{
		return NXOR(x.booleanValue(),y);
	}
	public static Bit NXOR(boolean x, Bit y)
	{
		return NXOR(x,y.booleanValue());
	}
	public static Bit NXOR(boolean x, boolean y)
	{
		return NOT(x ^ y);
	}
	public static Bit[] NXOR(Bit[] x, Bit[] y)
	{
		if(x.length<y.length)
			SignExtension(x,y.length);
		if(y.length<x.length)
			SignExtension(y,x.length);
		Bit[] output = new Bit[x.length];
		for(int bit = 0; bit<output.length;bit++)
		{
			output[bit] = NXOR(x[bit],y[bit]);
		}
		return output;
	}
	public static Bit NOR(Bit x, Bit y)
	{
		return NOR(x.booleanValue(),y.booleanValue());
	}
	public static Bit NOR(Bit x, boolean y)
	{
		return NOR(x.booleanValue(),y);
	}
	public static Bit NOR(boolean x, Bit y)
	{
		return NOR(x,y.booleanValue());
	}
	public static Bit NOR(boolean x, boolean y)
	{
		return NOT(x | y);
	}
	public static Bit[] NOR(Bit[] x, Bit[] y)
	{
		if(x.length<y.length)
			SignExtension(x,y.length);
		if(y.length<x.length)
			SignExtension(y,x.length);
		Bit[] output = new Bit[x.length];
		for(int bit = 0; bit<output.length;bit++)
		{
			output[bit] = NOR(x[bit],y[bit]);
		}
		return output;
	}
	public static void SignExtension(Bit[] bitArray,int numBits)
	{
		if(numBits<=bitArray.length)
			return;
		Bit signBit = bitArray[0];
		Bit[] temp=new Bit[numBits];
		int increment=0;	//used to access positions in bitArray
		for(int x=0;x<temp.length-bitArray.length;x++)
		{
			temp[x]=signBit;
		}
		for(int x=temp.length-bitArray.length;x<temp.length;x++)
		{
			temp[x]=bitArray[increment];
			increment++;
		}
	}
	public static Bit[] special(Bit[] x, Bit[] y)
	{
		if(x.length<y.length)
			SignExtension(x,y.length);
		if(y.length<x.length)
			SignExtension(y,x.length);
		Bit[] output = x;
		for(int bit = 0; bit<output.length;bit++)
		{
			if(x[bit].booleanValue() == y[bit].booleanValue());
			else
				output[bit]=NOR(x[bit],y[bit]);	
		}
		return output;
	}
	public static void rotateRightNoCarry(Bit[] b) //WARNING: Changes actual Bit array
	{
		Bit holder = b[b.length-1];
		for(int x=b.length-1;x>0;x--)
		{
			b[x]=b[x-1];
		}
		b[0]=holder;
	}
	public static void rotateLeftNoCarry(Bit[] b) //WARNING: Changes actual Bit array
	{
		Bit holder = b[0];
		for(int x=0;x<b.length-1;x++)
		{
			b[x]=b[x+1];
		}
		b[b.length-1]=holder;
	}
	public static Bit[] bitValue(String s)
	{
		//All indexes of key are defined the default value of FALSE, used to prevent Null Values
		Bit[] key;
		if(s.length()<9)
			key=new Bit[]{FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE};
		else if(s.length()>8 && s.length()<17)
			key=new Bit[]{FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE};
		else if(s.length()>16 && s.length()<33)
			key=new Bit[]{FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE};
		else
			key=new Bit[]{FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE};
		if(s.indexOf("1") == -1)
			return key = new Bit[]{new Bit(false)};
		int index=s.indexOf("1");
		for(int x=key.length-s.length();x<key.length;x++)
		{
			key[x]= (s.charAt(index)=='1') ? TRUE : FALSE;
			index++;
		}
		return key;
	}
	public static Bit[] bitValue(short b) //signed
	{
		if(b>=0)
			return bitValue(Integer.toBinaryString((int)b));
		if(b==-1)
			return new Bit[]{TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE};
		return NOT(bitValue(Integer.toBinaryString((int)((-b)-1))));
	}	
	public static Bit[] bitValue(int b) //signed
	{
		if(b>=0)
			return bitValue(Integer.toBinaryString(b));
		if(b==-1)
			return new Bit[]{TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE};
		return NOT(bitValue(Integer.toBinaryString((-b)-1)));
	}
	public static Bit[] bitValue(long b) //signed
	{
		if(b>=0)
			return bitValue(Long.toBinaryString(b));
		if(b==-1)
			return new Bit[]{TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE};
		return NOT(bitValue(Long.toBinaryString((-b)-1)));
	}
	public static byte byteValue(Bit[] b) throws NumberFormatException
	{
		if(b.length>8)
			throw new NumberFormatException();
		if(b[0].equals(TRUE))
		{
			return (byte)((Byte.parseByte(toBinaryString(NOT(b)),2)+1)*-1);
		}
		return (byte)((Byte.parseByte(toBinaryString(b),2)));
	}
	public static char charValue(Bit[] b) throws NumberFormatException
	{
		if(b.length>16)
			throw new NumberFormatException();
		return (char)(Short.parseShort(toBinaryString(b),2));
	}
	public static short shortValue(Bit[] b) throws NumberFormatException
	{
		if(b.length>16)
			throw new NumberFormatException();
		if(b[0].equals(TRUE))
		{
			return (short)((Short.parseShort(toBinaryString(NOT(b)),2)+1)*-1);
		}
		return (short)((Short.parseShort(toBinaryString(b),2)));
	}
	public static int intValue(Bit[] b) throws NumberFormatException
	{
		if(b.length>32)
			throw new NumberFormatException();
		if(b[0].equals(TRUE))
		{
			return (int)((Integer.parseInt(toBinaryString(NOT(b)),2)+1)*-1);
		}
		return (int)((Integer.parseInt(toBinaryString(b),2)));
	}
	public static long longValue(Bit[] b) throws NumberFormatException
	{
		if(b.length>64)
			throw new NumberFormatException();
		if(b[0].equals(TRUE))
		{
			return (long)((Long.parseLong(toBinaryString(NOT(b)),2)+1)*-1);
		}
		return (long)((Long.parseLong(toBinaryString(b),2)));
	}
	private static String toBinaryString(Bit[] b)
	{
		String binaryString="";
		for(int x=0;x<b.length;x++)
		{
			binaryString+= (b[x].booleanValue()) ? 1 : 0;
		}
		return binaryString;
	}
}