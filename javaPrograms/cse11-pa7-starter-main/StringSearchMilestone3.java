import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.lang.Integer.*;

interface Query {
    boolean matches(String s);
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

        }
    }


}