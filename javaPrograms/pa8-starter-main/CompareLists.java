import tester.*;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;
import java.lang.Boolean;

class Point {
  int x, y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}

class PointCompare implements Comparator<Point> {
  public int compare(Point p1, Point p2){
    if (p1.y < p2.y) return -1;
    if (p1.y > p2.y) return 1;
    if (p1.y == p2.y && p1.x < p2.x) return -1;
    if (p1.y == p2.y && p1.x > p2.x) return 1;
    return 0;
  }

  void testPointCompare(Tester t){
    Point p1 = new Point(5,5);
    Point p2 = new Point(10,10);
    Point p3 = new Point(10,5);
    Point p4 = new Point(5,10);
    t.checkExpect(compare(p1,p2), -1);
    t.checkExpect(compare(p2,p4), 1);
    t.checkExpect(compare(p1,p3), -1);
    t.checkExpect(compare(p2,p2), 0);
  }
}

class PointDistanceCompare implements Comparator<Point> {
  public int compare(Point p1, Point p2){
    if (p1.distance(new Point(0,0)) > p2.distance(new Point(0,0))) return 1;
    if (p1.distance(new Point(0,0)) < p2.distance(new Point(0,0))) return -1;
    return 0;
  }

  void testPointDistanceCompare(Tester t){
    Point p1 = new Point(5,5);
    Point p2 = new Point(10,10);
    t.checkExpect(compare(p1,p1), 0);
    t.checkExpect(compare(p1,p2), -1);
    t.checkExpect(compare(p2,p1), 1);
    t.checkExpect(compare(p2,p2), 0);
  }
}

class StringCompare implements Comparator<String>{
  public int compare(String s1, String s2){
    return s1.compareTo(s2);
  }

  void testStringCompare(Tester t){
    String s1 = "abc";
    String s3 = "abcd";
    String s2 = "aab";
    t.checkExpect(compare(s1,s1), 0);
    t.checkExpect(compare(s1,s2), 1);
    t.checkExpect(compare(s2,s1), -1);
    t.checkExpect(compare(s1, s3), -1);
  }
}

class StringLengthCompare implements Comparator<String>{
  public int compare(String s1, String s2){
    if (s1.length() > s2.length()) return 1;
    if (s1.length() < s2.length()) return -1;
    return 0;
  }

  void testStringLengthCompare(Tester t){
    String s1 = "longer";
    String s2 = "short";
    String s3 = "samlen";
    t.checkExpect(compare(s1,s2), 1);
    t.checkExpect(compare(s2,s1), -1);
    t.checkExpect(compare(s1,s3), 0);
    t.checkExpect(compare(s2,s2), 0);
  }
}

class BooleanCompare implements Comparator<Boolean>{
  public int compare(Boolean b1, Boolean b2){
    if (b1 && !b2) return 1;
    if (b2 && !b1) return -1;
    return 0;
  }

  void testBooleanCompare(Tester t){
    boolean b1 = true;
    boolean b2 = false;
    t.checkExpect(compare(b1,b2), 1);
    t.checkExpect(compare(b2,b1), -1);
    t.checkExpect(compare(b1,b1), 0);
    t.checkExpect(compare(b2,b2), 0);
  }
}

class CompareLists {
  <E> E minimum(List<E> list, Comparator<E> c){
    if (list.size() == 0) return null;
    Collections.sort(list,c);
    return list.get(0);
  }

  void testMinimum(Tester t){
    List<String> strs = new ArrayList<>();
    strs.add("banana");
    strs.add("pie");
    strs.add("apple");
    Comparator<String> compareStringLength = new StringLengthCompare();
    t.checkExpect(minimum(strs,compareStringLength), "pie");
    List<Boolean> bools = new ArrayList<>();
    bools.add(true); bools.add(false); bools.add(true); bools.add(false); bools.add(true); bools.add(true);
    Comparator<Boolean> compareBooleans = new BooleanCompare();
    t.checkExpect(minimum(bools, compareBooleans), false);
    List<Point> pts = new ArrayList<>();
    Comparator<Point> comparePointDist = new PointDistanceCompare();
    t.checkExpect(minimum(pts, comparePointDist), null);
  }

  <E> E minimum(E[] e, Comparator<E> c){
     if (e.length == 0){
       return null;
     }
    for (int i = 0; i < e.length; i++){
      for (int j = 0; j < e.length-1; j++){
        if (c.compare(e[j], e[j+1]) == 1){
          E placeholder = e[j];
          e[j] = e[j+1];
          e[j+1] = placeholder;
        }
      }
    }
    return e[0];
  }

  void testArrayMinimum(Tester t){
    String[] strs = {"banana", "peach", "pie", "apple"};
    Comparator<String> compareStringLength = new StringLengthCompare();
    t.checkExpect(minimum(strs,compareStringLength), "pie");
    Point[] points = {new Point(10,10), new Point(5,0), new Point(-1,-1)};
    Comparator<Point> comparePointDist = new PointDistanceCompare();
    t.checkExpect(minimum(points, comparePointDist), new Point(-1,-1));
    String[] emptyStrs = {};
    t.checkExpect(minimum(emptyStrs, compareStringLength), null);
  }

  <E> List<E> greaterThan(List<E> list, Comparator<E> c, E e){
    List<E> greaterThans = new ArrayList<>();
    for (E x : list){
      if (c.compare(x, e) > 0)
        greaterThans.add(x);
    }
    return greaterThans;
  }

