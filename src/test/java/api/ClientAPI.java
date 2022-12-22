package api;

import core.Request;

public class ClientAPI extends Request {
    //TODO: Implement this class as a homework
    //TODO: API Docs: https://api.inv.bg/v3/swagger-ui/#/


    public ClientAPI(String token) {
        super(token);
    }


    public ClientAPI(String token, Role role){
        //this is calling the parent constructor to initialize token and role;
        super(token, role);
    }

}
