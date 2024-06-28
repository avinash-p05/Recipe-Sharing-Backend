package com.matrixglitch.RasoiRang.repository;

import com.matrixglitch.RasoiRang.model.Recipe;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    List<Recipe> findByUserEmail(String email);

    @Query("{ 'date': { $regex: ?0, $options: 'i' } }")
    List<Recipe> findByMonth(String monthPattern, Sort sort);

    @Query("{ 'date': ?0 }")
    List<Recipe> findByDate(String date, Sort sort);

    @Query("{ 'date': { $regex: ?0, $options: 'i' } }")
    List<Recipe> findByWeek(String weekPattern, Sort sort);
}

