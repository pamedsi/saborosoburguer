package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.category.CategoryDTO;
import saboroso.saborosoburguer.DTOs.category.CategoryMapper;
import saboroso.saborosoburguer.entities.BurgerCategory;
import saboroso.saborosoburguer.repositories.CategoryRepository;

import java.time.LocalDateTime;
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
        List<BurgerCategory> categoriesPersistence = categoryRepository.findByDeletedFalse();
        return categoryMapper.severalToDTO(categoriesPersistence);
    }
    public Boolean createCategory(String categoryTitle) {
        BurgerCategory category = categoryRepository.findByTitle(categoryTitle.toUpperCase());
        if (category != null && category.getDeleted()) {
            category.setDeleted(false);
            return true;
        }
        if (category != null) return false;
        BurgerCategory newCategory = new BurgerCategory(categoryTitle);
        categoryRepository.save(newCategory);
        return true;
    }
    public Boolean updateCategory(CategoryDTO categoryDTO) {
        String inputTitle = categoryDTO.title().toUpperCase();
        Boolean exists = categoryRepository.existsByTitle(inputTitle);
        if (exists) return false;
        BurgerCategory categoryToEdit = categoryRepository.findByIdentifier(categoryDTO.identifier());
        categoryToEdit.setTitle(inputTitle);
        categoryToEdit.setLastEditedIn(LocalDateTime.now());
        categoryRepository.save(categoryToEdit);
      return true;
    }
    public Boolean deleteCategory(String categoryIdentifier) {
        BurgerCategory categoryToDelete = categoryRepository.findByIdentifier(categoryIdentifier);
        if (categoryToDelete == null || !categoryToDelete.getDeleted()) return false;
        categoryToDelete.setDeleted(true);
        categoryRepository.save(categoryToDelete);
      return true;
    }    
}
