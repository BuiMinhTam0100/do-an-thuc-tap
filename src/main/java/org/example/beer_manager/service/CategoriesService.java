package org.example.beer_manager.service;
import lombok.RequiredArgsConstructor;
import org.example.beer_manager.dto.request.CategoriesRequest;
import org.example.beer_manager.dto.response.CategoriesResponse;
import org.example.beer_manager.entity.Category;
import org.example.beer_manager.mapper.CategoriesMapper;
import org.example.beer_manager.repo.CategoryRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoryRepository categoryRepository;
    private final CategoriesMapper categoriesMapper;

    public List<CategoriesResponse> findAll() {
        return categoryRepository.findAll().stream().map(categoriesMapper::toResponse).toList();
    }

    public CategoriesResponse create(CategoriesRequest request) {
        Category new_category = categoriesMapper.toEntity(request);
        categoryRepository.save(new_category);
        return categoriesMapper.toResponse(new_category);
    }

    public CategoriesResponse findCategoriesByID(Integer id) {
        Category find_by_id = findByID(id);
        return categoriesMapper.toResponse(find_by_id);
    }

    public CategoriesResponse update(Integer id, CategoriesRequest request) {
        Category update_by_id = findByID(id);
        categoriesMapper.updateSupplier(update_by_id, request);
        categoryRepository.save(update_by_id);
        return categoriesMapper.toResponse(update_by_id);
    }

    public CategoriesResponse delete(Integer id) {
        Category delete_by_id = findByID(id);
        delete_by_id.setDeletedAt(Instant.now());
        categoryRepository.save(delete_by_id);
        return categoriesMapper.toResponse(delete_by_id);
    }

    public Category findByID(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy ID này!"));
    }
}
