package com.lawman.inaction.advertisement.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.Date;

@Document(indexName = "advertisement")
public class Advertisement {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Keyword)
    private String description;

    private Double price;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date creationDate;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date lastModifiedDate;

    public Advertisement() {
    }

    public Advertisement(String title, String description, Double price, Date creationDate, Date lastModifiedDate) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Advertisement(String title, String description, Double price) {
        this(title, description, price,Date.from(Instant.now()), Date.from(Instant.now()));
    }

    public Advertisement(String id, String title, String description, Double price, Date creationDate, Date lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
