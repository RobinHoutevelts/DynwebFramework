package domain;

public class Question extends Identifiable implements Removable {

    private User user;
    private String text;
    private boolean approved;
    private boolean reviewed;
    private boolean removed;

    public Question(long id, User user, String text, boolean approved,
            boolean reviewed, boolean removed) throws DomainException {
        this.setUser(user);
        this.setText(text);
        this.setId(id);
        this.approved = approved;
        this.reviewed = reviewed;
        this.removed = removed;
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

    @Override
    public void remove() {
        this.removed = true;
    }

    @Override
    public boolean isRemoved() {
        return this.removed;
    }
}
