package com.lawman.inaction.advertisement.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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



}
