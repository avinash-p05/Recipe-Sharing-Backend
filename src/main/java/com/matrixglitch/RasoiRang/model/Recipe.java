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



//date
//"2024-02-26"
//        (string)
//
//day
//"27"
//        (string)
//
//imageUrl
//"https://firebasestorage.googleapis.com/v0/b/recipesharingapp-c7ae9.appspot.com/o/recipe_images%2F17f45747-3390-4b3b-8922-c421123ea0a4?alt=media&token=cd8b7457-bf1e-4a02-a7f0-1edbfc9a4ddd"
//        (string)
//
//ingredients
//"• 200g spaghetti\n• 2 large eggs\n• 50g pecorino cheese, finely grated\n• 50g parmesan cheese, finely grated\n•"
//        (string)
//
//month
//"1"
//        (string)
//
//saves
//"3.2"
//        (string)
//
//steps
//"1. Cook the spaghetti according to the package instructions until al dente.\n2. While the spaghetti is cooking, whisk together the eggs, pecorino cheese, and parmesan cheese in a bowl.\n3. In a separate pan, cook the pancetta or guanciale until crispy. Add the minced garlic and cook for another minute.\n"
//        (string)
//
//tags
//"#Italian#Pasta#Carbonara"
//        (string)
//
//title
//"Classic Spaghetti Carbonara"
//        (string)
//
//type
//"Veg Main Courses"
//        (string)
//
//userId
//"M51FUxSuiiZpqmWi8FjncCgCouc2"
//        (string)
//
//userName
//"Aaron Dsouza"
//        (string)
//
//week
//"4"