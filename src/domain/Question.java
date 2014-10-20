package domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Questions")
public class Question extends Identifiable implements Removable, Serializable {

    private User user;
    private String text;
    private boolean approved;
    private boolean reviewed;
    private boolean removed;

    protected Question() {
        this(null, null);
    }

    public Question(User user, String text) {
        super();
        Question.this.setUser(user);
        Question.this.setText(text);
        this.approved = false;
        this.reviewed = false;
        this.removed = false;
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
