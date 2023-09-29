package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTO.category.CategoryDTO;
import saboroso.saborosoburguer.DTO.category.CategoryMapper;
import saboroso.saborosoburguer.entities.burger.BurgerCategory;
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
        String newCategoryTitle = categoryTitle.toUpperCase();
        BurgerCategory category = categoryRepository.findByTitle(newCategoryTitle);
        if (category != null && category.getDeleted()) {
            category.setDeleted(false);
            categoryRepository.save(category);
            return true;
        }
        if (category != null) return false;
        BurgerCategory newCategory = new BurgerCategory(newCategoryTitle);
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
        if (categoryToDelete == null || categoryToDelete.getDeleted()) return false;
        categoryToDelete.setDeleted(true);
        categoryRepository.save(categoryToDelete);
      return true;
    }    
}
