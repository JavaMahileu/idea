package com.epam.idea.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.epam.idea.core.util.State;

@Entity
@Table(name = "IDEA")
public class Idea implements Serializable {

    public static final int MIN_LENGTH_TITLE = 1;
    public static final int MAX_LENGTH_TITLE = 150;
    public static final int MAX_LENGTH_DESCRIPTION = 500;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CREATION_TIME", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime creationTime;

    @Column(name = "MODIFICATION_TIME", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime modificationTime;

    @Column(name = "RATING", nullable = false)
    private int rating;

    @Column(name = "LATITUDE", nullable = true)
    private BigDecimal latitude;

    @Column(name = "LONGITUDE", nullable = true)
    private BigDecimal longitude;

    @Column(name = "IMAGE_URL", nullable = true)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User author;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "IDEA_TAG", joinColumns = @JoinColumn(name = "IDEA_ID") , inverseJoinColumns = @JoinColumn(name = "TAG_ID") )
    private List<Tag> relatedTags;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "IDEA_LIKES", joinColumns = @JoinColumn(name = "IDEA_ID") , inverseJoinColumns = @JoinColumn(name = "USER_ID") )
    private List<User> likedUsers;

    @Enumerated(EnumType.STRING)
    private State state;

    @Transient
    private boolean liked;

    public Idea() {
        this.relatedTags = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.likedUsers = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public ZonedDateTime getModificationTime() {
        return modificationTime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(final int rating) {
        this.rating = rating;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(final User author) {
        this.author = author;
    }

    public List<Tag> getRelatedTags() {
        return relatedTags;
    }

    public void setRelatedTags(final List<Tag> relatedTags) {
        this.relatedTags = relatedTags;
    }

    public void addTag(final Tag tag) {
        this.relatedTags.add(tag);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(final Comment comment) {
        this.comments.add(comment);
    }

    public void updateWith(final Idea source) {
        this.title = source.title;
        this.description = source.description;
        this.rating = source.rating;
        this.liked = source.liked;
        this.likedUsers = source.likedUsers;
        this.latitude = source.latitude;
        this.longitude = source.longitude;

        if (source.imageUrl != null) {
            this.imageUrl = source.imageUrl;
        } // if
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(final BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(final BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = ZonedDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        final ZonedDateTime now = ZonedDateTime.now();
        this.creationTime = now;
        this.modificationTime = now;
    }

    @Override
    public String toString() {
        return "Idea{" + "id=" + id + ", title='" + title + '\'' + ", description='" + description + '\'' + ", rating="
                + rating + "state=" + state + ", GPS [" + latitude + "; " + longitude + "]" + '}';
    }

    public List<User> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(final List<User> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public boolean getLiked() {
        return liked;
    }

    public void setLiked(final boolean liked) {
        this.liked = liked;
    }

    public State getState() {
        return state;
    }

    public void setState(final State state) {
        this.state = state;
    }
}
