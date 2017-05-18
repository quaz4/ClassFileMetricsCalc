import java.io.*;

/**
 * Parses and stores a Java .class file. Parsing is currently incomplete.
 *
 * @author David Cooper
 */
public class ClassFile
{
    private String filename;
    private long magic;
    private int minorVersion;
    private int majorVersion;
    private ConstantPool constantPool;
    private AccessFlags accessFlags;
    private String thisClass;
    private String superClass;
    private Interfaces interfaces;
    private Fields fields;
    private Methods methods;
    private Attributes attributes;

    // ...

    /**
     * Parses a class file an constructs a ClassFile object. At present, this
     * only parses the header and constant pool.
     */
    public ClassFile(String filename) throws ClassFileParserException,
                                             IOException
    {
        DataInputStream dis =
            new DataInputStream(new FileInputStream(filename));

        this.filename = filename;
        magic = (long)dis.readUnsignedShort() << 16 | dis.readUnsignedShort();
        minorVersion = dis.readUnsignedShort();
        majorVersion = dis.readUnsignedShort();
        constantPool = new ConstantPool(dis);

        System.out.println(constantPool);

        accessFlags = new AccessFlags(dis);

        //TODO this and super class values need validation, see docs for details
        thisClass = constantPool.getEntry(dis.readUnsignedShort()).getResolved();
        superClass = constantPool.getEntry(dis.readUnsignedShort()).getResolved();

        interfaces = new Interfaces(dis, constantPool);

        fields = new Fields(dis, constantPool);

        methods = new Methods(dis, constantPool);

        attributes = new Attributes(dis, constantPool);
    }

    /** Returns the contents of the class file as a formatted String. */
    public String toString()
    {
        return String.format(
            "Filename: %s\n" +
            "Magic: 0x%08x\n" +
            "Class file format version: %d.%d\n\n" +
            "Constant pool:\n\n%s" +
            "This Class:%s\n" +
            "Super Class:%s\n",
            filename, magic, majorVersion, minorVersion, constantPool, thisClass, superClass);
    }
}