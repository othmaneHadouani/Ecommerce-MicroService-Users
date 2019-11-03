package org.sid.service;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;

import javax.xml.soap.SAAJResult;

public interface AccountService {

    public AppUser saveUser(String username, String password, String confirmedPassword);
    public AppUser editUserName(String lastUserName, String newUserName);
    public AppUser editPassword(String username, String password, String confirmedPassword);
    public AppRole save(AppRole role);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username, String rolename);

}
