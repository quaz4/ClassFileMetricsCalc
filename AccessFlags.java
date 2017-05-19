import java.util.*;
import java.io.*;
import java.lang.IllegalArgumentException;

/**
 * Represents the access_flags for a java .class file
 * 
 * (Requires Java 1.6.)
 *
 * @author Will Stewart
 */

public class AccessFlags
{
	private int[] flagValues = {1, 16, 32, 512, 1024, 4096, 8192, 16384};

	File file = new File("log");
    FileWriter log = new FileWriter(file, true);

	/** 
	 * Values in flags array are associated with these flags
	 * ACC_PUBLIC, ACC_FINAL, ACC_SUPER, ACC_INTERFACE, 
	 * ACC_ABSTRACT, ACC_SYNTHETIC, ACC_ANNOTATION, ACC_ENUM
	 */
	private boolean[] flags = new boolean[8];

	public AccessFlags(DataInputStream dis) throws IOException
	{
		int access = (Integer.valueOf(String.valueOf(dis.readUnsignedShort()), 10));

		/** 
		 * Step through each table entry to see if flags are set and table to correct values
		 */
		for(int i = 7; i >= 0; i--)
		{
			access = setFlag(access, i);
			log.write(i + ": " + access + "\n");
			//System.out.println(i + ": " + access);
		}
		
		log.close();
	}

	/** 
	 * Checks if flag has been set and removes it, updates flags[], returns modified access value
	 */	
	private int setFlag(int access, int tableIndex) throws IOException
	{
		if(tableIndex < 0 || tableIndex > 7)
		{
			throw new IllegalArgumentException("Invalid tableIndex: " + tableIndex);
		}

		if(access < 0)
		{
			throw new IllegalArgumentException("Invalid tableIndex: " + tableIndex);
		}

		// If access mask divisible by mask value, flag was set to enabled
		if(access / flagValues[tableIndex] >= 1)
		{
			log.write("True");
			//System.out.println("True");
			flags[tableIndex] = true;
			access -= flagValues[tableIndex];
		}
		else
		{
			log.write("False");
			//System.out.println("False");
			flags[tableIndex] = false;
		}

		if(access < 0)
		{
			//TODO Create custom exception for this
			//throw new 
		}

		return access;
	}
}