import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.lang.Integer.*;

interface Query {
    boolean matches(String s);
}
interface Transform {
    String transform(String s);
}

class Upper implements Transform{
    public String transform(String s){
        return s.toUpperCase();
    }
}
class Lower implements Transform{
    public String transform(String s){
        return s.toLowerCase();
    }
}
class First implements Transform{
    int num;
    First(int num){
        this.num = num;
    }
    public String transform(String s){
        return s.substring(0,num);
    }
}
class Last implements Transform{
    int num;
    Last(int num){
        this.num = num;
    }
    public String transform(String s){
        return s.substring(s.length()-num,s.length());
    }
}
class Replace implements Transform{
    String replaceWord, wordToReplace;
    Replace(String wordToReplace, String replaceWord){
        this.replaceWord = replaceWord;
        this.wordToReplace = wordToReplace;
    }
    public String transform(String s){
        return s.replace(this.wordToReplace,this.replaceWord);
    }
}
class Contains implements Query{
    String containsWord;
    Contains(String containsWord){
        this.containsWord = containsWord;
    }
    public boolean matches(String s){
        if (s.contains(containsWord))
            return true;
        else
            return false;
    }
}
class Length implements Query{
    int num;
    Length(int num){
        this.num = num;
    }
    public boolean matches(String s){
        if (s.length() == num)
            return true;
        else
            return false;
    }
}
class Greater implements Query{
    int num;
    Greater(int num){
        this.num = num;
    }
    public boolean matches(String s){
        if (s.length() > num)
            return true;
        else
            return false;
    }
}
class Less implements Query{
    int num;
    Less(int num){
        this.num = num;
    }
    public boolean matches(String s){
        if (s.length() < num)
            return true;
        else
            return false;
    }
}
class Starts implements Query{
    String start;
    Starts(String start){
        this.start = start;
    }
    public boolean matches(String s){ //s is the txt string
        if (s.substring(0,this.start.length()).equals(this.start))
            return true;
        else
            return false;
    }
}
class Ends implements Query{
    String end;
    Ends(String end){
        this.end = end;
    }
    public boolean matches(String s){
        if(s.substring(s.length()-this.end.length(),s.length()).equals(this.end))
            return true;
        else
            return false;
    }
}
class Not implements Query{
    Query query;
    Not(Query query){
        this.query = query;
    }
    public boolean matches(String s){
        if (!query.matches(s))
            return true;
        else
            return false;
    }
}
class StringSearch {
    Transform readTransform(String t){
        String[] transformString = t.split("=");
        String task = transformString[0];
        String userInput = transformString[0];
        if (transformString.length > 1){
            userInput = transformString[1];
        }
        if (task.equals("upper")){
            userInput = userInput.substring(1,userInput.length()-1);
            return new Upper();
        }
        if (task.equals("lower")){
            userInput = userInput.substring(1,userInput.length()-1);
            return new Lower();
        }
        if (task.equals("first"))
            return new First(parseInt(userInput));
        if (task.equals("last"))
            return new Last(parseInt(userInput));
        if (task.equals("replace")){
            String[] replaceWords = userInput.split(";");
            String word1 = replaceWords[0].substring(1,replaceWords[0].length()-1);
            String word2 = replaceWords[1].substring(1,replaceWords[1].length()-1);
            return new Replace(word1, word2);
        }
        return null;
    }
    Query readQuery(String q){
        String[] queryString = q.split("=");
        String task = queryString[0];
        String userInput = queryString[1];
        if (task.contains("not")){
            int firstParenthesisLocation = q.indexOf("(");
            int lastParenthesisLocation = q.indexOf(")");
            String notQuery = q.substring(firstParenthesisLocation+1,lastParenthesisLocation);
            StringSearch not = new StringSearch();
            return new Not(not.readQuery(notQuery));
        }
        if (task.equals("length")){
            return new Length(parseInt(userInput));
        }
        if (task.equals("contains")){
            userInput = userInput.substring(1,queryString[1].length()-1);
            return new Contains(userInput);
        }
        if (task.equals("less")){
            return new Less(parseInt(userInput));
        }
        if (task.equals("greater")){
            return new Greater(parseInt(userInput));
        }
        if (task.equals("starts")){
            userInput = userInput.substring(1,queryString[1].length()-1);
            return new Starts(userInput);
        }
        if (task.equals("ends")){
            userInput = userInput.substring(1,queryString[1].length()-1);
            return new Ends(userInput);
        }
        return null;
    }
    public static void main(String [] args) throws IOException{
        if (args.length == 0){
            throw new IllegalArgumentException("please enter a command line argument");
        }
        String contents = Files.readString(Paths.get(args[0]));
        //System.out.println(contents);
        String[] lines = contents.split("\n");

        if (args.length == 1){
            for (String s : lines){
                System.out.println(s);
            }
        }

        if (args.length == 2){
            StringSearch check = new StringSearch();
            for (int i = 0; i < lines.length; i++){
                if (check.readQuery(args[1]).matches(lines[i])){
                    System.out.println(lines[i]);
                }
            }
        }

        if (args.length == 3){
            StringSearch check = new StringSearch();
            for (int i = 0; i < lines.length; i++){
                if (check.readQuery(args[1]).matches(lines[i])){
                    System.out.println(check.readTransform(args[2]).transform(lines[i]));
                }
            }
        }
    }
}