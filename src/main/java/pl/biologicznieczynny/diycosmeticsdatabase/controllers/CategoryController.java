package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.biologicznieczynny.diycosmeticsdatabase.models.RecipeCategory;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeCategoryRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CategoryController {

    private final RecipeCategoryRepository recipeCategoryRepository;

    public CategoryController(RecipeCategoryRepository recipeCategoryRepository) {this.recipeCategoryRepository = recipeCategoryRepository;}


    @RequestMapping("/recipeCategories")
    public List<RecipeCategory> getRecipeCategoriesList() {
        return recipeCategoryRepository.findAll();
    }
}
