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

import com.omernaci.accountcore.persistence.entity.Account;
import com.omernaci.accountcore.persistence.entity.Customer;
import com.omernaci.accountcore.persistence.entity.CustomerType;
import com.omernaci.accountcore.persistence.repository.AccountRepository;
import com.omernaci.accountcore.persistence.repository.CustomerRepository;
import com.omernaci.accountcore.service.AccountService;
import com.omernaci.accountcore.service.dto.AccountDto;
import com.omernaci.accountcore.service.impl.AccountServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import java.util.Optional;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@CucumberContextConfiguration
public class AccountOpeningSteps {

    AccountRepository accountRepository = mock(AccountRepository.class);
    CustomerRepository  customerRepository = mock(CustomerRepository.class);
    AccountService accountService;

    private Customer customer;
    private Exception exception;
    private boolean eligibleForAccountOpening;

    @Given("I have an AccountService")
    public void iHaveAnAccountService() {
        accountService = new AccountServiceImpl(accountRepository, customerRepository);
    }

    @Given("the customer with id {long} is a {string} customer")
    public void theCustomerWithIdIsACustomer(Long customerId, String customerType) {
        customer = new Customer(customerId, CustomerType.valueOf(customerType));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
    }

    @Given("the customer has {int} existing account\\(s)")
    public void theCustomerHasExistingAccountS(Integer existingAccountCount) {
        when(accountRepository.countByCustomerId(customer.getId())).thenReturn(existingAccountCount);
    }

    @When("the customer opens a new account using AccountService")
    public void theCustomerOpensANewAccountUsingAccountService() {
        try {
            exception = null;
            AccountDto accountDto = AccountDto.builder()
                .customerId(customer.getId())
                .build();

            accountService.openAccount(accountDto);
            eligibleForAccountOpening = true;
        } catch (Exception e) {
            exception = e;
            eligibleForAccountOpening = false;
        }
    }

    @Then("the account should be created successfully if eligible")
    public void theAccountShouldBeCreatedSuccessfullyIfEligible() {
        if (eligibleForAccountOpening) {
            verify(accountRepository, times(1)).save(any(Account.class));
        } else {
            verify(accountRepository, times(0)).save(any(Account.class));
        }
    }

    @Then("an exception should be thrown if not eligible")
    public void anExceptionShouldBeThrownIfNotEligible() {
        if (eligibleForAccountOpening) {
            assertNull(exception);
        } else {
            assertNotNull(exception);
        }
    }

}
