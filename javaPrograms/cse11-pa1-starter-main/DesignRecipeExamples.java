import tester.*;
  /*
  Write the method header
  Write a short comment documenting the method (including assumptions, like if a parameter should take values only in a certain range)
  Write examples of calling the method
  Write the method body
  */

class DesignRecipeExamples {
  //This method will find the perimeter of the rectangle. No doubles or values <= 0
  public int perimeter(int width, int height) { 
    return width * 2 + height * 2;
  }
  int example1 = this.perimeter(4,5); //perimeter of this rectangle should be 18
  int example2 = this.perimeter(10,10); //perimeter of this should be 40

  //This method will take two rectangles by their width and height, one of the rectangles inside the other. 
  //It will then calculate the area of the region between the two.
  //The inner rectangle's length and heigth cannot exceed the outer rectangle's. Both heigth and width must be > 0 and are integers.
  public int borderArea(int width, int height, int innerWidth, int innerHeight){
    return (width*height)-(innerWidth*innerHeight);
  }
  int example3 = this.borderArea(10,10,5,5); //expect 75
  int example4 = this.borderArea(20,15,7,3); //expect 279

  //Will take yards and approximates it to feet. yards parameter must be > 0 and be an int since problem asked for int parameter and return.
  public int yardsToFeet(int yards){
    return yards*3;
  }
  int converter1 = this.yardsToFeet(100); //100 yards is 300 feet
  int converter2 = this.yardsToFeet(500); //500 yards is 1500 feet

  //Will take feet and inches and combine them to total inches. The int values feet and inches should be > 0.
  public int totalInches(int feet, int inches){
    return feet*12 + inches;
  }
  int totalLength1 = totalInches(5,6); //5 feet and 6 inches would be 66 inches
  int totalLength2 = totalInches(12, 5); //12 feet and 5 inches is 149 inches\
}

class TestDrive{
  String s = "hello&yello&cello";
  String[] sArray = s.split("&",2);

}