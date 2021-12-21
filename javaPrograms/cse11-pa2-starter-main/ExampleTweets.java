
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
class Tweet {
    String content;
    User user;
    int likes;
    String id;

    Tweet(String content, User user, int likes, String id){
        this.content = content;
        this.user = user;
        this.likes = likes;
        this.id = id;
    }

    boolean longerThan(Tweet other){
        if (this.content.length() > other.content.length()){
            return true;
        }
        return false;
    }

    boolean moreLikes(Tweet other){
        if (this.likes > other.likes){
            return true;
        }
        return false;
    }

    String toText() {
        return this.user.displayName + " @" + this.user.username + " : " + this.content + " : " + this.likes + " Likes";
    }

    String toLink() {
        return "https://twitter.com/" + this.user.username + "/status/" + this.id;
    } 
}
class ExampleTweets {
    User twitter = new User("Twitter", "Twitter", 60200000);
    String userinfo = this.twitter.toText();
    //https://twitter.com/Twitter/status/1445078208190291973
    //cannot represent the embedded links within the tweet
    Tweet twitterTweet = new Tweet("hello literally everyone", twitter, 3300000, "1445078208190291973");
    //https://twitter.com/Twitter/status/1443969410440601638
    //cannot embed comment section
    Tweet twitterTweet2 = new Tweet("apparently it's october", twitter, 167000, "1443969410440601638");
    boolean longerTweet = this.twitterTweet.longerThan(this.twitterTweet2);
    boolean moreLikes = this.twitterTweet.moreLikes(this.twitterTweet2);

    User chessChamp = new User("MagnusCarlsen", "Magnus Carlsen", 556200);
    String userinfo2 = this.chessChamp.toText();
    //https://twitter.com/MagnusCarlsen/status/1442453491293818880
    //cannot embed the videos in the comment sections and videos in general
    Tweet chessTweet = new Tweet("Plan for today: Play chess.", chessChamp, 11000, "1442453491293818880");
    String tweetText = this.chessTweet.toText();
    String tweetLink = this.chessTweet.toLink();
    User netflix = new User("netflix", "Netflix", 13400000);
    //https://twitter.com/netflix/status/1446913354061295622
    //Cannot embed the picture
    Tweet squidTweet = new Tweet("Traffic hits different after Squid Game", netflix, 3815, "1446913354061295622");
    String tweetText2 = this.squidTweet.toText();
    String tweetLink2 = this.squidTweet.toLink();

    boolean longerTweet2 = this.squidTweet.longerThan(this.chessTweet);
    boolean moreLikes2 = this.squidTweet.moreLikes(this.chessTweet);
    
}