package api;

public class API {
    private String token;
    private ItemAPI itemAPI;
    private ClientAPI clientAPI;
    private BankAccountAPI bankAccountAPI;
    private LoginAPI loginAPI;

    public API (String token){
        this.token = token;
    }

    public ItemAPI itemAPI(){
        if (itemAPI == null){
            itemAPI = new ItemAPI(token);
        }
        return itemAPI;
    }

    public ClientAPI clientAPI(){
        if (clientAPI == null){
            clientAPI = new ClientAPI(token);
        }
        return clientAPI;
    }

    public BankAccountAPI bankAccountAPI(){
        if (bankAccountAPI == null){
            bankAccountAPI = new BankAccountAPI(token);
        }
        return bankAccountAPI;
    }

    public LoginAPI loginAPI(){
        if (loginAPI == null){
            loginAPI = new LoginAPI();
        }
        return loginAPI;
    }
}
