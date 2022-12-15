package api;

import core.Request;
import dto.BankAccount;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

public class BankAccountAPI extends Request {
    private static final String BANK_ACC_ENDPOINT = "/bank/accounts";

    public BankAccountAPI(String token) {
        super(token);
    }

    /**
     * Retrieves bank accounts based on criteria
     * @param queryParams filters like (order_by, per_page, order ...)
     * @return Response
     */
    @Step("Get bank accounts matching criteria")
    public Response getBankAccounts(Map<String, ?> queryParams){
        return get(BANK_ACC_ENDPOINT, queryParams);
    }

    /**
     * Retrieves single bank account by id
     * @param id bank account id
     * @return Response
     */
    @Step("Get bank account by id {id}")
    public Response getBankAccount(int id){
        return get(BANK_ACC_ENDPOINT + "/" + id);
    }

    /**
     * Retrieves bank account by id
     * @param id bank account id
     * @return BankAccount
     */
    @Step("Get bank account as object")
    public BankAccount getBankAccountAsObj(int id){
        Response response = getBankAccount(id);
        return response.then().extract().as(BankAccount.class);
    }

    /**
     * Deletes bank account by id
     * @param id bank account id
     * @return Response
     */
    @Step("Delete bank account by id")
    public Response deleteBankAccount(int id){
        return delete(BANK_ACC_ENDPOINT + "/" + id);
    }

    /**
     * Creates bank account
     * @param bankAccount bank account information
     * @return Response
     */
    @Step("Create bank account")
    public Response createBankAccount(BankAccount bankAccount){
        return post(BANK_ACC_ENDPOINT, gson.toJson(bankAccount));
    }

    /**
     * Updates bank account
     * @param bankAccount updated bank account information
     * @return Response
     */
    @Step("Create bank account")
    public Response updateBankAccount(int id, BankAccount bankAccount){
        return patch(BANK_ACC_ENDPOINT + "/" + id, gson.toJson(bankAccount));
    }
}
