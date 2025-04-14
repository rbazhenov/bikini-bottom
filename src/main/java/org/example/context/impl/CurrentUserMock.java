package org.example.context.impl;

public class CurrentUserMock extends CurrentUserImpl {

    @Override
    public String getLogin() {
        return "mock login";
    }
}
