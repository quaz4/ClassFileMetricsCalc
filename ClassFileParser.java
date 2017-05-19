import java.io.*;
import java.sql.Timestamp;

/**
 * Parses and displays a Java .class file.
 *
 * @author David Cooper
 */
public class ClassFileParser
{


    public static void main(String[] args)
    {
        if(args.length > 0)
        {
            try
            {
                File file = new File("log");
                FileWriter log = new FileWriter(file, true);

                for(int i = 0; i < args.length; i++)
                {
                    ClassFile cf = new ClassFile(args[i]);
                    //System.out.println(cf);
                    log.write(cf.toString());

                    //Calculate metrics here using classfile...if it worked

                    //Write to graph
                }
            }
            catch(Exception e)
            {
                //Blanket case just in case unexpected exceptiom
                System.out.println("Unexpected exception: " + e.getMessage());
            }

        }
        else
        {
            System.out.println("Usage: java ClassFileParser <class-file-1> <class-file-2> ...");
        }
    }
}
