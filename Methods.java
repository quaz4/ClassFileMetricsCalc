import java.io.*;

/**
 * Parses and stores a Java .class file. Parsing is currently incomplete.
 *
 * @author William Stewart
 */

public class Methods
{
	private MethodAccessFlags flags;
    private String nameIndex;
    private String descIndex;
    private Attributes attributes;

	public Methods(DataInputStream dis, ConstantPool cp) throws IOException,
                                                        InvalidTagException, 
                                                        InvalidConstantPoolIndex
	{
		flags = new MethodAccessFlags(dis);
        nameIndex = cp.getEntry(dis.readUnsignedShort()).getResolved();
        descIndex = cp.getEntry(dis.readUnsignedShort()).getResolved();

        System.out.println(nameIndex);
        System.out.println(descIndex);

        attributes = new Attributes(dis, cp);
	}
}