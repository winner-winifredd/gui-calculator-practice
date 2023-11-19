package com.example.rewardyourteachersq011bjavapode.models;
import com.example.rewardyourteachersq011bjavapode.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction extends BaseClass{
    private String uuid;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String description;
    private BigDecimal amount;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId" , referencedColumnName = "id")
    private User user;




}

