package com.codeqa;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class PostTest {

    // Test case to verify adding a valid post
    @Test
    public void testAddPost_ValidPost() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        assertTrue(post.addPost()); // Expecting true as the post is valid
    }

    // Test case to verify adding a post with an invalid title
    @Test
    public void testAddPost_InvalidTitle() {
        Post post = new Post("12345", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        assertFalse(post.addPost()); // Expecting false as the title is invalid
    }

    // Test case to verify adding a post with an invalid body
    @Test
    public void testAddPost_InvalidBody() {
        Post post = new Post("Valid Title", "Short body",
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        assertFalse(post.addPost()); // Expecting false as the body is too short
    }

    // Test case to verify adding a post with invalid tags
    @Test
    public void testAddPost_InvalidTags() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("T1", "tag2"), "Difficult", "Highly Needed");
        assertFalse(post.addPost()); // Expecting false as one of the tags is invalid
    }

    // Test case to verify adding an 'Easy' post with more than 3 tags
    @Test
    public void testAddPost_TooManyTagsForEasyPost() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2", "tag3", "tag4"), "Easy", "Ordinary");
        assertFalse(post.addPost()); // Expecting false because an "Easy" post cannot have more than 3 tags
    }

    // Test case to verify adding a post with invalid urgency
    @Test
    public void testAddPost_InvalidUrgency() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Ordinary");
        assertFalse(post.addPost()); // Expecting false as the urgency is invalid for the given difficulty
    }

    // Test case to verify adding a valid comment
    @Test
    public void testAddComment_ValidComment() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        assertTrue(post.addComment("This is a valid comment.")); // Expecting true as the comment is valid
    }

    // Test case to verify adding an invalid comment
    @Test
    public void testAddComment_InvalidComment() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        assertFalse(post.addComment("invalid comment.")); // Expecting false as the comment is invalid
    }

    // Test case to verify adding a comment that exceeds the word limit
    @Test
    public void testAddComment_CommentExceedsWordLimit() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        assertFalse(post.addComment("This is a very valid comment. It exceeds the word limit.")); // Expecting false as the comment exceeds the word limit
    }

    // Test case to verify adding a sixth comment to a post
    @Test
    public void testAddComment_AddingSixthComment() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        post.addComment("This is valid content 1.");
        post.addComment("This is valid content 2.");
        post.addComment("This is valid content 3.");
        post.addComment("This is valid content 4.");
        post.addComment("This is valid content 5.");
        assertFalse(post.addComment("This is valid content 6.")); // Expecting false as the comment exceeds the maximum limit for non-easy posts
    }

    // Test case to verify adding a comment that is too short
    @Test
    public void testAddComment_TooShortComment() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Difficult", "Highly Needed");
        assertFalse(post.addComment("Too short.")); // Expecting false as the comment is too short
    }

    // Test case to verify adding a fourth comment to an "Easy" post
    @Test
    public void testAddComment_TooManyCommentsForEasyPost() {
        Post post = new Post("Valid Title", "This is a valid body with more than 250 characters. ".repeat(10),
                Arrays.asList("tag1", "tag2"), "Easy", "Ordinary");
        post.addComment("This is valid comment 1.");
        post.addComment("This is valid comment 2.");
        post.addComment("This is valid comment 3.");
        assertFalse(post.addComment("This is a valid comment for an Easy post.")); // Expecting false as the comment exceeds the maximum limit for easy posts
    }
}
