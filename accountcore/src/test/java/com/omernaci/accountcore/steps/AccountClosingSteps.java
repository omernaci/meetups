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
import com.omernaci.accountcore.persistence.repository.AccountRepository;
import com.omernaci.accountcore.service.dto.AccountClosingDto;
import com.omernaci.accountcore.service.dto.ApiResponse;
import com.omernaci.accountcore.service.impl.AccountServiceImpl;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AccountClosingSteps {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account;

    private ApiResponse response;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("an account with ID {long} and balance {double}")
    public void anAccountWithIDAndBalance(Long accountId, Double balance) {
        account = new Account();
        account.setId(accountId);
        account.setBalance(BigDecimal.valueOf(balance));

        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
    }

    @When("the account is closed on {string}")
    public void theAccountIsClosedOn(String closedDate) {
        AccountClosingDto accountClosingDto = new AccountClosingDto(account.getId(), LocalDate.parse(closedDate));

        response = accountService.closeAccount(accountClosingDto);
    }

    @Then("the system should return message {string}")
    public void theSystemShouldReturnMessage(String expectedMessage) {
        assertEquals(expectedMessage, response.getMessage());
    }

}
