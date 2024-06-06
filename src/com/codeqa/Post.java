package com.codeqa;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private final String title; // The title of the post
    private final String body; // The body content of the post
    private final List<String> tags; // List of tags associated with the post
    private final String difficulty; // The difficulty level of the post
    private final String urgency; // The urgency level of the post
    private final List<String> comments; // List of comments on the post
    private static final int MAX_COMMENTS = 5; // Maximum number of comments allowed for non-easy posts

    // Constructor to initialize a Post object with given title, body, tags, difficulty, and urgency
    public Post(String title, String body, List<String> tags, String difficulty, String urgency) {
        this.title = title;
        this.body = body;
        this.tags = tags;
        this.difficulty = difficulty;
        this.urgency = urgency;
        this.comments = new ArrayList<>(); // Initialize the comments list
    }

    // Method to add a post if all validations pass
    public boolean addPost() {
        // Validate title, body, tags, difficulty, and urgency
        if (validateTitle(title) && validateBody(body) && validateTags(tags) && validateDifficulty() && validateUrgency()) {
            try (FileWriter writer = new FileWriter("posts.txt", true)) { // Open the posts.txt file in append mode
                writer.write(this + "\n"); // Write the post details to the file
                System.out.println("Post added successfully.");
                return true; // Return true indicating successful addition
            } catch (IOException e) {
                e.printStackTrace(); // Handle any IO exceptions
                System.out.println("Failed to write post to file.");
            }
        } else {
            System.out.println("Failed to add post due to validation errors.");
        }
        return false; // Return false if validations fail or an exception occurs
    }

    // Method to add a comment if it is valid and within the allowed comment limit based on difficulty
    public boolean addComment(String comment) {
        if (validateComment(comment)) { // Validate the comment
            // Check if the comment limit is not exceeded based on the difficulty level
            if ((difficulty.equals("Easy") && comments.size() < 3) || (!difficulty.equals("Easy") && comments.size() < MAX_COMMENTS)) {
                comments.add(comment); // Add the comment to the comments list
                try (FileWriter writer = new FileWriter("comments.txt", true)) { // Open the comments.txt file in append mode
                    writer.write(comment + "\n"); // Write the comment to the file
                    System.out.println("Comment added successfully.");
                    return true; // Return true indicating successful addition
                } catch (IOException e) {
                    e.printStackTrace(); // Handle any IO exceptions
                    System.out.println("Failed to write comment to file.");
                }
            } else {
                System.out.println("Maximum comment limit reached for this post difficulty. Cannot add more comments."); // Print a message if the limit is exceeded
            }
        } else {
            System.out.println("Failed to add comment due to validation errors."); // Print a message if the comment validation fails
        }
        return false; // Return false if validations fail or an exception occurs
    }

    // Validation methods
    // Validate the title based on length and starting character
    private boolean validateTitle(String title) {
        if (title.length() < 10 || title.length() > 250 || title.matches("^[0-9!@#\\$%\\^&\\*].*")) {
            System.out.println("Title validation failed: Title must be 10-250 characters long and not start with a number or special character.");
            return false;
        }
        return true;
    }

    // Validate the body based on length and difficulty
    private boolean validateBody(String body) {
        if ((difficulty.equals("Very Difficult") || difficulty.equals("Difficult")) && body.length() < 300) {
            System.out.println("Body validation failed: For 'Very Difficult' or 'Difficult' posts, the body must be at least 300 characters long.");
            return false;
        }
        if (body.length() < 250) {
            System.out.println("Body validation failed: The body must be at least 250 characters long.");
            return false;
        }
        return true;
    }

    // Validate the tags based on their number, length, and case
    private boolean validateTags(List<String> tags) {
        if (tags.size() < 2 || tags.size() > 5) {
            System.out.println("Tags validation failed: Number of tags must be between 2 and 5.");
            return false;
        }
        for (String tag : tags) {
            if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                System.out.println("Tags validation failed: Each tag must be 2-10 characters long and lowercase.");
                return false;
            }
        }
        return true;
    }

    // Validate the difficulty based on the number of tags
    private boolean validateDifficulty() {
        if (difficulty.equals("Easy") && tags.size() > 3) {
            System.out.println("Difficulty validation failed: 'Easy' posts cannot have more than 3 tags.");
            return false;
        }
        return true;
    }

    // Validate the urgency based on the difficulty
    private boolean validateUrgency() {
        if (difficulty.equals("Easy") && (urgency.equals("Immediately Needed") || urgency.equals("Highly Needed"))) {
            System.out.println("Urgency validation failed: 'Easy' posts cannot have 'Immediately Needed' or 'Highly Needed' urgency.");
            return false;
        }
        if ((difficulty.equals("Very Difficult") || difficulty.equals("Difficult")) && urgency.equals("Ordinary")) {
            System.out.println("Urgency validation failed: 'Very Difficult' or 'Difficult' posts cannot have 'Ordinary' urgency.");
            return false;
        }
        return true;
    }

    // Validate the comment based on word count and capitalization
    private boolean validateComment(String comment) {
        String[] words = comment.split("\\s+");
        if (words.length < 4 || words.length > 10 || !Character.isUpperCase(comment.charAt(0))) {
            System.out.println("Comment validation failed: Comment must be 4-10 words long and start with an uppercase letter.");
            return false;
        }
        return true;
    }

    // Override toString method to return a formatted string representation of the post
    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Body: " + body + "\n" +
                "Tags: " + tags + "\n" +
                "Difficulty: " + difficulty + "\n" +
                "Urgency: " + urgency + "\n";
    }
}
