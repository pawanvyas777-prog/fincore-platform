package com.fincore.accountservice.repository;

import com.fincore.accountservice.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> findByUserId(UUID userId);

    Optional<AccountEntity> findByAccountNumber(String accountNumber);

    Optional<AccountEntity> findByAccountNumberAndUserId(
            String accountNumber,
            UUID userId
    );
}