package com.codefans.practicetask.httpserver;

/**
 * @author: mpif
 * @date: 2018-06-06 16:19
 */
public class RequestDomain {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RequestDomain{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
