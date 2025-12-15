    package org.example.beer_manager.service;

    import lombok.RequiredArgsConstructor;
    import org.example.beer_manager.dto.request.ProductRequest;
    import org.example.beer_manager.dto.response.ProductResponse;
    import org.example.beer_manager.entity.Category;
    import org.example.beer_manager.entity.Product;
    import org.example.beer_manager.mapper.ProductMapper;
    import org.example.beer_manager.repo.CategoryRepository;
    import org.example.beer_manager.repo.ProductRepository;
    import org.springframework.stereotype.Service;

    import java.time.Instant;
    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class ProductService {
        private final ProductRepository productRepository;
        private final CategoryRepository categoryRepository;
        private final ProductMapper productMapper;

        public List<ProductResponse> findAll() {
            return productRepository.findAllProduct().stream().map(productMapper::toResponse).toList();
        }

        public ProductResponse create(ProductRequest request) {
            Product new_product = productMapper.toEntity(request);
            new_product.setCategory(findCategoryByID(request.categoryID()));
            productRepository.save(new_product);
            return productMapper.toResponse(new_product);
        }

        public ProductResponse findByID(Integer id) {
            Product find_product_by_id = findProductByID(id);
            return productMapper.toResponse(find_product_by_id);
        }

        public ProductResponse update(Integer id, ProductRequest request) {
            Product update_product_by_id = findProductByID(id);
            productMapper.updateSupplier(update_product_by_id, request);
            update_product_by_id.setCategory(findCategoryByID(request.categoryID()));
            productRepository.save(update_product_by_id);
            return productMapper.toResponse(update_product_by_id);
        }

        public ProductResponse delete(Integer id) {
            Product delete_by_id = findProductByID(id);
            delete_by_id.setDeletedAt(Instant.now());
            productRepository.save(delete_by_id);
            return productMapper.toResponse(delete_by_id);
        }

        public Category findCategoryByID(Integer id) {
            return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục này!"));
        }

        public Product findProductByID(Integer id) {
            return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm này!"));
        }


    }
