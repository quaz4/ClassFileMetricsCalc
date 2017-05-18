import java.io.*;

/**
 * Parses and stores the fields from a Java .class file.
 *
 * @author William Stewart
 */
public class Fields
{
    private FieldEntry[] entries;

    public Fields(DataInputStream dis, ConstantPool cp) throws IOException, InvalidTagException, InvalidConstantPoolIndex
    {
    	int len = dis.readUnsignedShort();
    	entries = new FieldEntry[len];

        System.out.println("There are " + len + " fields in this class file");

		// Initialise entries to null.
        for(int i = 0; i < len; i++)
        {
            entries[i] = null;
        }

        for(int i = 0; i < len; i++)
        {
        	entries[i] = new FieldEntry(dis, cp);
        }

        //Attributes
        //attributes = new Attributes(dis, cp);
    }
}