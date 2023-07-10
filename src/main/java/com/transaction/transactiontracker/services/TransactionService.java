package com.transaction.transactiontracker.services;

import com.transaction.transactiontracker.entities.Child;
import com.transaction.transactiontracker.entities.Parent;
import com.transaction.transactiontracker.repo.ChildRepository;
import com.transaction.transactiontracker.repo.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;

    @Autowired
    public TransactionService(ParentRepository parentRepository, ChildRepository childRepository) {
        this.parentRepository = parentRepository;
        this.childRepository = childRepository;
    }

    public List<Parent> getAllParents(int page, int pageSize, String sortBy) {
        validatePageAndPageSize(page, pageSize);
        validateSortingField(sortBy);

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(sortBy));
        Page<Parent> parentPage = parentRepository.findAll(pageRequest);
        return parentPage.getContent();
    }

    public Optional<Parent> getParentById(UUID parentId) {
        validateParentId(parentId);
        return parentRepository.findById(parentId);
    }

    public List<Child> getChildrenByParentId(UUID parentId) {
        validateParentId(parentId);
        return childRepository.findByParentParentId(parentId);
    }

    private void validatePageAndPageSize(int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid page or pageSize value");
        }
    }

    private void validateSortingField(String sortBy) {
        List<String> allowedFields = Arrays.asList("parentId", "sender", "receiver", "totalAmount", "totalPaidAmount");
        if (!allowedFields.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sorting field");
        }
    }


    private void validateParentId(UUID parentId) {
        if (parentId == null) {
            throw new IllegalArgumentException("Parent ID cannot be null");
        }
    }
}
