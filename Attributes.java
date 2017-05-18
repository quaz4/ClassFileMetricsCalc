import java.io.*;

/**
 * Parses and stores the fields from a Java .class file.
 *
 * @author William Stewart
 */

public class Attributes
{
	AttrEntry[] entries;

	public Attributes(DataInputStream dis, ConstantPool cp) throws IOException, InvalidTagException, InvalidConstantPoolIndex
	{
		int len = dis.readUnsignedShort();

		//int len = dis.readInt();
        entries = new AttrEntry[len];

        System.out.println("There are " + len + " attributes in this class file");
        
        // Initialise entries to null.
        for(int i = 0; i < len; i++)
        {
            entries[i] = null;
        }

        for(int i = 0; i < len; i++)
        {
        	entries[i] = AttrEntry.parse(dis, cp);
        }
	}
}