package org.example.beer_manager.service;
import lombok.RequiredArgsConstructor;
import org.example.beer_manager.dto.request.SupplierRequest;
import org.example.beer_manager.dto.response.SupplierResponse;
import org.example.beer_manager.entity.Supplier;
import org.example.beer_manager.mapper.SupplierMapper;
import org.example.beer_manager.repo.SupplierRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public List<SupplierResponse> findAll() {
        return supplierRepository.findAll().stream().map(supplierMapper::toResponse).toList();
    }

    public SupplierResponse create(SupplierRequest request) {
        Supplier new_supplier = supplierMapper.toEntity(request);
        supplierRepository.save(new_supplier);
        return supplierMapper.toResponse(new_supplier);
    }

    public SupplierResponse findSupplierByID(Integer id) {
        Supplier find_by_id = findByID(id);
        return supplierMapper.toResponse(find_by_id);
    }

    public SupplierResponse update(Integer id, SupplierRequest request) {
        Supplier update_by_id = findByID(id);
        supplierMapper.updateSupplier(update_by_id, request);
        supplierRepository.save(update_by_id);
        return supplierMapper.toResponse(update_by_id);
    }

    public SupplierResponse delete(Integer id) {
        Supplier delete_by_id = findByID(id);
        delete_by_id.setDeletedAt(Instant.now());
        supplierRepository.save(delete_by_id);
        return supplierMapper.toResponse(delete_by_id);
    }

    public Supplier findByID(Integer id) {
        return supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy nhà cung cấp này!"));
    }
}
