package com.transaction.transactiontracker.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "child")
public class Child {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID childId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    private String sender;

    private String receiver;

    private Double totalAmount;

    private Double paidAmount;
}

