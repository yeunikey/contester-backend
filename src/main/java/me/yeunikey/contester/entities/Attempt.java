package me.yeunikey.contester.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "attempts")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uniqueId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    public Attempt() {
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
