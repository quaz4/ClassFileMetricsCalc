import java.io.*;

/**
 * Parses and stores the field information from a Java .class file.
 *
 * @author William Stewart
 */

public class FieldEntry
{
	private FieldAccessFlags flags;
	private int nameIndex;
	private int descIndex;
	private int attCount;
	private Attributes attributes;

	public FieldEntry(DataInputStream dis, ConstantPool cp) throws IOException, 
															InvalidTagException,
															InvalidConstantPoolIndex
	{
		flags = new FieldAccessFlags(dis);

		nameIndex = dis.readUnsignedShort();
		descIndex = dis.readUnsignedShort();

		System.out.println("nameIndex: " + nameIndex);
		System.out.println("descIndex: " + descIndex);
		
		//attCount = dis.readUnsignedShort();
		//Attributes
        attributes = new Attributes(dis, cp);
	}
}