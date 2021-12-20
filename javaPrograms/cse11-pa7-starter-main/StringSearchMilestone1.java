import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.lang.Integer.*;

class StringSearch {
    public static void main(String [] args) throws IOException{
        if (args.length == 0){
            throw new IllegalArgumentException("please enter a command line argument");
        }
        String contents = Files.readString(Paths.get(args[0]));
        //System.out.println(contents);
        String[] lines = contents.split("\n");
        for (String s : lines){
            System.out.println(s);
        }
    }
}