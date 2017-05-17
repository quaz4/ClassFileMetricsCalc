import java.io.*;

/**
 * Parses and stores the field information from a Java .class file.
 *
 * @author William Stewart
 */

public class FieldEntry
{
	FieldAccessFlags flags;
	int nameIndex;
	int descIndex;
	int attCount;

	public FieldEntry(DataInputStream dis) throws IOException
	{
		flags = new FieldAccessFlags(dis);

		nameIndex = dis.readUnsignedShort();
		descIndex = dis.readUnsignedShort();

		System.out.println("nameIndex: " + nameIndex);
		System.out.println("descIndex: " + descIndex);
		
		attCount = dis.readUnsignedShort();
	}
}