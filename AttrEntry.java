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
                                                        InvalidTagException
    {
        AttrEntry entry;
        String tag = null;
        byte[] b = new byte[2];
        dis.readFully(b);
        tag = new String(b);

        switch(tag)
        {
            case  "ConstantValue": entry = new ConstantValue(dis);                                               break;
            case  "Code": entry = new Code(dis);                                                                 break;
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
                throw new InvalidTagException(
                    String.format("Invalid tag: 0x%02x", tag));

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
    public ConstantValue(DataInputStream dis) throws IOException
    {
        byte[] b = new byte[4];
        dis.readFully(b);
        tag = new String(b);
    }

    public String getTagString() { return "ConstantValue"; }
}

class Code extends AttrEntry
{
    public Code(DataInputStream dis) throws IOException
    {
        
    }

    public String getTagString() { return "Code"; }
}

class StackMapTable extends AttrEntry
{
    public StackMapTable(DataInputStream dis) throws IOException
    {
        
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