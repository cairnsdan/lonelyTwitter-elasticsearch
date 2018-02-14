package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dan on 2018-02-13.
 */

public class TweetListTest extends ActivityInstrumentationTestCase2 {

    public TweetListTest() {
        super(LonelyTwitterActivity.class);
    }

    public void testAddTweet() {

        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("adding tweet");
        tweets.add(tweet);
        assertTrue(tweets.hasTweet(tweet));
    }

    public void testHasTweet() {

        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("hello");
        assertFalse(tweets.hasTweet(tweet));
        tweets.add(tweet);
        assertTrue(tweets.hasTweet(tweet));
    }

    public void testGetTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("hello");

        tweets.add(tweet);
        Tweet returnedTweet = tweets.getTweet(0);
        assertEquals(returnedTweet.getMessage(), tweet.getMessage());
        assertEquals(returnedTweet.getDate(), tweet.getDate());

    }

    public void testDeleteTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("hello");

        tweets.add(tweet);
        tweets.delete(tweet);
        assertFalse(tweets.hasTweet(tweet));
    }

    public void testAddTweetDuplicate() {
        TweetList tweets = new TweetList();

        Date date = new Date();
        Tweet tweet = new NormalTweet("hello", date);
        Tweet tweet2 = new NormalTweet("hello", date);

        tweets.addTweet(tweet);
        try {
            tweets.addTweet(tweet2);
        } catch (IllegalArgumentException e) {
            assertTrue(Boolean.TRUE);
        }
    }

    public void testGetTweetsChrono() {
        TweetList tweets = new TweetList();

        Date olddate = new Date("0");
        Date newdate = new Date();
        Tweet oldTweet = new NormalTweet("old", olddate);
        Tweet newTweet = new NormalTweet("new", newdate);

        tweets.add(newTweet);
        tweets.add(oldTweet);

        ArrayList<Tweet> returnedList;
        returnedList = tweets.getTweets();

        assertTrue(returnedList.get(0).getDate().before(returnedList.get(1).getDate()));
    }

    public void testGetCount() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("test");

        assertEquals(0, tweets.getCount());

        tweets.add(tweet);

        assertEquals(1, tweets.getCount());

        tweets.add(tweet);

        assertEquals(2, tweets.getCount());

        tweets.delete(tweet);

        assertEquals(1, tweets.getCount());

        for (int i = 0; i < 10; i++)
            tweets.add(tweet);

        assertEquals(11, tweets.getCount());


    }

}
