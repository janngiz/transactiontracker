package com.transaction.transactiontracker.repo;

import com.transaction.transactiontracker.entities.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChildRepository extends JpaRepository<Child, UUID> {
    List<Child> findByParentParentId(UUID parentId);
}

