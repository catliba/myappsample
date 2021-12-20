class R{
    String type1;
    R type2;
    R(String type1, R type2){
        this.type1 = type1;
        this.type2 = type2;
    }
}
class ExamplesR{
    R r;
    R classR = new R("Arrrrr", r);
}

/* Output:
-----------------------------------
Tests defined in the class: ExamplesR:
---------------------------
ExamplesR:
---------------
new ExamplesR:1(
 this.r = null
 this.classR = new R:2(
  this.type1 = "Arrrrr"
  this.type2 = null))
---------------
No test methods found.
*/
//This works but the R parameter is defined as a null