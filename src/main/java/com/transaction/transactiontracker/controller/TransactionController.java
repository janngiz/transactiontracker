package com.transaction.transactiontracker.controller;

import com.transaction.transactiontracker.entities.Child;
import com.transaction.transactiontracker.entities.Parent;
import com.transaction.transactiontracker.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/parents")
    public ResponseEntity<List<Parent>> getAllParents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int pageSize,
            @RequestParam(defaultValue = "parentId") String sortBy
    ) {
        List<Parent> parents = transactionService.getAllParents(page, pageSize, sortBy);
        return ResponseEntity.ok(parents);
    }

    @GetMapping("/parents/{parentId}")
    public ResponseEntity<Parent> getParentById(@PathVariable UUID parentId) {
        Optional<Parent> parent = transactionService.getParentById(parentId);
        return parent.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/parents/{parentId}/children")
    public ResponseEntity<List<Child>> getChildrenByParentId(@PathVariable UUID parentId) {
        List<Child> children = transactionService.getChildrenByParentId(parentId);
        return ResponseEntity.ok(children);
    }

}
