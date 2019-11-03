package org.sid.web;

import lombok.Data;
import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm){
        return  accountService.saveUser(
                userForm.getUsername(),userForm.getPassword(),userForm.getConfirmedPassword());
    }

    @PostMapping("/editUserName")
    public AppUser editUserName(@RequestBody UserFormUsername userFormUsername){

        return accountService.editUserName(userFormUsername.getLastUserName(),userFormUsername.getNewUserName());
    }

    @PostMapping("/editPassword")
    public AppUser editPassword(@RequestBody UserFormPassword userFormPassword){

        return accountService.editPassword(userFormPassword.getUsername(),userFormPassword.getPassword(),userFormPassword.getConfirmedPassword());
    }
}

@Data
class UserForm{

    private String username;
    private String password;
    private String confirmedPassword;

}

@Data
class UserFormUsername{

    private String newUserName;
    private String lastUserName;

}

@Data
class UserFormPassword{

    private String username;
    private String password;
    private String confirmedPassword;

}