  void testGreaterThan(Tester t){
    List<String> lst = new ArrayList<>();
    lst.add("apple");
    lst.add("banana");
    lst.add("cauliflower");
    lst.add("cherry");
    lst.add("pie");
    lst.add("cake");
    String largerThan = "four";
    List<String> expectedList = new ArrayList<>();
    expectedList.add("apple");
    expectedList.add("banana");
    expectedList.add("cauliflower");
    expectedList.add("cherry");
    Comparator<String> compareStringLength = new StringLengthCompare();
    t.checkExpect(greaterThan(lst, compareStringLength, largerThan), expectedList);
    Comparator<Point> p = new PointCompare();
    List<Point> points = new ArrayList<>();
    Point largerThanPoint = new Point(5,5);
    points.add(new Point(1,1));
    points.add(new Point(5,6));
    points.add(new Point(6,6));
    points.add(new Point(6,5));
    points.add(new Point(4,5));
    List<Point> expectedPoints = new ArrayList<>();
    expectedPoints.add(new Point(5,6));
    expectedPoints.add(new Point(6,6));
    expectedPoints.add(new Point(6,5));
    t.checkExpect(greaterThan(points, p, largerThanPoint), expectedPoints);
    List<Boolean> empty = new ArrayList<>();
    Comparator<Boolean> compareBools = new BooleanCompare();
    Boolean b = true;
    t.checkExpect(greaterThan(empty, compareBools, b), empty);
  }

  <E> boolean inOrder(List<E> list, Comparator<E> c){
    boolean ascending = true;
    for (int i = 0; i < list.size()-1; i++){
      if (list.get(i) == null || list.get(i+1) == null){
        throw new IllegalArgumentException("null value in list");
      }
      if (c.compare(list.get(i), list.get(i+1)) == 1) ascending = false;
    }
    return ascending;
  }

  void testInOrder(Tester t){
    Comparator<Boolean> b = new BooleanCompare();
    Comparator<String> s = new StringLengthCompare();
    Comparator<Point> p = new PointDistanceCompare();
    List<Boolean> booleansInOrder =  Arrays.asList(false, false, false, true);
    List<String> StringsInOrder = Arrays.asList("o", "on", "one");
    List<Point> PointsNotInOrder = Arrays.asList(new Point(1,0), new Point(0,0), new Point(10,10));
    t.checkExpect(inOrder(booleansInOrder, b), true);
    t.checkExpect(inOrder(StringsInOrder, s), true);
    t.checkExpect(inOrder(PointsNotInOrder, p), false);
    List<Point> nullPoints = Arrays.asList(new Point(0,0), null);
    t.checkException(new IllegalArgumentException("null value in list"), this, "inOrder", nullPoints, p);
  }

  <E> boolean inOrder(E[] e, Comparator<E> c){
    boolean ascending = true;
    for (int i = 0; i < e.length-1; i++){
      if (e[i] == null || e[i+1] == null) throw new IllegalArgumentException("null value in array");
      if (c.compare(e[i],e[i+1]) == 1) ascending = false;
    }
    return ascending;
  }

  void testInOrderArray(Tester t){
    Comparator<Boolean> b = new BooleanCompare();
    Comparator<String> s = new StringLengthCompare();
    Comparator<Point> p = new PointDistanceCompare();
    Boolean[] booleansNotInOrder = {true, false, false, false, true};
    String[] stringInOrder = {"e", "eeee", "eeee"};
    Point[] pointInOrder = {new Point(0,0), null};
    t.checkExpect(this.inOrder(booleansNotInOrder, b), false);
    t.checkExpect(inOrder(stringInOrder, s), true);
    t.checkException(new IllegalArgumentException("null value in array"), this, "inOrder", pointInOrder, p);
  }

  <E> List<E> merge(Comparator<E> c, List<E> list1, List<E> list2){  //list1 and list2 should be in ascending order
    List<E> newList = new ArrayList<>();
    for (int i = 0; i < list1.size(); i++){
      if(list1.get(i) == null) throw new IllegalArgumentException("null value in first list");
      newList.add(list1.get(i));
    }
    for (int i = 0; i < list2.size(); i++){
      if(list2.get(i) == null) throw new IllegalArgumentException("null value in second list");
      newList.add(list2.get(i));
    }
    Collections.sort(newList,c);
    return newList;
  }

  void testMerge(Tester t){
    Comparator<String> s = new StringCompare();
    Comparator<Boolean> b = new BooleanCompare();
    Comparator<Point> p = new PointCompare();
    List<Boolean> lst1 = Arrays.asList(false, false, false, true, true);
    List<Boolean> lst2 = Arrays.asList(false,true);
    List<Boolean> expectedBools = Arrays.asList(false, false, false, false, true, true, true);
    t.checkExpect(merge(b, lst1, lst2), expectedBools);
    List<String> lst3 = Arrays.asList("apple", "banana", "cucumber");
    List<String> lst4 = Arrays.asList("apricot", "blueberry", "celery");
    List<String> expectedStrs = Arrays.asList("apple", "apricot", "banana", "blueberry", "celery", "cucumber");
    t.checkExpect(merge(s, lst3, lst4), expectedStrs);
    List<Point> lst5 = Arrays.asList(new Point(0,0), new Point(0,1), new Point(1,1));
    List<Point> lst6 = Arrays.asList(new Point(-1,0), new Point(1,0), new Point(1,1));
    List<Point> expectedPoints = Arrays.asList(new Point(-1,0), new Point(0,0), new Point(1,0), new Point(0,1), new Point(1,1), new Point(1,1));
    t.checkExpect(merge(p, lst5, lst6), expectedPoints);
    List<Point> lst7 = Arrays.asList(new Point(0,0), null);
    t.checkException(new IllegalArgumentException("null value in first list"), this, "merge", p, lst7, lst5);
    t.checkException(new IllegalArgumentException("null value in second list"), this, "merge", p, lst6, lst7);
  }
} 