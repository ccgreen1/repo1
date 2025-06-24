package com.myweb.domain;

public class UserTest {
    private Integer id;
    private String accountName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return "UserTest{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}
