import java.io.*;

/**
 * Parses and stores the fields from a Java .class file.
 *
 * @author William Stewart
 */
public class Fields
{
	AccessFlags flags;
	int nameIndex;
	int descIndex;
	int attCount;

    //private CPEntry[] entries;

    public Fields(DataInputStream dis) throws IOException
    {
    	int len = dis.readUnsignedShort();
		flags = new AccessFlags(dis);

		nameIndex = dis.readUnsignedShort();
		descIndex = dis.readUnsignedShort();

		System.out.println("nameIndex: " + nameIndex);
		System.out.println("descIndex: " + descIndex);
		
		attCount = dis.readUnsignedShort();

		//TODO Get attributes
    }
}

NOTE TO WILL:

Need a new class called FieldInfo
I half wrote it wrong, needs to be like the constant pool, 
how its an array of values, there could be multiple values stored as fields