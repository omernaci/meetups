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

import com.omernaci.accountcore.service.TokenService;
import com.omernaci.accountcore.service.impl.TokenServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertNotNull;

public class TokenSteps {

    private TokenService tokenService;
    private String token;

    @Given("I have a token service")
    public void iHaveATokenService() {
        tokenService = new TokenServiceImpl();
    }

    @When("I call generate token service")
    public void iCallGenerateTokenService() {
        token = tokenService.generateToken("ING");
    }

    @Then("The result should not be empty")
    public void theResultShouldNotBeEmpty() {
        assertNotNull(token);
    }

}
