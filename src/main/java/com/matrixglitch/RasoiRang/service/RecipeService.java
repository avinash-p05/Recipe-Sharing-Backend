package com.matrixglitch.RasoiRang.service;

import com.matrixglitch.RasoiRang.model.Recipe;
import com.matrixglitch.RasoiRang.model.User;
import com.matrixglitch.RasoiRang.repository.RecipeRepository;
import com.matrixglitch.RasoiRang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> getAllTime() {
        // The regex pattern to match dates with the specified month (e.g., "-06-")
        return recipeRepository.findAll(Sort.by(Sort.Order.desc("saves")));
    }

    public List<Recipe> getByMonth(String month) {
        // The regex pattern to match dates with the specified month (e.g., "-06-")
        String monthPattern = String.format("-%02d-", Integer.parseInt(month));
        return recipeRepository.findByMonth(monthPattern, Sort.by(Sort.Order.desc("saves")));
    }

    public List<Recipe> getByDate(String date) {
        return recipeRepository.findByDate(date, Sort.by(Sort.Order.desc("saves")));
    }

    public List<Recipe> getByWeek(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);

            // The regex pattern to match dates in the same week of the year
            String weekPattern = String.format("%d-W%02d", calendar.get(Calendar.YEAR), weekOfYear);
            return recipeRepository.findByWeek(weekPattern, Sort.by(Sort.Order.desc("saves")));
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format. Please use dd-MM-yyyy.");
        }
    }

    public List<Recipe> getUserRecipes(String email) {
        List<Recipe> recipes = recipeRepository.findByUserEmail(email);
        return recipes.stream()
                .peek(recipe -> {
                    Optional<User> user = userRepository.findById(recipe.getUserEmail());
                    user.ifPresent(recipe::setUser);
                })
                .collect(Collectors.toList());
    }

    public Recipe uploadRecipe(Recipe recipe) {
        Recipe savedRecipe = recipeRepository.save(recipe);

        // Update the user's postedRecipes list
        userRepository.findByEmail(recipe.getUserEmail()).ifPresent(user -> {
            user.getPostedRecipes().add(savedRecipe.getId());
            userRepository.save(user);
        });

        return savedRecipe;
    }


    public Map<String, Object> saveRecipe(String email, String recipeId) {
        Map<String, Object> response = new HashMap<>();

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            response.put("success", false);
            response.put("message", "User not found");
        } else {
            User user = userOptional.get();
            if (user.getSavedRecipes() == null) {
                user.setSavedRecipes(new ArrayList<>());
            }

            if (!user.getSavedRecipes().contains(recipeId)) {
                user.getSavedRecipes().add(recipeId);
                userRepository.save(user);
                response.put("success", true);
                response.put("message", "Recipe Saved Successfully");
            } else {
                response.put("success", false);
                response.put("message", "Recipe already saved");
            }
        }

        return response;
    }

    public List<Recipe> getSavedRecipes(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            return new ArrayList<>();
        }

        User user = userOptional.get();
        if (user.getSavedRecipes() == null) {
            return new ArrayList<>();
        }

        List<Recipe> savedRecipes = new ArrayList<>();
        for (String recipeId : user.getSavedRecipes()) {
            recipeRepository.findById(recipeId).ifPresent(savedRecipes::add);
        }

        return savedRecipes;
    }

}
