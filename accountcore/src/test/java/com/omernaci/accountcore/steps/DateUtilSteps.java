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

import com.omernaci.accountcore.util.DateUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class DateUtilSteps {
    private LocalDate inputDate;
    private LocalDate resultDate;

    @Given("a date {string}")
    public void set_input_date(String dateString) {
        inputDate = LocalDate.parse(dateString);
    }

    @When("I check if it is a weekend")
    public void check_if_weekend() {
        boolean isWeekend = DateUtil.isWeekend(inputDate);
        assertTrue(isWeekend);
    }

    @When("I calculate the next work date")
    public void calculate_next_work_date() {
        resultDate = DateUtil.getNextWorkDate(inputDate);
    }

    @Then("the result should be {string}")
    public void assert_result_date(String expectedDate) {
        LocalDate expected = LocalDate.parse(expectedDate);
        assertEquals(expected, resultDate);
    }

    @Then("the result should be true")
    public void theResultShouldBeTrue() {
        assertTrue(true);
    }
}
