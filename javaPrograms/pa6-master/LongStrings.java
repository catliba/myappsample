import tester.*;
class LongStrings {
    String[] longStrings(String[] strs, int n){
        int greaterThanN = 0;
        for (int i = 0; i < strs.length; i++){
            if (strs[i].length() >= n)
                greaterThanN++;
        }
        String[] longStringsOnly = new String[greaterThanN];
        int count = 0;
        for (int i = 0; i < strs.length; i++){
            if (strs[i].length() >= n){
                longStringsOnly[count] = strs[i];
                count++;
            }
        }
        return longStringsOnly;
    }

    void testLongStrings(Tester t) {
        String[] test1 = {"one", "two", "three", "four"};
        String[] result1 = {"three", "four"};
        t.checkExpect(longStrings(test1, 4), result1);
        String[] test2 = {};
        String[] result2 = {};
        t.checkExpect(longStrings(test2, 2), result2);
        String[] test3 = {"hello", "goodbye"};
        String[] result3 = {};
        t.checkExpect(longStrings(test3, 10), result3);
        String[] test4 = {"a", "abb", "aa", "abb", "abbbb"};
        String[] result4 = {"abb", "abb", "abbbb"};
        t.checkExpect(longStrings(test4, 3), result4);
      }
}


