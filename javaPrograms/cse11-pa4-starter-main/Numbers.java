import tester.*;
interface Number {
    int numerator();
    int denominator();
    Number add(Number other);
    Number multiply(Number other);
    String toText();
    double toDouble();
  }

class WholeNumber implements Number{
  int n;
  WholeNumber(int n){
    this.n = n;
  }
  public int numerator() {
    return this.n;
  }
  public int denominator() {
    return 1;
  }
  //return a Number that adds two other Numbers one of this and the other can be Fraction or Whole
  public Number add(Number other){
    Number x = new Fraction((this.numerator() * other.denominator()) + other.numerator(), other.denominator());
    return x;
  }
  public Number multiply(Number other){
    Number x = new Fraction(this.numerator() * other.numerator(), other.denominator());
    return x;
  }
  public String toText(){
    return this.n + "";
  }
  public double toDouble(){
    return (double) this.n;
  }
}
class Fraction implements Number{
  int n, d;
  Fraction(int n, int d){
    this.n = n;
    this.d = d;
  }
  public int numerator() {
    return n;
  }
  public int denominator() {
    return d;
  }
  public Number add(Number other){
    Number x = new Fraction((this.numerator()*other.denominator()) + (other.numerator()*this.denominator()), this.denominator()*other.denominator());
    return x;
  }
  public Number multiply(Number other){
    Number x = new Fraction(this.numerator()*other.numerator(),this.denominator()*other.denominator());
    return x;
  }
  public String toText(){
    return this.n + "/" + this.d;
  }
  public double toDouble() {
    return (double) this.n/this.d;
  }
}

class Numbers {
  Number n1 = new WholeNumber(5);
    Number n2 = new WholeNumber(7);
    Number n3 = new Fraction(7, 2);
    Number n4 = new Fraction(1, 2);

    void testAdd(Tester t) {
        t.checkExpect(this.n1.add(this.n2).toDouble(), 12.0);
        t.checkExpect(this.n1.add(this.n3).toDouble(), 5 + 7.0/2.0);
        t.checkExpect(this.n3.add(this.n3).toDouble(), 7.0);
    }

    void testMultiply(Tester t) {
        t.checkExpect(this.n1.multiply(this.n4).toDouble(), 2.5);
        t.checkExpect(this.n3.multiply(this.n4).toDouble(), 7.0/4.0);
    }

    void testNumDem(Tester t) {
        t.checkExpect(this.n3.numerator(), 7);
        t.checkExpect(this.n1.numerator(), 5);
        t.checkExpect(this.n4.denominator(), 2);
        t.checkExpect(this.n2.denominator(), 1);
    }

    void testToString(Tester t) {
        t.checkExpect(this.n4.toText(), "1/2");
        t.checkExpect(this.n3.toText(), "7/2");
        t.checkExpect(this.n2.toText(), "7");
    }

    //Exploration
  double result = 0.1 + 0.2 + 0.3;
  double result2 = 0.1 + (0.2 + 0.3);

  Number num1 = new Fraction(1,10);
  Number num2 = new Fraction(2,10);
  Number num3 = new Fraction(3,10);

  Number numResult = num1.add(num2).add(num3);
  Number numResult2 = num1.add(num2.add(num3));

  String str = numResult.toText();
  String str2 = numResult2.toText(); 
}
