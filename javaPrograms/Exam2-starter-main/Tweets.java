// EXAM INSTRUCTIONS:
// All of your code for Task 2 goes in this file.
// Add new method headers and implementations as appropriate to these classes
// Add examples to the ExamplesTweets class.
import tester.*;
interface Tweet {
  int lengthOfLongestTweetInThread();
  int timesAuthorPostedInThread(User user);  
}
class User {
  String username, displayName;
  User(String username, String displayName) {
    this.username = username;
    this.displayName = displayName;
  }
}
class TextTweet implements Tweet {
  User author;
  String contents;
  int likes;
  TextTweet(User author, String contents, int likes) {
    this.author = author;
    this.contents = contents;
    this.likes = likes;
  }
  public int lengthOfLongestTweetInThread(){
    return contents.length();
  }
  public int timesAuthorPostedInThread(User user){
    if (user == author){
      return 1;
    }
    return 0;
  }
}
class ReplyTweet implements Tweet {
  User author;
  String contents;
  int likes;
  Tweet replyTo;
  ReplyTweet(User author, String contents, int likes, Tweet replyTo) {
    this.author = author;
    this.contents = contents;
    this.likes = likes;
    this.replyTo = replyTo;
  }
  public int lengthOfLongestTweetInThread(){
    if(this.contents.length() > this.replyTo.lengthOfLongestTweetInThread()){
      return this.contents.length();
    }
    else{
      return this.replyTo.lengthOfLongestTweetInThread();
    }
  }
  public int timesAuthorPostedInThread(User user){
    int count = 0;
    if(user == this.author){
      count++;
    }
    count += this.replyTo.timesAuthorPostedInThread(user);
    return count;
  }
}

class ExamplesTweets {
  User joe = new User("joepolitz", "Joe Gibbs Politz");
  User greg = new User("gregory_miranda", "Greg Miranda");
  Tweet t1 = new TextTweet(this.joe, "Java 17 has a cool feature called records", 77);
  Tweet t2 = new ReplyTweet(this.greg, "Hmm I wonder if we could use it for CSE11", 12, this.t1);

  User caleb = new User("CalebLi", "Caleb Li");
  User mom = new User("Mom", "Mother");
  User dad = new User("Dad", "Father");
  Tweet t3 = new TextTweet(this.caleb, "I am finally free, I finished my cse homework!", 15);
  Tweet t4 = new ReplyTweet(this.mom, "Hey caleb, that's great! Can you wash the dishes and clean your room please?", 3, this.t3);
  Tweet t5 = new ReplyTweet(this.dad, "Also, did you finish your math homework as well?", 10, this.t4);
  Tweet t6 = new ReplyTweet(this.caleb, "...", 50, this.t5);

  void testLongestTweetInThread(Tester t) {
    t.checkExpect(this.t2.lengthOfLongestTweetInThread(), 41);
    
    //2 ReplyTweets 1 TextTweets
    t.checkExpect(this.t6.lengthOfLongestTweetInThread(), 76);
    /* class          name                             reference                return value
       TextTweet      lengthOfLongestTweetInThread()   :9                        46
       ReplyTweet     lengthOfLongestTweetInThread()   :10                       76
       ReplyTweet     lengthOfLongestTweetInThread()   :11                       48
       ReplyTweet     lengthOfLongestTweetInThread()   :12                       3
    */
    
  }

  void testAuthorPostedInThread(Tester t) {
    t.checkExpect(this.t1.timesAuthorPostedInThread(joe), 1);
    t.checkExpect(this.t1.timesAuthorPostedInThread(greg), 0);
    t.checkExpect(this.t6.timesAuthorPostedInThread(caleb), 2);
  }
}
    
