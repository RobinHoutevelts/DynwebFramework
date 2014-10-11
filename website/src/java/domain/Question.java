package domain;

public class Question {
    
    private long id;
    private User user;
    private String text;
    private boolean approved;
    private boolean reviewed;
    
    public Question(User user, String text) {
        Question.this.setUser(user);
        Question.this.setText(text);
        this.approved = false;
        this.reviewed = false;
    }
    
    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isApproved() {
        return this.approved;
    }

    public void disapprove() {
        this.reviewed = true;
        this.approved = false;
    }
    
    public void approve() {
        this.reviewed = true;
        this.approved = true;
    }
    
    public boolean isReviewed() {
        return this.reviewed;
    }
}
