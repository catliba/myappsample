class Point {
    int x; //uninitialized field definitions
    int y;

    //constructor
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    //method
    Point add(Point pointAdded){
        Point adding = new Point(this.x + pointAdded.x, this.y + pointAdded.y);
        return adding;
    }
}
class ExampleLec {
    Point point1 = new Point(4,5);
    Point point2 = new Point(9,1);
    
    Point added = this.point1.add(point2);
    Point added2 = this.point2.add(point1);
}
