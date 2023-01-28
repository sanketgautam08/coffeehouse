package com.coffeehouse.demo_coffeehouse.serviceimpl;

import com.coffeehouse.demo_coffeehouse.DAO.CredentialDao;
import com.coffeehouse.demo_coffeehouse.DAO.UserDao;
import com.coffeehouse.demo_coffeehouse.model.Credential;
import com.coffeehouse.demo_coffeehouse.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("credentialService")
public class CredentialServiceImpl implements CredentialService {
    @Autowired
    CredentialDao credentialDao;
    @Override
    public Credential getCredentials(String username) {
        Optional<List<Credential>> userCred = credentialDao.getCredentialsByUsername(username);
        if(!userCred.isEmpty()){
            return userCred.get().iterator().next();
        }else{
            throw new UsernameNotFoundException("User name not found: "+ username);
        }
    }
}
