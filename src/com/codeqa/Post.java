package com.codeqa;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private String title;
    private String body;
    private List<String> tags;
    private String difficulty;
    private String urgency;
    private List<String> comments;
    private static final int MAX_COMMENTS = 5;

    public Post(String title, String body, List<String> tags, String difficulty, String urgency) {
        this.title = title;
        this.body = body;
        this.tags = tags;
        this.difficulty = difficulty;
        this.urgency = urgency;
        this.comments = new ArrayList<>();
    }

    public boolean addPost() {
        if (validateTitle(title) && validateBody(body) && validateTags(tags) && validateDifficulty() && validateUrgency()) {
            try (FileWriter writer = new FileWriter("posts.txt", true)) {
                writer.write(this.toString() + "\n");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean addComment(String comment) {
        if (validateComment(comment)) {
            if (comments.size() < MAX_COMMENTS) {
                comments.add(comment);
                try (FileWriter writer = new FileWriter("comments.txt", true)) {
                    writer.write(comment + "\n");
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private boolean validateTitle(String title) {
        return title.length() >= 10 && title.length() <= 250 && !title.matches("^[0-9!@#\\$%\\^&\\*].*");
    }

    private boolean validateBody(String body) {
        if (difficulty.equals("Very Difficult") || difficulty.equals("Difficult")) {
            return body.length() >= 300;
        }
        return body.length() >= 250;
    }

    private boolean validateTags(List<String> tags) {
        if (tags.size() < 2 || tags.size() > 5) return false;
        for (String tag : tags) {
            if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    private boolean validateDifficulty() {
        if (difficulty.equals("Easy") && tags.size() > 3) {
            return false;
        }
        return true;
    }

    private boolean validateUrgency() {
        if (difficulty.equals("Easy") && (urgency.equals("Immediately Needed") || urgency.equals("Highly Needed"))) {
            return false;
        }
        if ((difficulty.equals("Very Difficult") || difficulty.equals("Difficult")) && urgency.equals("Ordinary")) {
            return false;
        }
        return true;
    }

    private boolean validateComment(String comment) {
        String[] words = comment.split("\\s+");
        return words.length >= 4 && words.length <= 10 && Character.isUpperCase(comment.charAt(0));
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", tags=" + tags +
                ", difficulty='" + difficulty + '\'' +
                ", urgency='" + urgency + '\'' +
                '}';
    }
}
