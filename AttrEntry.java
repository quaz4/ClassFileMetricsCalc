import java.io.*;

/**
 * Parses and stores an entry from attribute tables (in a Java .class
 * file).
 *
 * @author William Stewart
 */
public abstract class AttrEntry
{
    /**
     * Parses a constant pool entry from a DataInputStream, returning an
     * instance of the appropriate class. Any references to other entries
     * remain unresolved until the resolveReferences() method is called.
     */
    public static AttrEntry parse(DataInputStream dis, ConstantPool cp) throws IOException,
                                                        InvalidTagException, InvalidConstantPoolIndex
    {
        File file = new File("log");
        FileWriter log = new FileWriter(file, true);
        
        log.write("ATTRIBUTE:");

        //System.out.println("ATTRIBUTE:");
        AttrEntry entry = null;
        String tag = null;

        int value = dis.readUnsignedShort();

        log.write(value);
        //System.out.println(value);
        tag = cp.getEntry(value).getResolved();
        //System.out.println(tag);
        log.write(tag);

        switch(tag)
        {
            case  "ConstantValue": entry = new ConstantValue(dis);                                               break;
            case  "Code": entry = new Code(dis, cp);                                                             break;
            case  "StackMapTable": entry = new StackMapTable(dis);                                               break;
            case  "Exceptions": entry = new Exceptions(dis);                                                     break;
            case  "InnerClasses": entry = new InnerClasses(dis);                                                 break;
            case  "EnclosingMethod": entry = new EnclosingMethod(dis);                                           break;
            case  "Synthetic": entry = new Synthetic(dis);                                                       break;
            case  "Signature": entry = new Signature(dis);                                                       break;
            case  "SourceFile": entry = new SourceFile(dis);                                                     break;
            case  "SourceDebugExtension": entry = new SourceDebugExtension(dis);                                 break;
            case  "LineNumberTable": entry = new LineNumberTable(dis);                                           break;
            case  "LocalVariableTable": entry = new LocalVariableTable(dis);                                     break;
            case  "LocalVariableTypeTable": entry = new LocalVariableTypeTable(dis);                             break;
            case  "Deprecated": entry = new Deprecated(dis);                                                     break;
            case  "RuntimeVisibleAnnotations": entry = new RuntimeVisibleAnnotations(dis);                       break;
            case  "RuntimeInvisibleAnnotations": entry = new RuntimeInvisibleAnnotations(dis);                   break;
            case  "RuntimeVisibleParameterAnnotations": entry = new RuntimeVisibleParameterAnnotations(dis);     break;
            case  "RuntimeInvisibleParameterAnnotations": entry = new RuntimeInvisibleParameterAnnotations(dis); break;
            case  "AnnotationDefault": entry = new AnnotationDefault(dis);                                       break;
            case  "BootstrapMethods": entry = new BootstrapMethods(dis);                                         break;

            default:
                throw new InvalidTagException("Bad attribute tag '" + tag + "'");

        }

        return entry;
    }

    //TODO FIX DIS PLZ
    public String getTagString() throws InvalidTagException
    {
        throw new InvalidTagException("Eeep");
    }
}

class ConstantValue extends AttrEntry
{
    private int attrLength;
    private int constValueIndex;
    private String nameString = null;

    public ConstantValue(DataInputStream dis) throws IOException
    {
        attrLength = dis.readInt();
        constValueIndex = dis.readUnsignedShort();
    }

    public void resolveReferences(ConstantPool cp) throws InvalidConstantPoolIndex
    {
        this.nameString = cp.getEntry(constValueIndex).toString();
    }

    public String getTagString() { return "ConstantValue"; }

    public String getValue()
    {
        return nameString;
    }
}

class Code extends AttrEntry
{
    private int attrLength;
    private int maxStack;
    private int maxLocals;
    private int codeLength;
    private byte randomByte;
    private Opcode[] code;
    private byte[] labels;
    private int excLen;
    // exception stuffs
    private int attrCount;

    private int catchType;
    private String[] exceptions;

    private Attributes attributes;

