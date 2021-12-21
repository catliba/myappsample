class ExamplesMethods {
    //calculate the difference between the larger circle and the inner circle
    double ringArea(double r1, double r2){ //r1 and r2 are values greater than 0
        double areaOfCircle1 = Math.PI*r1*r1;
        double areaOfCircle2 = Math.PI*r2*r2;
        if (r1 > r2) {
            return areaOfCircle1-areaOfCircle2;
        }
        return areaOfCircle2-areaOfCircle1;
    }
    //takes a number and that number represents the number of letters to put in the back
    String rotate(String str, int n) {
        if (str.length() < n){
            return str; //run
        }   
        String firstPart = str.substring(0,n); //Good
        String secondPart = str.substring(n,str.length()); //bye
        return secondPart + firstPart; //byeGood
    }

    double ringAreaExample1 = ringArea(5,10); //expect 235.619449
    double ringAreaExample2 = ringArea(3,3); //expect 0.0
    double ringAreaExample3 = ringArea(2.5,10.75); //expect 343.4153469
    String rotateExample1 = rotate("Hello", 3); //expect loHel
    String rotateExample2 = rotate("Goodbye", 4); //expect byeGood
    String rotateExample3 = rotate("No", 4); //expect No 
    String rotateExample4 = rotate("Area 59", 7); //expect "Area 59"
}