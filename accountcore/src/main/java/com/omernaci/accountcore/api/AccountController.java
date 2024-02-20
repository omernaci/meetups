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

package com.omernaci.accountcore.api;

import com.omernaci.accountcore.service.AccountService;
import com.omernaci.accountcore.service.dto.AccountClosingDto;
import com.omernaci.accountcore.service.dto.AccountDto;
import com.omernaci.accountcore.service.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/open")
    public ResponseEntity<String> openAccount(@RequestBody AccountDto accountDto) {
        accountService.openAccount(accountDto);
        return ResponseEntity.ok("Account opened successfully");
    }

    @PostMapping("/{accountId}/close")
    public ResponseEntity<String> closeAccount(@RequestBody AccountClosingDto accountClosingDto) {
        ApiResponse response = accountService.closeAccount(accountClosingDto);
        return ResponseEntity.ok(response.getMessage());
    }

}
