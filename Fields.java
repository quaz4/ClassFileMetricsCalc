import java.io.*;

/**
 * Parses and stores the fields from a Java .class file.
 *
 * @author William Stewart
 */
public class Fields
{
    File file = new File("log");
    FileWriter log = new FileWriter(file, true);

    private FieldEntry[] entries;

    public Fields(DataInputStream dis, ConstantPool cp) throws IOException, InvalidTagException, InvalidConstantPoolIndex
    {
    	int len = dis.readUnsignedShort();
    	entries = new FieldEntry[len];

        log.write("There are " + len + " fields in this class file\n");

        //System.out.println("There are " + len + " fields in this class file");

		// Initialise entries to null.
        for(int i = 0; i < len; i++)
        {
            entries[i] = null;
        }

        for(int i = 0; i < len; i++)
        {
        	entries[i] = new FieldEntry(dis, cp);
        }

    log.close();
    }
}