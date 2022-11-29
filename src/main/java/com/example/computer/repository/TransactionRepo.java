package com.example.computer.repository;

import com.example.computer.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.List;


@Repository
@Transactional
public interface TransactionRepo extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByCustomerId(Long var1);

    List<Transaction> findAllByCustomerIdAndTransactionDateBetween(Long var1, Timestamp var2, Timestamp var3);
}
