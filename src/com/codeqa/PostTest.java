package com.codeqa;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class PostTest {

    @Test
    public void testAddPost_ValidPost() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        assertTrue(post.addPost());
    }

    @Test
    public void testAddPost_InvalidTitle() {
        Post post = new Post("12345", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPost_InvalidBody() {
        Post post = new Post("Valid Title", "Short body",
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPost_InvalidTags() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("T1", "tag2"), "Difficult", "Highly Needed");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPost_InvalidDifficulty() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2", "tag3", "tag4"), "Easy", "Highly Needed");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPost_InvalidUrgency() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Ordinary");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddComment_ValidComment() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        assertTrue(post.addComment("This is a valid comment."));
    }

    @Test
    public void testAddComment_InvalidComment() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        assertFalse(post.addComment("invalid comment."));
    }

    @Test
    public void testAddComment_TooManyComments() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        post.addComment("This is a valid comment.");
        post.addComment("This is another valid comment.");
        post.addComment("Yet another valid comment.");
        post.addComment("Still a valid comment.");
        post.addComment("Valid comment again.");
        assertFalse(post.addComment("This is a valid comment."));
    }
}
