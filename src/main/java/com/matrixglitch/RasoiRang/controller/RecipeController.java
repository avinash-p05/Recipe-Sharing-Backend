package com.matrixglitch.RasoiRang.controller;

import com.matrixglitch.RasoiRang.model.Recipe;
import com.matrixglitch.RasoiRang.model.User;
import com.matrixglitch.RasoiRang.repository.UserRepository;
import com.matrixglitch.RasoiRang.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/allRecipes")
    public ResponseEntity<Map<String, Object>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "All recipes fetched successfully");
        response.put("data", recipes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllTime")
    public ResponseEntity<Map<String, Object>> getAllTime() {
        List<Recipe> recipes = recipeService.getAllTime();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "All recipes fetched successfully");
        response.put("data", recipes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getByMonth/{month}")
    public ResponseEntity<Map<String, Object>> getByMonth(@PathVariable String month) {
        List<Recipe> recipes = recipeService.getByMonth(month);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "All recipes fetched successfully");
        response.put("data", recipes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getByWeek/{week}")
    public ResponseEntity<Map<String, Object>> getByWeek(@PathVariable String week) {
        List<Recipe> recipes = recipeService.getByWeek(week);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "All recipes fetched successfully");
        response.put("data", recipes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getByDate/{date}")
    public ResponseEntity<Map<String, Object>> getByDate(@PathVariable String date) {
        List<Recipe> recipes = recipeService.getByDate(date);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "All recipes fetched successfully");
        response.put("data", recipes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/userRecipes/{email}")
    public ResponseEntity<Map<String, Object>> getUserRecipes(@PathVariable String email) {
        List<Recipe> recipes = recipeService.getUserRecipes(email);
        Map<String, Object> response = new LinkedHashMap<>();
        if (recipes.isEmpty()) {
            response.put("success", false);
            response.put("message", "No recipes found for the given user");
            response.put("data", null);
            return ResponseEntity.status(404).body(response);
        } else {
            response.put("success", true);
            response.put("message", "User recipes fetched successfully");
            response.put("data", recipes);
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadRecipe(@RequestBody Recipe recipe) {
        Recipe savedRecipe = recipeService.uploadRecipe(recipe);

        // Fetch user details
        Optional<User> userOptional = userRepository.findByEmail(recipe.getUserEmail());
        userOptional.ifPresent(savedRecipe::setUser);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "Recipe uploaded successfully");
        response.put("data", savedRecipe);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/saveRecipe/{email}/{recipeId}")
    public ResponseEntity<Map<String, Object>> saveRecipe(@PathVariable String email, @PathVariable String recipeId) {
        Map<String, Object> response = recipeService.saveRecipe(email, recipeId);

        if ((boolean) response.get("success")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @GetMapping("/savedRecipes/{email}")
    public ResponseEntity<Map<String, Object>> getSavedRecipes(@PathVariable String email) {
        List<Recipe> savedRecipes = recipeService.getSavedRecipes(email);
        Map<String, Object> response = new LinkedHashMap<>();

        if (savedRecipes.isEmpty()) {
            response.put("success", false);
            response.put("message", "No saved recipes found for the given email");
            response.put("data", savedRecipes);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            response.put("success", true);
            response.put("message", "Saved recipes fetched successfully");
            response.put("data", savedRecipes);
            return ResponseEntity.ok(response);
        }
    }


}
