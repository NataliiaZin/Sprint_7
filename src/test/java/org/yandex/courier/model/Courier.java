package org.yandex.courier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Courier {

    private String login;
    private String password;
    @JsonIgnore
    private Integer id;

    public String getPassword() {
        return password;
    }

    public Courier setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Courier setLogin(String login) {
        this.login = login;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Courier setId(Integer id) {
        this.id = id;
        return this;
    }
}
