import tester.*;

class Pair {
    int a,b;
    Pair(int a, int b){
        this.a = a;
        this.b = b;
    }
}

class ArrayExamples {
    static String joinWith(String[] str, String seperator){
        if (str.length == 0){
            return "";
        }
        String result = "";
        for (String s:str){
            result += s+seperator;
        }
        result = result.substring(0,result.length() - 1);
        return result;
    }
    static boolean somethingFalse(boolean[] booleans){
        if (booleans.length == 0){
            return false;
        }
        boolean result = false;
        for (boolean b:booleans){
            if (b == false){
                result = true;
                break;
            }    
        }
        return result;
    }
     
    static int countWithinRange(double[] dub, double low, double high){
        if (dub.length == 0){
            return 0;
        }
        int count = 0;
        for (double d: dub){
            if (d >= low && d <= high){
                count++;
            }
        }
        return count;
    }
    
    static double[] numsWithinRange(double[] dub, double low, double high){
        if (dub.length == 0){
            return new double[0];
        }
        int count = 0;
        for (double d: dub){
            if (d >= low && d <= high){
                count++;
            }
        }
        double[] result = new double[count];
        count = 0;
        for (int i = 0; i < dub.length; i++){
            if (dub[i] >= low && dub[i] <= high){
                result[count] = dub[i];
                count++;
            }
        }
        return result; 
    }

    static Pair maxmin(int[] nums){
        int lowest = nums[0];
        int highest = nums[0];
        for (int n: nums){
            if (n < lowest)
                lowest = n;
            if (n > highest)
                highest = n;
        }
        return new Pair(lowest, highest);
    }
    
    static String earliest(String[] str){
        String result = str[0];
        for (int i = 0; i< str.length; i++){
            if (result.compareTo(str[i]) > 0){
                result = str[i];
            }
        }
        return result;
    }
}
class ProvidedArrayExamples {
    void testJoinWith(Tester t){ 
      String[] example1 = {"a", "b","c"};
      t.checkExpect(ArrayExamples.joinWith(example1, ":"), "a:b:c");
    }
  
    void testSomethingFalse(Tester t){
      boolean[] example1 = {true, false};
      t.checkExpect(ArrayExamples.somethingFalse(example1), true);
    }
  
    void testCountWithinRange(Tester t){
      double[] example = {0.1, 1.3, 2.6};
      t.checkExpect(ArrayExamples.countWithinRange(example, 1.1, 2.2), 1);
    }
 
    void testNumsWithinRange(Tester t){
      double[] example = {0.0, 3.0, 1.4, 1.5, 2.7, 9.1, 2.1};
      double[] expected = {1.4, 1.5, 2.1};
      t.checkExpect(ArrayExamples.numsWithinRange(example, 1.1, 2.2), expected);
    }
   
    void testMaxmin(Tester t){
      int[] example = {4, 5, 2, 3, 1};
      t.checkExpect(ArrayExamples.maxmin(example), new Pair(1, 5));
    }
   
    void testEarliest(Tester t){
      String[] example = {"aa", "aab", "abcd", "a"};
      t.checkExpect(ArrayExamples.earliest(example), "a");
    }
}

class MyArrayExamples{
    void testJoinWith2(Tester t){
        String[] empty = {};
        t.checkExpect(ArrayExamples.joinWith(empty, "|"), "");
        String[] myExample2 = {"::::", "####","++++"};
        t.checkExpect(ArrayExamples.joinWith(myExample2, "a"), "::::a####a++++");
        String[] myExample3 = {"hello"};
        t.checkExpect(ArrayExamples.joinWith(myExample3, "a"), "hello");
    }
  
    void testSomethingFalse2(Tester t){
        boolean[] myExample1 = {false, false, true, false};
        t.checkExpect(ArrayExamples.somethingFalse(myExample1), true);
        boolean[] myExample2 = {true, true, true};
        t.checkExpect(ArrayExamples.somethingFalse(myExample2), false);
        boolean[] myExample3 = {false};
        t.checkExpect(ArrayExamples.somethingFalse(myExample3), true);
    }
  
    void testCountWithinRange2(Tester t){
        double[] emptyArray = {};
        t.checkExpect(ArrayExamples.countWithinRange(emptyArray, 5, 7), 0);
        double[] myExample = {0, 5, 5.3, 1000};
        t.checkExpect(ArrayExamples.countWithinRange(myExample, 5, 5.5), 2);
        double[] myExample2 = {1,5,2,4,3,6};
        t.checkExpect(ArrayExamples.countWithinRange(myExample2, 1.5, 5.5), 4);
    }

    void testNumsWithinRange2(Tester t){
        double[] emptyArray = {};
        t.checkExpect(ArrayExamples.numsWithinRange(emptyArray, 0, 100), new double[0]);
        double[] myExample = {0,3,5,7,9};
        double[] myExpected = {3,5,7};
        t.checkExpect(ArrayExamples.numsWithinRange(myExample, 3,7), myExpected);
        double[] myExample2 = {10.01, 40.1, 60};
        double[] myExpected2 = {10.01};
        t.checkExpect(ArrayExamples.numsWithinRange(myExample2, 10,40), myExpected2);
    }
   
    void testMaxmin2(Tester t){
        int[] myExample1 = {0, 6, 3, 7 ,10};
        t.checkExpect(ArrayExamples.maxmin(myExample1), new Pair(0, 10));
        int[] myExample2 = {0};
        t.checkExpect(ArrayExamples.maxmin(myExample2), new Pair(0, 0));
        int[] myExample3 = {10, 10, 11};
        t.checkExpect(ArrayExamples.maxmin(myExample3), new Pair(10, 11));
    }
    
    void testEarliest2(Tester t){
        String[] myExample = {"cake", "bake", "rake", "steak"};
        t.checkExpect(ArrayExamples.earliest(myExample), "bake");
        String[] myExample2 = {"one"};
        t.checkExpect(ArrayExamples.earliest(myExample2), "one");
        String[] myExample3 = {"zebra", "same", "same"};
        t.checkExpect(ArrayExamples.earliest(myExample3), "same");
    }
}