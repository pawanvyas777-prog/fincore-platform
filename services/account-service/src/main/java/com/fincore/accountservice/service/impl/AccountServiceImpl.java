package com.fincore.accountservice.service.impl;

import com.fincore.accountservice.dto.AccountResponse;
import com.fincore.accountservice.dto.CreateAccountRequest;
import com.fincore.accountservice.entity.AccountEntity;
import com.fincore.accountservice.enums.AccountStatus;
import com.fincore.accountservice.enums.AccountType;
import com.fincore.accountservice.exception.AccountNotFoundException;
import com.fincore.accountservice.repository.AccountRepository;
import com.fincore.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;


    @Override
    public AccountResponse createAccount(CreateAccountRequest request, UUID userId) {

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setUserId(userId);
        accountEntity.setAccountType(request.getAccountType().name());
        accountEntity.setStatus(AccountStatus.ACTIVE.name());
        accountEntity.setBalance(BigDecimal.ZERO);

        accountEntity.setAccountNumber(generateAccountNumber());

        accountEntity.setCreatedAt(OffsetDateTime.now());

        accountEntity.setUpdatedAt(OffsetDateTime.now());

        AccountEntity savedAccount = accountRepository.save(accountEntity);


        AccountResponse response = new AccountResponse();

        response.setAccountNumber(savedAccount.getAccountNumber());
        response.setAccountType(AccountType.valueOf(savedAccount.getAccountType()));
        response.setBalance(savedAccount.getBalance());
        response.setStatus(AccountStatus.valueOf(savedAccount.getStatus()));
        response.setCreatedAt(savedAccount.getCreatedAt().toLocalDateTime());

        return response;
    }

    private String generateAccountNumber() {
        return "ACC" + System.currentTimeMillis();
    }

    @Override
    public List<AccountResponse> getAccounts(UUID userId) {

        List<AccountEntity> accounts = accountRepository.findByUserId(userId);

        List<AccountResponse> responses = new ArrayList<>();

        for (AccountEntity account : accounts) {

            AccountResponse response = new AccountResponse();

            response.setAccountNumber(account.getAccountNumber());
            response.setAccountType(AccountType.valueOf(account.getAccountType()));
            response.setBalance(account.getBalance());
            response.setStatus(AccountStatus.valueOf(account.getStatus()));
            response.setCreatedAt(account.getCreatedAt().toLocalDateTime());

            responses.add(response);
        }

        return responses;
    }


    @Override
    public AccountResponse getAccount(String accountNumber, UUID userId) {

        AccountEntity account = accountRepository
                .findByAccountNumberAndUserId(accountNumber, userId)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account not found"));

        AccountResponse response = new AccountResponse();

        response.setAccountNumber(account.getAccountNumber());
        response.setAccountType(AccountType.valueOf(account.getAccountType()));
        response.setBalance(account.getBalance());
        response.setStatus(AccountStatus.valueOf(account.getStatus()));
        response.setCreatedAt(account.getCreatedAt().toLocalDateTime());

        return response;
    }

    @Override
    public BigDecimal getBalance(String accountNumber, UUID userId) {

        AccountEntity account = accountRepository
                .findByAccountNumberAndUserId(accountNumber, userId)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account not found"));

        return account.getBalance();
    }
}
