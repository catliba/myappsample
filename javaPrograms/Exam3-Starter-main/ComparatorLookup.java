import java.util.NoSuchElementException;
import tester.*;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import java.lang.Boolean;

class ComparatorLookupTable<K,V>{
    List<K> keys;
    List<V> values;
    Comparator<K> c;
    public ComparatorLookupTable(List<K> keys, List<V> values, Comparator<K> c){
        this.keys = keys;
        this.values = values;
        this.c = c;
    }

    boolean contains(K key){//This line on stack
        if(this.keys.size() == 0){
            return false;
        }
        for(int i = 0; i < keys.size(); i++){
            if(c.compare(keys.get(i), key) == 0)
                return true;
        }
        return false;
    }
    void add(K key, V value){
        if(this.keys.contains(key)){
            throw new IllegalArgumentException();
        }
        keys.add(key);
        values.add(value);
    }

    V find(K key){//This line on stack
        if(!this.keys.contains(key)){
            throw new NoSuchElementException();
        }
        int i = keys.indexOf(key);
        return values.get(i);
    }

    void update(K key, V value){
        if(!this.keys.contains(key)){
            throw new NoSuchElementException();
        }
        int i = keys.indexOf(key);
        values.set(i, value);
    }
}

class StringComparator implements Comparator<String> {//This line on stack
    public int compare(String s1, String s2) {
      return s1.compareTo(s2);
    }
}
class ComparatorLookupTableExamples {
    void testUpdate(Tester t) {
        List<String> strs = new ArrayList<>(Arrays.asList("a", "b", "c"));
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3));
        ComparatorLookupTable<String, Integer> ctl = new ComparatorLookupTable<>(strs, nums, new StringComparator());
        t.checkExpect(ctl.contains("a"), true);
        ctl.update("a", 9);
        t.checkExpect(ctl.find("a"), 9);
        ctl.add("z", 10);
        t.checkExpect(ctl.keys, Arrays.asList("a", "b", "c", "z"));
        t.checkExpect(ctl.values, Arrays.asList(9, 2, 3, 10));
  
        t.checkException(new IllegalArgumentException(), ctl, "add", "z", 5);
        t.checkException(new NoSuchElementException(), ctl, "find", "y");
    }
}

class Examples {
    List<String> strs = new ArrayList<>(Arrays.asList("apples", "bananas", "cucumber"));
    List<Integer> nums = new ArrayList<>(Arrays.asList(10, 22, 1));
    ComparatorLookupTable<String, Integer> ctl = new ComparatorLookupTable<>(strs, nums, new StringComparator());

    void testFind(Tester t){
        t.checkExpect(ctl.find("oranges"), 1); //This line on stack
    }
}
/*
class                              method          this reference          other variables
Examples                            testFind        <ignore>                strs=:2 nums=:3 ctl=:4 new StringComparator=:5
ComparatorLookupTable                 find           :4                      key = "oranges"
ComparatorLookupTable                contains        :4                      key = "oranges" i=0 i=3
StringCompare                          compare        :5                       s1=keys.get(i),s2="oranges"


*/