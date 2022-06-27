package com.kreshnik.FilterExercise.Models;

import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    private int id;
    private String reviewId;
    private String reviewFullText;
    private String reviewText;
    private int numLikes;
    private int numComments;
    private int numShares;
    private int rating;
    private String reviewCreatedOn;
    private Date reviewCreatedOnDate;
    private int reviewCreatedOnTime;
    private String reviewerId;
    private String reviewerUrl;
    private String reviewerName;
    private String reviewerEmail;
    private String sourceType;
    private Boolean isVerified;
    private String source;
    private String sourceName;
    private String sourceId;
    private ArrayList<String> tags;
    private String href;
    private String logoHref;
    private ArrayList<String> photos;
}