    public Code(DataInputStream dis, ConstantPool cp) throws IOException, InvalidConstantPoolIndex, InvalidTagException
    {
        attrLength = dis.readInt();

        for(int i = 0; i < attrLength-1; i++)
        {
            dis.readByte();
        }

        //maxStack = dis.readUnsignedShort();
        //maxLocals = dis.readUnsignedShort();
        //codeLength = dis.readInt();
        ////randomByte = dis.readByte(); //MAYBE NOT NEEDED ITS REALLY UNCLEAR, LIKE REALLY UNCLEAR


/*
        int i = 0;
        int start = 0;
        int size = 0;

        while(i < codeLength)
        {
            byte[] b = new byte[1];
            b[0] = dis.readByte();
            Opcode oCode = Opcode.getOpcode(dis.readByte());
            size = oCode.getSize(b, 0);
            code[i] = oCode;

            for(int k = i + 1; k < i + size; k++)
            {
                labels[k] = dis.readByte();
            }

            i += size;
        }

        excLen = dis.readUnsignedShort();
        exceptions = new String[excLen];

        for(i = 0; i < excLen; i++)
        {
            dis.readUnsignedShort();
            dis.readUnsignedShort();
            dis.readUnsignedShort();
            exceptions[i] = cp.getEntry(dis.readUnsignedShort()).toString();
        }

        attributes = new Attributes(dis, cp);
*/
    }

    public String getTagString() { return "Code"; }
}

class StackMapTable extends AttrEntry
{
    private int attrLength;

    public StackMapTable(DataInputStream dis) throws IOException
    {
        attrLength = dis.readInt();

        for(int i = 0; i < attrLength; i++)
        {
            dis.readByte();
        }
    }

    public String getTagString() { return "StackMapTable"; }
}

class Exceptions extends AttrEntry
{
    public Exceptions(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "Exceptions"; }
}

class InnerClasses extends AttrEntry
{
    public InnerClasses(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "InnerClasses"; }
}

class EnclosingMethod extends AttrEntry
{
    public EnclosingMethod(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "EnclosingMethod"; }
}

class Synthetic extends AttrEntry
{
    public Synthetic(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "Synthetic"; }
}

class Signature extends AttrEntry
{
    public Signature(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "Signature"; }
}

class SourceFile extends AttrEntry
{
    public SourceFile(DataInputStream dis) throws IOException
    {
        System.out.println("SOURCE FILE WOO");
    }

    public String getTagString() { return "SourceFile"; }
}

class SourceDebugExtension extends AttrEntry
{
    public SourceDebugExtension(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "SourceDebugExtension"; }
}

class LineNumberTable extends AttrEntry
{
    public LineNumberTable(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "LineNumberTable"; }
}

class LocalVariableTable extends AttrEntry
{
    public LocalVariableTable(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "LocalVariableTable"; }
}

class LocalVariableTypeTable extends AttrEntry
{
    public LocalVariableTypeTable(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "LocalVariableTypeTable"; }
}

class Deprecated extends AttrEntry
{
    public Deprecated(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "Deprecated"; }
}

class RuntimeVisibleAnnotations extends AttrEntry
{
    public RuntimeVisibleAnnotations(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "RuntimeVisibleAnnotations"; }
}

class RuntimeInvisibleAnnotations extends AttrEntry
{
    public RuntimeInvisibleAnnotations(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "RuntimeInvisibleAnnotations"; }
}

class RuntimeVisibleParameterAnnotations extends AttrEntry
{
    public RuntimeVisibleParameterAnnotations(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "RuntimeVisibleParameterAnnotations"; }
}

class RuntimeInvisibleParameterAnnotations extends AttrEntry
{
    public RuntimeInvisibleParameterAnnotations(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "RuntimeInvisibleParameterAnnotations"; }
}

class AnnotationDefault extends AttrEntry
{
    public AnnotationDefault(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "AnnotationDefault"; }
}

class BootstrapMethods extends AttrEntry
{
    public BootstrapMethods(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "BootstrapMethods"; }
}