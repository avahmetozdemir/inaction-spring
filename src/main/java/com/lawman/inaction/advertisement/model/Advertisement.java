package com.lawman.inaction.advertisement.model;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "advertisement")
@Document(indexName = "advertisement")
public class Advertisement {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private Long userId;

    @Field(type = FieldType.Keyword)
    private String title;

    private Set<String> hashtags = new HashSet<>();

    @Field(type = FieldType.Keyword)
    private String description;

    private Double price;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private LocalDateTime creationDate;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private LocalDateTime lastModifiedDate;

    public Advertisement() {
    }

    public Advertisement(String title, String description, Double price, LocalDateTime creationDate, LocalDateTime lastModifiedDate,Long userId,Set<String> hashtags) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.userId= userId;
        this.hashtags=hashtags;
    }

    public Advertisement(String title, String description, Double price,Long userId,Set<String> hashtags) {
        this(title, description, price,LocalDateTime.now(), LocalDateTime.now(), userId,hashtags);
    }

    public Advertisement(String id, String title, String description, Double price, LocalDateTime creationDate, LocalDateTime lastModifiedDate,Set<String> hashtags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.hashtags=hashtags;
    }

    public String getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Set<String> getHashtags() {
        return hashtags;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", hashtags=" + hashtags +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", creationDate=" + creationDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(title, that.title) && Objects.equals(hashtags, that.hashtags) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(creationDate, that.creationDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, hashtags, description, price, creationDate, lastModifiedDate);
    }
}
