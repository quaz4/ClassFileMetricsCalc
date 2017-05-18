import java.io.*;

/**
 * Parses and stores the constant pool from a Java .class file.
 *
 * @author William Stewart
 */
public class Interfaces
{
    private CPEntry[] entries;

    public Interfaces(DataInputStream dis, ConstantPool cp) throws InvalidConstantPoolIndex,
    																IOException,
    																InvalidTagException
    {
    	int len = dis.readUnsignedShort();
        //int random = dis.readUnsignedShort();
        entries = new CPEntry[len];

        System.out.println("There are " + len + " interfaces in this class file");

        // Initialise entries to null.
        for(int i = 0; i < len; i++)
        {
            entries[i] = null;
        }

        for(int i = 0; i < len; i++)
        {
        	entries[i] = CPEntry.parse(dis);
        }

        // Once the constant pool has been parsed, resolve the various
        // internal references.
        for(int i = 0; i < len; i++)
        {
            if(entries[i] != null)
            {
                entries[i].resolveReferences(cp);
                System.out.println(entries[i]);
            }
        }
    }

    /** Retrieves a given constant pool entry. */
    public CPEntry getEntry(int index) throws InvalidConstantPoolIndex
    {
        if(index < 0 || index > entries.length)
        {
            throw new InvalidConstantPoolIndex(String.format(
                "Invalid interfaces pool index: %d (not in range [0, %d])",
                index, entries.length));
        }
        else if(entries[index] == null)
        {
            throw new InvalidConstantPoolIndex(String.format(
                "Invalid Interfaces pool index: %d (entry undefined)", index));
        }
        return entries[index];
    }
}