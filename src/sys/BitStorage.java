package sys;

public class BitStorage
{
	private boolean[] bits;
	public BitStorage(boolean... bitArray)
	{
		bits = bitArray;
	}
	public BitStorage(byte[] bytes)
	{
		this.bits = byte2bit(bytes);
	}
	public BitStorage(int length)
	{
		this.bits = new boolean[length];
		for(int i=0; i<length; ++i)
			this.bits[i]=true;
	}
	public void set(int index, boolean value)
	{
		this.bits[index] = value;
	}
	public byte[] toByteArray()
	{
		byte[] bytes=new byte[bits.length/8+1];
		String bitString = this.toString();
		System.out.println(bitString);
		while(bytes.length*8>bitString.length())
			bitString+='0';
		for(int i=0; i<bytes.length; ++i)
		{
			String con = bitString.substring(8*i, 8+8*i);
			if(con.charAt(0)=='1')
				con = '-'+con.substring(1);
			bytes[i] = Byte.parseByte(con, 2);
		}
		return bytes;
	}
	public char[] toCharArray()
	{
		return new String(this.toByteArray()).toCharArray();
	}
	public String toString()
	{
		String out = "";
		for(boolean b : bits)
			out += b?'1':'0';
		return out;
	}
	public String toCharString()
	{
		return new String(this.toByteArray());
	}
	public static String toCharString(BitStorage... bitStorages)
	{
		String str="";
		for(BitStorage bs : bitStorages)
			str+=bs.toCharString();
		return str;
	}
	public static byte[] concatenate (byte[] a, byte[]... bc)
	{
		byte[] n = java.util.Arrays.copyOf(a, a.length);
		for(byte[] b : bc)
		{
			n = java.util.Arrays.copyOf(n, n.length+b.length);
			n = concatenate(n, b);
		}
		System.out.println(java.util.Arrays.toString(n));
		return n;
	}
	private static byte[] concatenate (byte[] a, byte[] b)
	{
		byte[] c = new byte[a.length+b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		System.out.println("c="+java.util.Arrays.toString(c));
		return c;
	}
	@SuppressWarnings("unused")
	private static byte[] arraycopy(byte[] src, int srcPos, byte[] dest, int destPos, int length)
	{
		System.out.println("\na="+java.util.Arrays.toString(src)+"\nb="+java.util.Arrays.toString(dest));
		for(int i=srcPos,d=destPos;i<srcPos+length&&d<destPos+length;++i,++d)
			dest[d]=((Byte)src[i]).byteValue();
		System.out.println("b="+java.util.Arrays.toString(dest));
		return dest;
	}
	private static boolean[] byte2bit(byte[] bytes) {
		boolean[] bits = new boolean[bytes.length / 8];
		for (int i = 0; i < bytes.length * 8; i++)
			if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
				bits[i] = true;
		return bits;
	}
	public boolean get(int i)
	{
		return this.bits[i];
	}
}

