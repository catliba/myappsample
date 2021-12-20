import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


class WordSearch {
    static int largestIndexValue(List<Integer> s){
        int largestIndex = 0;
        int largestValue = s.get(0);
        for (int i = 0; i < s.size()-1; i++){
            if (s.get(i+1) > largestValue){
                largestValue = s.get(i+1);
                largestIndex = i+1;
            }
        }
        return largestIndex;
    }
    public static void main(String[] args) throws IOException{
        if (args.length < 2){
            throw new IllegalArgumentException("Please provide files");
        }
        String contents = Files.readString(Paths.get(args[0]));
        String[] lines = contents.split("\n");
        String[] documents = new String[args.length-1];
        for (int i = 0; i < args.length-1; i++){
            documents[i] = Files.readString(Paths.get(args[i+1]));
        }
        List<Integer> queryCounter = new ArrayList<>();
        ArrayList<String> docs = new ArrayList<>();
        List<ArrayList<String>> docslist = new ArrayList<>();
        for (int i = 0; i < lines.length; i++){
            int counter = 0;
            for (int j = 0; j < documents.length; j++){
                if (documents[j].contains(lines[i])){
                    counter++;
                    docs.add(args[j+1]);
                }
            }
            queryCounter.add(counter);
            ArrayList<String> docsClone = (ArrayList<String>)docs.clone();
            docslist.add(docsClone);
            docs.clear();
        }
        
        ArrayList<String> queries = new ArrayList<String>();
        List<ArrayList<String>> queriesList = new ArrayList<>();
        List<Integer> docTracker = new ArrayList<>();
        for (int i = 0; i < documents.length; i++){
            int counter = 0;
            for (int j = 0; j < lines.length; j++){
                if (documents[i].contains(lines[j])){
                    counter++;
                    queries.add(lines[j]);
                }
            }
            docTracker.add(counter);
            ArrayList<String> queryClone = (ArrayList<String>)queries.clone();
            queriesList.add(queryClone);
            queries.clear();
        }
        
        boolean allZeros = true;
        for (int i = 0; i < queryCounter.size(); i++){
            if (queryCounter.get(i) != 0) allZeros = false;
        }
        if (allZeros == true){
            System.out.println("No matches found.");
        }
        else {
            System.out.println("Most relevant search term: "+ lines[largestIndexValue(queryCounter)]);
            System.out.println("Most relevant document: "+ args[largestIndexValue(docTracker)+1]);
            System.out.println();
            for (int i = 0; i < lines.length; i++){
                System.out.println(lines[i] + ": " +  queryCounter.get(i) + " " + docslist.get(i));
            }
            System.out.println();
            for (int i = 0; i < args.length-1; i++){
                System.out.println(args[i+1] + ": " + docTracker.get(i) + " " + queriesList.get(i));
            }
        }
    }
}