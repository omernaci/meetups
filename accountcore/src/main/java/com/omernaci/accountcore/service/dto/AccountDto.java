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

package com.omernaci.accountcore.service.dto;

import com.omernaci.accountcore.persistence.entity.AccountStatus;
import com.omernaci.accountcore.persistence.entity.AccountType;
import java.math.BigDecimal;

public record AccountDto(
    Long id,
    Long customerId,
    String currency,
    String accountName,
    BigDecimal balance,
    AccountType accountType,
    AccountStatus accountStatus
) {

    public static class Builder {
        private Long id;
        private Long customerId;
        private String currency;
        private String accountName;
        private BigDecimal balance;
        private AccountType accountType;
        private AccountStatus accountStatus;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder accountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder accountStatus(AccountStatus accountStatus) {
            this.accountStatus = accountStatus;
            return this;
        }

        public AccountDto build() {
            return new AccountDto(id, customerId, currency, accountName, balance, accountType, accountStatus);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
