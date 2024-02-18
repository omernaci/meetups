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

package com.omernaci.accountcore.steps;

import com.omernaci.accountcore.exception.AccountCreateException;
import com.omernaci.accountcore.persistence.entity.AccountStatus;
import com.omernaci.accountcore.persistence.entity.AccountType;
import com.omernaci.accountcore.persistence.entity.Customer;
import com.omernaci.accountcore.persistence.entity.CustomerType;
import com.omernaci.accountcore.persistence.repository.AccountRepository;
import com.omernaci.accountcore.persistence.repository.CustomerRepository;
import com.omernaci.accountcore.service.AccountService;
import com.omernaci.accountcore.service.dto.AccountDto;
import com.omernaci.accountcore.service.impl.AccountServiceImpl;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountOpeningSteps {

    AccountRepository accountRepository = mock(AccountRepository.class);
    CustomerRepository  customerRepository = mock(CustomerRepository.class);
    AccountService accountService;

    private Customer customer;
    private boolean success;

    @Given("I have an AccountService")
    public void iHaveAnAccountService() {
        accountService = new AccountServiceImpl(accountRepository, customerRepository);
    }

    @Given("the customer with id {long} is a {string} customer")
    public void theCustomerWithIdIsACustomer(Long customerId, String customerType) {
        customer = new Customer(customerId, CustomerType.valueOf(customerType));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
    }

    @And("the customer has {int} account")
    public void theCustomerHasAccount(int existingAccountCount) {
        when(accountRepository.countByCustomerId(customer.getId())).thenReturn(existingAccountCount);
    }

    @When("an account is opened with currency {string}, name {string}, balance {double}, type {string}, and status {string}")
    public void anAccountIsOpenedWithCurrencyNameBalanceTypeAndStatus(String currency, String accountName, double balance, String accountType, String accountStatus) {
        AccountDto accountDto = AccountDto.builder()
            .customerId(customer.getId())
            .currency(currency)
            .accountName(accountName)
            .balance(BigDecimal.valueOf(balance))
            .accountType(AccountType.valueOf(accountType))
            .accountStatus(AccountStatus.valueOf(accountStatus))
            .build();

        try {
            accountService.openAccount(accountDto);
            success = true;
        } catch (AccountCreateException e) {
            success = false;
        }
    }

    @Then("the account should be created {boolean}")
    public void theAccountShouldBeCreated(boolean expectedSuccess) {
        assertEquals(expectedSuccess, success);
    }

    @ParameterType(name = "boolean", value = "true|false")
    public boolean booleanValue(String value) {
        return Boolean.parseBoolean(value);
    }

}
