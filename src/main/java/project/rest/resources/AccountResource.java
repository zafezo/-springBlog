package project.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import project.core.models.entities.Account;

/**
 * Created by swen on 5/2/16.
 */
public class AccountResource extends ResourceSupport {
    private String name;
    private String password;

    public Account toAccount(){
        Account temp = new Account();
        temp.setName(name);
        temp.setPassword(password);
        return temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
