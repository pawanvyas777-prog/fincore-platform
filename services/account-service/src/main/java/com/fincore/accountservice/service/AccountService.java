package com.fincore.accountservice.service;

import com.fincore.accountservice.dto.AccountResponse;
import com.fincore.accountservice.dto.CreateAccountRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountResponse createAccount(CreateAccountRequest request, UUID userId);

    List<AccountResponse> getAccounts(UUID userId);

    AccountResponse getAccount(String accountNumber, UUID userId);

    BigDecimal getBalance(String accountNumber, UUID userId);
}
