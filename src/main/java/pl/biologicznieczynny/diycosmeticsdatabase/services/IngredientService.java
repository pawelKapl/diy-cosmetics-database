package pl.biologicznieczynny.diycosmeticsdatabase.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.NotFoundException;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.IngredientRepository;

import java.net.URI;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {this.ingredientRepository = ingredientRepository;}

    public Page<Ingredient> findAll(int page, int size, String sort) {

        Pageable pageable;
        if (sort.equals("asc")) {
            pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by("name").descending());
        }

        return ingredientRepository.findAll(pageable);
    }

    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient with id" + id + " not found"));
    }


    public ResponseEntity addNewIngredient(Ingredient ingredient) {
        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedIngredient.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(savedIngredient);
    }

    public void deleteIngredientById(Long id) {
        if (ingredientRepository.existsById(id)) {
            ingredientRepository.deleteById(id);
        } else {
            throw new NotFoundException("Ingredient with id: " + id + " already does not exist");
        }
    }

    public ResponseEntity<Ingredient> updateIngredient(Ingredient ingredient) {
        Long id = ingredient.getId();
        if (ingredientRepository.existsById(id)) {
            Ingredient updatedIngredient = ingredientRepository.save(ingredient);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(updatedIngredient.getId())
                    .toUri();

            return ResponseEntity
                    .created(uri)
                    .body(updatedIngredient);
        } else {
            throw new NotFoundException("Tool with id:" + id + " does not exist - cannot be updated!");
        }
    }
}
