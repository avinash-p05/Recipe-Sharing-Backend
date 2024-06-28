package com.matrixglitch.RasoiRang.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
public class User {
    @Id
    String email;
    String username;
    String password;
    List<String> savedRecipes;
    List<String> postedRecipes;

    public List<String> getPostedRecipes() {
        if (postedRecipes == null) {
            postedRecipes = new ArrayList<>();
        }
        return postedRecipes;
    }
    public User() {
        this.savedRecipes = new ArrayList<>();
        this.postedRecipes = new ArrayList<>();
    }


}
