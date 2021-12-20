import tester.*;
interface Tweet {
    public boolean isReplyTo(Tweet other);
    public int totalLikes();
    public String allAuthors();
    public boolean textAppearsOnThread(String text);
}

class User {
    String username;
    String displayName;
    int followers;

    User(String username, String displayName, int followers) {
        this.username = username;
        this.displayName = displayName;
        this.followers = followers;
    }

    String toText() {
        return this.displayName + " " + "@" + this.username;
    }
}

class TextTweet implements Tweet {
    String contents;
    int likes;
    User author;
    TextTweet(User author, String contents, int likes){
        this.contents = contents;
        this.likes = likes;
        this.author = author;
    }
    public boolean isReplyTo(Tweet other){
        return false;
    }
    public int totalLikes(){
        return this.likes;
    }
    public String allAuthors(){
        return this.author.username;
    }
    public boolean textAppearsOnThread(String text){
        if (this.contents.indexOf(text) != -1){
            return true;
        }
        return false;
    }
}
class ReplyTweet implements Tweet {
    String contents;
    int likes;
    User author;
    Tweet replyTo;
    ReplyTweet(User author, String contents, int likes, Tweet replyTo){
        this.contents = contents;
        this.likes = likes;
        this.author = author;
        this.replyTo = replyTo;
    }
    public boolean isReplyTo(Tweet other){
        if (this.replyTo == other){
            return true;
        }
        return false;
    }
    public int totalLikes() {
        return this.likes + this.replyTo.totalLikes();
    }
    public String allAuthors() {
        return this.author.username + ";" + this.replyTo.allAuthors();
    }
    public boolean textAppearsOnThread(String text){
        if (this.contents.indexOf(text) != -1){
            return true;
        }
        else if (this.replyTo.textAppearsOnThread(text) == true){
            return true;
        }
        return false;
    }
} 
class Tweets{
    User joe = new User("joepolitz", "Joe Gibbs Politz", 999);
    User greg = new User("gregory_miranda", "Greg Miranda", 9999);
    User rachel = new User("Rachel__Lim", "Rachel Lim", 1000000);
    Tweet t1 = new TextTweet(this.joe, "Java 17 has a cool feature called records", 77);
    Tweet t2 = new ReplyTweet(this.greg, "Hmm I wonder if we could use it for CSE11", 12, this.t1);
    Tweet t3 = new ReplyTweet(this.greg, "Thought about this more, probably not yet, too new.", 73, this.t2);
    Tweet t4 = new ReplyTweet(this.joe, "Yeah, good point. Maybe in 2022.", 10, this.t3);
    Tweet t5 = new ReplyTweet(this.rachel, "Yeah... I don't want to rewrite the book right this minute", 1005, this.t2);

    User calvin = new User("calvin", "Calvin", 323583);
    User hobbes = new User("therealhobbes", "Hobbes", 925465);
    User mother = new User("mother", "Mother", 265787);
    Tweet t6 = new TextTweet(this.calvin, "I've cleaned my room mom", 100);
    Tweet t7 = new ReplyTweet(this.mother, "Well that was very thoughtful of you", 145, this.t6);
    Tweet t8 = new ReplyTweet(this.calvin, "I did it without you telling me to.", 145, this.t7);
    Tweet t9 = new ReplyTweet(this.calvin, "This isn't going to be a habit or anything", 552, this.t7);
    Tweet t10 = new ReplyTweet(this.hobbes, "Tell your mom to put some conditioner in the washer next time", 3434, this.t6);

    public void testIsReplyTo(Tester t) {
        t.checkExpect(this.t1.isReplyTo(this.t2), false);
        t.checkExpect(this.t2.isReplyTo(this.t1), true);
        t.checkExpect(this.t5.isReplyTo(this.t2), true);
        t.checkExpect(this.t2.isReplyTo(this.t2), false);
        t.checkExpect(this.t4.isReplyTo(this.t3), true);
    }

    public void testIsReplyTo2(Tester t) {
        t.checkExpect(this.t8.isReplyTo(this.t6), false);
        t.checkExpect(this.t9.isReplyTo(this.t6), false);
    }

    public void testTotalLikes(Tester t) {
        t.checkExpect(this.t5.totalLikes(), 1005 + 12 + 77);
        t.checkExpect(this.t4.totalLikes(), 10 + 73 + 12 + 77);
        t.checkExpect(this.t1.totalLikes(), 77);
    }

    public void testTotalLikes2(Tester t) {
        t.checkExpect(this.t10.totalLikes(), 3434 + 100);
        t.checkExpect(this.t9.totalLikes(), 552 + 145 + 100);
    }

    public void testAllAuthors(Tester t) {
        t.checkExpect(this.t1.allAuthors(), "joepolitz");
        t.checkExpect(this.t2.allAuthors(), "gregory_miranda;joepolitz");
        t.checkExpect(this.t3.allAuthors(), "gregory_miranda;gregory_miranda;joepolitz");
        t.checkExpect(this.t5.allAuthors(), "Rachel__Lim;gregory_miranda;joepolitz");
    }
    public void testAllAuthors2(Tester t){
        t.checkExpect(this.t8.allAuthors(), "calvin;mother;calvin");
        t.checkExpect(this.t7.allAuthors(), "mother;calvin");
    }

    public void testTextAppearsOnThread(Tester t) {
        t.checkExpect(this.t1.textAppearsOnThread("joepolitz"), false);
        t.checkExpect(this.t1.textAppearsOnThread("2022"), false);
        t.checkExpect(this.t1.textAppearsOnThread("cool"), true);
        t.checkExpect(this.t4.textAppearsOnThread("wonder"), true);
        t.checkExpect(this.t4.textAppearsOnThread("Java"), true);
        t.checkExpect(this.t4.textAppearsOnThread("rewrite"), false);
        t.checkExpect(this.t4.textAppearsOnThread("2022"), true);
    }
    public void testTextAppearsOnThread2(Tester t) {
        t.checkExpect(this.t10.textAppearsOnThread("mom"), true);
        t.checkExpect(this.t9.textAppearsOnThread("I've"), true);
    }
}