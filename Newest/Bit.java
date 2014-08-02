package com.tigerwolf.encryption;
import static java.lang.Math.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

//This class is used to redefine Boolean indirectly
//Adds Bitwise operations to class
public final class Bit implements Serializable, Comparable<Bit>
{
	public static final Class<Boolean> TYPE = Boolean.TYPE;
	public static final Bit TRUE = new Bit(true);
	public static final Bit FALSE = new Bit(false);
	private static final long serialVersionUID = -6334195800985631469L;
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
	public static Bit[] SignExtension(Bit[] bitArray,int numBits)
	{
		if(numBits<=bitArray.length)
			return bitArray;
		Bit signBit = bitArray[0];
		Bit[] temp=new Bit[numBits];
		//int increment=0;	//used to access positions in bitArray
		for(int x=0;x<temp.length-bitArray.length;x++)
		{
			temp[x]=signBit;
		}
		for(int x=temp.length-bitArray.length,increment=0;x<temp.length;x++)
		{
			temp[x]=bitArray[increment];
			increment++;
		}
		return temp;
	}
	public static Bit[] special(Bit[] x, Bit[] y) //Derives key1 where key1 AND key2 == key3, given key2 and key3
	{
		if(x.length<y.length)
			SignExtension(x,y.length);
		if(y.length<x.length)
			SignExtension(y,x.length);
		Bit[] output = new Bit[x.length];
		for(int bit = 0; bit<output.length;bit++)
		{
			if(x[bit].value && y[bit].value);
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
	public static Bit[] bitValues(String s) //returns binary representation of caller
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
		else if(s.length()>32 && s.length()<65)
			key=new Bit[]{FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
					    FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE};
		else
		{
			key=new Bit[s.length()];
			for(int k=0;k<s.length();k++)
			{
				key[k]=FALSE;
			}
		}
		int index=s.indexOf("1");
		for(int x=key.length-s.length();x<key.length;x++,index++)
		{
			key[x]= (s.charAt(index)=='1') ? TRUE : FALSE;
		}
		return key;
	}
	public static Bit[] bitValue(String b)
	{
		return bitValue(b.toCharArray());
	}
	public static Bit[] bitValue(char[] b)
	{
		Bit[] bits = new Bit[b.length*16];
		for(int x=0,y=0;x<b.length;x++)
		{
			Bit[] temp=SignExtension(Bit.bitValue(b[x]),16);
			for(int z=0;z<16;z++,y++)
				bits[y]=temp[z];
		}
		return bits;
	}
	public static Bit[] bitValue(char b)
	{
		if (b == 0)
			return new Bit[]{FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
						  FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE};
		return bitValues(Integer.toBinaryString((int)b));
	}
	public static Bit[] bitValue(short b) //signed
	{
		if (b == 0)
			return new Bit[]{FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
						  FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE};
		if(b>=0)
			return bitValues(Integer.toBinaryString((int)b));
		if(b==-1)
			return new Bit[]{TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,
						  TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE};
		return NOT(bitValues(Integer.toBinaryString((-b)-1)));
	}
	public static Bit[] bitValue(int b) //signed
	{
		if (b == 0)
			return new Bit[]{FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
						  FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
						  FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
						  FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE};
		if(b>=0)
			return bitValues(Integer.toBinaryString(b));
		if(b==-1)
			return new Bit[]{TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,
						  TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,
						  TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,
						  TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE};
		return NOT(bitValues(Integer.toBinaryString((-b)-1)));
	}
	public static Bit[] bitValue(long b) //signed
	{
		if (b == 0)
			return new Bit[]{FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
						  FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
						  FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
						  FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
						  FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
						  FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
						  FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,
						  FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE};
		if(b>=0)
			return bitValues(Long.toBinaryString(b));
		if(b==-1)
			return new Bit[]{TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,
						  TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,
						  TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,
						  TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,
						  TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,
						  TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,
						  TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,
						  TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE};
		return NOT(bitValues(Long.toBinaryString((-b)-1)));
	}
	public static String stringValue(Bit[] b) //parses as unicode
	{
		String output="";
		if(b.length%8!=0)
			SignExtension(b,b.length*16);
		for(int x=0;x<b.length;x=x+16)
		{
			Bit[] temp = new Bit[16];
			System.arraycopy(b,x,temp,0,16);
			output+=charValue(temp);
		}
		return output;
	}
	public static byte byteValue(Bit[] b) throws NumberFormatException
	{
		if(b.length>8)
			throw new NumberFormatException();
		if(b[0].equals(TRUE))
		{
			return (byte)((Byte.parseByte(toBinaryString(NOT(b)),2)+1)*-1);
		}
		return Byte.parseByte(toBinaryString(b),2);
	}
	public static char charValue(Bit[] b) throws NumberFormatException
	{
		if(b.length>16)
			throw new NumberFormatException();
		return (char)(Integer.parseInt(toBinaryString(b),2));
	}
	public static short shortValue(Bit[] b) throws NumberFormatException
	{
		if(b.length>16)
			throw new NumberFormatException();
		if(b[0].equals(TRUE))
		{
			return (short)((Short.parseShort(toBinaryString(NOT(b)),2)+1)*-1);
		}
		return Short.parseShort(toBinaryString(b),2);
	}
	public static int intValue(Bit[] b) throws NumberFormatException
	{
		if(b.length>32)
			throw new NumberFormatException();
		if(b[0].equals(TRUE))
		{
			return (Integer.parseInt(toBinaryString(NOT(b)),2)+1)*-1;
		}
		return (Integer.parseInt(toBinaryString(b),2));
	}
	public static long longValue(Bit[] b) throws NumberFormatException
	{
		if(b.length>64)
			throw new NumberFormatException();
		if(b[0].equals(TRUE))
		{
			return (Long.parseLong(toBinaryString(NOT(b)),2)+1)*-1;
		}
		return (Long.parseLong(toBinaryString(b),2));
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
	public static boolean[] toBooleanArray(Bit[] b)
	{
		boolean[] bArray = new boolean[b.length];
		for(int x=0;x<b.length;x++)
			bArray[x]=b[x].value;
		return bArray;
	}
	public static String runlengthEncode(String pt) //implements runlength encoding
	{
		if(pt.length()==0)
			return pt;
		return runlengthEncode(pt.toCharArray());
	}
	private static String runlengthEncode(char[] pt)
	{
		String ct = "";
		int run = 0;
		char current;
		for(int x=0;x<pt.length;)
		{
			current = pt[x];
			while(x<pt.length&&pt[x++]==current)
				run++;
			ct += current+" "+run+" ";
			run = 1;
		}
		return ct;
	}
	public static String runlengthDecode(String ct) throws java.io.IOException
	{
		if(ct.length() == 0)
			return ct;
		String[] vals = ct.split(" ");
		char[] chars=new char[1];
		int counts;
		ct = "";
		for(int x=0;x<vals.length;x++)
		{
			if(x%2==0)
				chars[0]=vals[x].charAt(0);
			else
			{
				for(counts=Integer.parseInt(vals[x]);counts>0;counts--)
					ct+=chars[0]+"";
			}
		}
		return ct;
	}
	public static Bit[] compress(Bit[] b)
	{
		return compress(b, b[0],b[1]);
	}
	public static Bit[] compressString(String s)
	{
		char[] chars = s.toCharArray();
		ArrayList <Bit> bits = new ArrayList<Bit>();
		for(char c : chars)
			bits.addAll(Arrays.asList(compress(bitValue(c))));
		return bits.toArray(new Bit[]{});
	}
	private static Bit[] compress(Bit[] b, Bit pos,Bit pos2)
	{
		if(pos2.value)
		{
			return b;
		}
		return compress(Arrays.copyOfRange(b,1,b.length),b[1],b[2]);
	}
	public static byte[] compress(boolean[] b)
	{
		return new byte[1];
	}
	static byte[] compressHelper(Bit[] b)
	{
		return new byte[1];
	}
	static byte[] compressHelper(boolean[] b)
	{
		return new byte[1];
	}
}