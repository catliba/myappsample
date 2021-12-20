class User {
  String name;
  String email;
  int followers;
  User(String name, String email, int followers) {
    this.name = name;
    this.email = email;
    this.followers = followers;
  }
}

class Tweet {
  User author;
  String content;
  String tweetId;
  int likes;
  Date date;
  Tweet(User author, String content, String tweetId, int likes, Date date) {
    this.author = author;
    this.content = content;
    this.tweetId = tweetId;
    this.likes = likes;
    this.date = date;
  }

  //if this tweet's Date is before another Tweet, return true. If the other Tweet 
  boolean before(Tweet tweet){
    if (this.date.year < tweet.date.year) { //check the conditions
      return true;
    }
    if (this.date.year > tweet.date.year) {
      return false;
    }
    if (this.date.month < tweet.date.month) {
      return true; //returns true
    }
    if (this.date.month > tweet.date.month) {
      return false;
    }
    if (this.date.day < tweet.date.day) {
      return true;
    }
    if (this.date.day > tweet.date.day) {
      return false;
    }
    return false; //if date is the same, return false
  }
}

class Date {
  int month;
  int day;
  int year;
  Date(int month, int day, int year){
    this.month = month;
    this.day = day;
    this.year = year;
  }
}
class ExamplesTweets {
  Tweet tweet1 = new Tweet(new User("Caleb", "caleb@gmail.com", 0), "I like compsci!", "24623465747", 0, new Date(5, 14,2016));
  Tweet tweet2 = new Tweet(new User("UCSD", "ucsd@ucsd.edu", 125413), "Midterms is this week", "79797777797", 134, new Date(10,14,2020));
  Tweet tweet3 = new Tweet(new User("Bruno Mars", "marsbars@hotman.com", 7777777), "New album dropping soon", "352375463", 3536476, new Date(5,24,2016));
  Tweet tweet4 = new Tweet(new User("Mother", "mom@bing.org", 875443), "Happy New Years!", "3252673453", 3, new Date(1,1,2020)); 

  boolean tweetExample1 = this.tweet2.before(this.tweet4); //false
  boolean tweetExample2 = this.tweet1.before(this.tweet3); //true
  boolean tweetExample3 = this.tweet1.before(this.tweet1); //false
  boolean tweetExample4 = this.tweet4.before(this.tweet1); //false
}