import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.lang.Integer.*;

interface Query {
    boolean matches(String s);
    String word();
}
class Contains implements Query{
    String[] lines;
    String cmdWord;
    Contains(String[] lines, String cmdWord){
        this.lines = lines;
        this.cmdWord = cmdWord;
    }
    public String word(){
        int indexOfContains = this.cmdWord.indexOf("contains=");
        return this.cmdWord.substring(indexOfContains+10,cmdWord.length()-1);
    }
    public boolean matches(String s){
        if (s.contains("contains"))
            return true;
        else
            return false;
    }
    String[] contains(String s){
        int count = 0;
        for (String line : this.lines){
            if (line.contains(s)){
                count++;
            }
        }
        String[] newLines = new String[count];
        count = 0;
        for (int i = 0; i < lines.length; i++){
            if (lines[i].contains(s)){
                newLines[count] = lines[i];
                count++;
            }            
        }
        return newLines;
    }
}
class StringSearch {
    public static void main(String [] args) throws IOException{
        if (args.length == 0){
            throw new IllegalArgumentException("please enter a command line argument");
        }
        String contents = Files.readString(Paths.get(args[0]));
        //System.out.println(contents);
        String[] lines = contents.split("\n");
        Contains check = new Contains(lines, args[1]);
        if (check.matches(args[1])){
            lines = check.contains(check.word());
        }
        for (String s : lines){
            System.out.println(s);
        }
    }
}