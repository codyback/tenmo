package com.techelevator.tenmo.model;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class Account {
    private long accountId;
    private User user;
    @Positive
    private BigDecimal balance;

    public Account() {}

    public Account(long accountId, User user, BigDecimal balance) {
        this.accountId = accountId;
        this.user = user;
        this.balance = balance;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
