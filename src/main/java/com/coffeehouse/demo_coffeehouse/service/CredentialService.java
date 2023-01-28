package com.coffeehouse.demo_coffeehouse.service;

import com.coffeehouse.demo_coffeehouse.model.Credential;

public interface CredentialService {
    Credential getCredentials(String username);

}
