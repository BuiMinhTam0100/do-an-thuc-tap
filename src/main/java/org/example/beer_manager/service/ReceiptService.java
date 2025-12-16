package org.example.beer_manager.service;
import lombok.RequiredArgsConstructor;
import org.example.beer_manager.dto.request.ReceiptRequest;
import org.example.beer_manager.dto.response.ReceiptResponse;
import org.example.beer_manager.entity.Receipt;
import org.example.beer_manager.entity.Supplier;
import org.example.beer_manager.mapper.ReceiptMapper;
import org.example.beer_manager.repo.ReceiptRepository;
import org.example.beer_manager.repo.SupplierRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final SupplierRepository supplierRepository;
    private final ReceiptMapper mapper;

    public List<ReceiptResponse> findAll() {
        return receiptRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    public ReceiptResponse create(ReceiptRequest request) {
        Receipt new_receipt = mapper.toEntity(request);
        new_receipt.setSupplier(findSupplierByID(request.supplierId()));
        receiptRepository.save(new_receipt);
        return mapper.toResponse(new_receipt);
    }

    public ReceiptResponse findByID(Integer id) {
        Receipt find_by_id = findReceiptByID(id);
        return mapper.toResponse(find_by_id);
    }

    public ReceiptResponse update(Integer id, ReceiptRequest request) {
        Receipt update_by_id = findReceiptByID(id);
        mapper.updateReceipt(update_by_id, request);
        receiptRepository.save(update_by_id);
        return mapper.toResponse(update_by_id);
    }

    public ReceiptResponse delete(Integer id) {
        Receipt delete_by_id = findReceiptByID(id);
        delete_by_id.setDeletedAt(Instant.now());
        receiptRepository.save(delete_by_id);
        return mapper.toResponse(delete_by_id);
    }

    public Supplier findSupplierByID(Integer id) {
        return supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy nhà cung cấp này!"));
    }

    public Receipt findReceiptByID(Integer id) {
        return receiptRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu nhập này!"));
    }
}
