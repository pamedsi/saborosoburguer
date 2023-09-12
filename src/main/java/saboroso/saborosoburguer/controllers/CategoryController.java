package saboroso.saborosoburguer.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import saboroso.saborosoburguer.DTOs.category.CategoryDTO;
import saboroso.saborosoburguer.model.BaseController;
import saboroso.saborosoburguer.model.Message;
import saboroso.saborosoburguer.services.CategoryService;

import java.util.List;

@RestController
public class CategoryController extends BaseController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping(value = "/get-all-categories")
    public ResponseEntity<?> seeCategories(){
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    @PostMapping(value = "/add-category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO){
        Boolean worked = categoryService.createCategory(categoryDTO.title());
        if (!worked) return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new Message(categoryDTO.title() + " já cadastrado!")
        );
        return ResponseEntity.ok(new Message(categoryDTO.title() + " cadastrado!"));
    }
    @PutMapping(value = "/edit-category")
    public ResponseEntity<?> editCategory(@RequestBody CategoryDTO categoryDTO) {
        Boolean worked = categoryService.updateCategory(categoryDTO);
        if (!worked) return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new Message("Já existe uma categoria com esse título!")
        );
        return ResponseEntity.ok(new Message("Categoria atualizada! Agora se chama " + categoryDTO.title()));
    }
    @DeleteMapping(value = "/remove-category/{identifier}")
    public ResponseEntity<?> removeCategory(@PathVariable String identifier) {
        Boolean worked = categoryService.deleteCategory(identifier);
        if (!worked) return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new Message("Esta categoria não existe ou já foi deletada!")
        );
        return ResponseEntity.ok(new Message("Categoria removida com sucesso."));
    }    
}
