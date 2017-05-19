import java.io.*;

/**
 * Parses and stores the field information from a Java .class file.
 *
 * @author William Stewart
 */

public class FieldEntry
{
	File file = new File("log");
	FileWriter log = new FileWriter(file, true);

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

		log.write("nameIndex: " + nameIndex + "\n");
		log.write("descIndex: " + descIndex + "\n");

		//System.out.println("nameIndex: " + nameIndex + "\n");
		//System.out.println("descIndex: " + descIndex + "\n");
		
        attributes = new Attributes(dis, cp);

        log.close();
	}
}