package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.category.CategoryDTO;
import saboroso.saborosoburguer.DTOs.category.CategoryMapper;
import saboroso.saborosoburguer.entities.BurgerCategory;
import saboroso.saborosoburguer.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }
    public List<CategoryDTO> getAllCategories() {
        List<BurgerCategory> categoriesPersistence = categoryRepository.findAll();
        return categoryMapper.severalToDTO(categoriesPersistence);
    }
    public Boolean createCategory(String categoryTitle) {
        Boolean category = categoryRepository.existsByTitle(categoryTitle.toUpperCase());
        if (category) return false;
        BurgerCategory newCategory = new BurgerCategory(categoryTitle.toUpperCase());
        categoryRepository.save(newCategory);
        return true;
    }
}
