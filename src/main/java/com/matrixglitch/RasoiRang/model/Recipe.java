package com.matrixglitch.RasoiRang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    private String id;
    private String title;
    private String type;
    private String ingredients;
    private String steps;
    private String tags;
    private int saves;
    private String imageUrl;
    private String date;
    private String userEmail;
    private String userName;

    @Setter
    @Transient
    private User user;

}

