package com.fincore.accountservice.controller;

import com.fincore.accountservice.dto.AccountResponse;
import com.fincore.accountservice.dto.CreateAccountRequest;
import com.fincore.accountservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {

        // TODO: Extract userId from JWT/security context
        UUID userId = UUID.randomUUID();

        AccountResponse response =
                accountService.createAccount(request, userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAccounts() {

        // TODO: Extract userId from JWT/security context
        UUID userId = UUID.randomUUID();

        return ResponseEntity.ok(
                accountService.getAccounts(userId)
        );
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(
            @PathVariable String accountNumber) {

        // TODO: Extract userId from JWT/security context
        UUID userId = UUID.randomUUID();

        return ResponseEntity.ok(
                accountService.getAccount(accountNumber, userId)
        );
    }

    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<BigDecimal> getBalance(
            @PathVariable String accountNumber) {

        // TODO: Extract userId from JWT/security context
        UUID userId = UUID.randomUUID();

        return ResponseEntity.ok(
                accountService.getBalance(accountNumber, userId)
        );
    }
}