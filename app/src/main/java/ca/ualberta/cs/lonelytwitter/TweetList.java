package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by Dan on 2018-02-13.
 */

public class TweetList {

   private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

   public void add(Tweet tweet) {
      tweets.add(tweet);
   }

   public void addTweet(Tweet tweet) {

      if (this.checkDuplicateTweet(tweet))
           throw new IllegalArgumentException();
       else this.add(tweet);

   }

   public boolean checkDuplicateTweet(Tweet tweet) {
       int i = tweets.size();
       while (i >= 0) {
           if (tweets.get(i).getMessage() == tweet.getMessage())
               if (tweets.get(i).getDate() == tweet.getDate())
                   return true;
           i--;
       }
       return false;
   }

   public boolean hasTweet(Tweet tweet) {
      return tweets.contains(tweet);
   }

   public Tweet getTweet(int index) {

      return tweets.get(index);

   }
   public ArrayList<Tweet> getTweets() {
       ArrayList<Tweet> returnList = new ArrayList<Tweet>();

       if (tweets.size() == 0) return returnList;

       returnList.add(tweets.get(0));
       int i = 1;
       int j;
       Boolean found;

       while (i <= tweets.size()) {
           j = 0;
           found = false;
           while (!found && j <= returnList.size()) {
               if (returnList.get(j).getDate().after(tweets.get(i).getDate())) {
                   returnList.add(j, tweets.get(i));
                   found = true;
               }
               j++;
           }
           i++;
       }

       return returnList;
   }

   public void delete(Tweet tweet) {

      tweets.remove(tweet);

   }

   public int getCount() {
      return tweets.size();
   }
}
