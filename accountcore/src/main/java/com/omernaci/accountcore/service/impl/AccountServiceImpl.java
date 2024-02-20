/*
 * Copyright 2018-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.omernaci.accountcore.service.impl;

import com.omernaci.accountcore.exception.AccountCreateException;
import com.omernaci.accountcore.persistence.entity.Account;
import com.omernaci.accountcore.persistence.entity.AccountStatus;
import com.omernaci.accountcore.persistence.entity.Customer;
import com.omernaci.accountcore.persistence.entity.CustomerType;
import com.omernaci.accountcore.persistence.repository.AccountRepository;
import com.omernaci.accountcore.persistence.repository.CustomerRepository;
import com.omernaci.accountcore.service.AccountService;
import com.omernaci.accountcore.service.dto.AccountClosingDto;
import com.omernaci.accountcore.service.dto.AccountDto;
import com.omernaci.accountcore.service.dto.ApiResponse;
import com.omernaci.accountcore.util.DateUtil;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    private static final int ACCOUNT_LIMIT_COUNT = 3;

    public AccountServiceImpl(AccountRepository accountRepository,
        CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void openAccount(AccountDto accountDto) {
        Customer customer = customerRepository.findById(accountDto.customerId())
            .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        if (customer.getCustomerType() == CustomerType.CORPORATE && accountDto.currency().equals("EUR")) {
            throw new AccountCreateException("Corporate customers are not eligible for account opening");
        }

        int accountCount = accountRepository.countByCustomerId(customer.getId());
        if (accountCount >= ACCOUNT_LIMIT_COUNT) {
            throw new AccountCreateException("Customer has reached account limit");
        }

        if (accountDto.balance().compareTo(BigDecimal.ZERO) < 0) {
            throw new AccountCreateException("Initial balance cannot be negative");
        }

        Account account = new Account(customer, accountDto.currency(), accountDto.accountName(),
            accountDto.balance(), accountDto.accountType(), accountDto.accountStatus());
        accountRepository.save(account);
    }

    @Override
    public ApiResponse closeAccount(AccountClosingDto accountClosingDto) {
        Account account = accountRepository.findById(accountClosingDto.accountId())
            .orElse(null);

        if (account == null) {
            return new ApiResponse(false, "Account not found");
        }

        if (DateUtil.isWeekend(accountClosingDto.closedDate())) {
            return new ApiResponse(false, "Accounts cannot be closed on weekends.");
        }

        if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            return new ApiResponse(false, "Account balance must be zero to close the account");
        }

        account.setAccountStatus(AccountStatus.CLOSED);
        accountRepository.save(account);

        return new ApiResponse(true, "Account closed successfully");
    }

}
