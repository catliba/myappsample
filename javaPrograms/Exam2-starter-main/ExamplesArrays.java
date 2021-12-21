// EXAM INSTRUCTIONS:
// All of your code for Task 1 goes in this file.
import tester.*;
class Pair{
    int a,b;
    Pair(int a, int b){
        this.a = a;
        this.b = b;
    }
}
class ExamplesArrays{
    double averageWithoutLowest(double[] nums){
        if (nums.length == 0){
            return 0.0;
        }
        double total = 0;
        double lowest = nums[0]; //16
        for (int i = 0; i < nums.length; i++){
            total += nums[i]; 
            if (lowest > nums[i])
                lowest = nums[i]; //new value of 4 when i = 1
        }
        /* {16.0, 4.0, 4.0, 20.0} length 4
        i start   i end   total start   total end
            0       1         0               16 
            1        2         16             20
            2        3             20          24
            3        4           24           44
            4
        */ 
        return (total-lowest)/(nums.length-1); //40/3
    }

    int[] sumOfPairs(Pair[] pairs){
        int[] arrOfSums = new int[pairs.length];
        for (int i = 0; i< pairs.length; i++){
            arrOfSums[i] = pairs[i].a + pairs[i].b;
        }
        return arrOfSums;
    }

    Region[] regionsWithPoint(Region[] region, Point point){
        int count = 0;
        int counter = 0;
        for (int i = 0; i < region.length; i++){
            if (region[i].contains(point)){
                count++;
            }
        }
        Region[] containedPoints = new Region[count];
        for (int i = 0; i < region.length; i++){
            if (region[i].contains(point)){
                containedPoints[counter] = region[i];
                counter++; 
            }
        }
        return containedPoints;
    }

    void testAverageWithoutLowest(Tester t) {
		double[] unique = {1.0,2.0,3.0};
		t.checkExpect(averageWithoutLowest(unique), 2.5);
        double[] empty = new double[0];
        t.checkExpect(averageWithoutLowest(empty), 0.0);
        double[] videoTask = {16.0, 4.0, 4.0, 20.0}; //length = 4
        t.checkExpect(averageWithoutLowest(videoTask), 40.0/3.0);
	}
    void testRegionsWithPoint(Tester t) {
		Region[] regions = {new CircleRegion(new Point(0, 0), 5), new CircleRegion(new Point(0, 0), 10)};
		Region[] result = {new CircleRegion(new Point(0, 0), 10)};
		t.checkExpect(regionsWithPoint(regions, new Point(9, 0)), result);
        Region[] region2 = {new RectRegion(new Point(5,5), new Point(10,10)), new RectRegion(new Point(0,0), new Point(10,10)), new CircleRegion(new Point(0,0), 10)};
        Region[] result2 = {new RectRegion(new Point(0,0), new Point(10,10)), new CircleRegion(new Point(0,0), 10)};
        t.checkExpect(regionsWithPoint(region2, new Point(5,5)), result2); //exclusive
	}
	void testSumOfPairs(Tester t) {
		Pair[] pairs = {new Pair(1, 2), new Pair(3, 4)};
		int[] result = {3, 7};
		t.checkExpect(sumOfPairs(pairs), result);
        Pair[] pairs2 = {new Pair(0,0)};
        int[] result2 = {0};
        t.checkExpect(sumOfPairs(pairs2), result2);
	}
}