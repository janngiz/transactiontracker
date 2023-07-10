package com.transaction.transactiontracker.repo;

import com.transaction.transactiontracker.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParentRepository extends JpaRepository<Parent, UUID> {
}
