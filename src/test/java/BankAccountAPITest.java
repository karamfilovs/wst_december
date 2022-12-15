import api.BankAccountAPI;
import api.LoginAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.BankAccount;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Bank Accounts")
public class BankAccountAPITest {
    private static String TOKEN = LoginAPI.getToken();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    @Tag("bank-accounts")
    @DisplayName("Can create bank account")
    @Description("To check that user can create non-default bank account")
    public void canCreateBankAccount() {
        BankAccountAPI bankAccountAPI = new BankAccountAPI(TOKEN);
        BankAccount fibBankAccount = new BankAccount();
        fibBankAccount.setBank("ПИБ");
        fibBankAccount.setBankEng("First Investment Bank");
        fibBankAccount.setIban("BG98UNCR70006141567378");
        fibBankAccount.setCurrency("BGN");
        fibBankAccount.setBic("FINV");
        Response createRes = bankAccountAPI.createBankAccount(fibBankAccount);
        Assertions.assertEquals(201, createRes.statusCode());
        int id = createRes.then().extract().path("id");
        //Retrieve new bank account
        Response getRes = bankAccountAPI.getBankAccount(id);
        Assertions.assertEquals(200, getRes.statusCode());
        BankAccount createdBankAcc = gson.fromJson(getRes.asString(), BankAccount.class);
        Assertions.assertEquals(fibBankAccount.getBank(), createdBankAcc.getBank());
        Assertions.assertEquals(fibBankAccount.getIban(), createdBankAcc.getIban());
        Assertions.assertEquals(fibBankAccount.getCurrency(), createdBankAcc.getCurrency());
        //Delete bank account
        Response deleteRes = bankAccountAPI.deleteBankAccount(id);
        Assertions.assertEquals(204, deleteRes.statusCode());
    }

    @Test
    @Tag("bank-accounts")
    @DisplayName("Can create default bank account")
    @Description("To check that user can create default bank account")
    public void canCreateDefaultBankAccount() {
        BankAccountAPI bankAccountAPI = new BankAccountAPI(TOKEN);
        BankAccount fibBankAccount = new BankAccount();
        fibBankAccount.setBank("ПИБ");
        fibBankAccount.setBankEng("First Investment Bank");
        fibBankAccount.setIban("BG98UNCR70006141567379");
        fibBankAccount.setCurrency("BGN");
        fibBankAccount.setIsDefault(true);
        fibBankAccount.setBic("FINV");
        Response createRes = bankAccountAPI.createBankAccount(fibBankAccount);
        Assertions.assertEquals(201, createRes.statusCode());
        int id = createRes.then().extract().path("id");
        //Retrieve new bank account
        Response getRes = bankAccountAPI.getBankAccount(id);
        Assertions.assertEquals(200, getRes.statusCode());
        BankAccount createdBankAcc = gson.fromJson(getRes.asString(), BankAccount.class);
        Assertions.assertEquals(fibBankAccount.getBank(), createdBankAcc.getBank());
        Assertions.assertEquals(fibBankAccount.getIban(), createdBankAcc.getIban());
        Assertions.assertEquals(fibBankAccount.getCurrency(), createdBankAcc.getCurrency());
        Assertions.assertTrue(createdBankAcc.getIsDefault(), "The bank account is not marked as default");
        Assertions.assertFalse(createdBankAcc.getDeleted(), "The bank account is marked as deleted");
        //Delete bank account
        Response deleteRes = bankAccountAPI.deleteBankAccount(id);
        Assertions.assertEquals(204, deleteRes.statusCode());
    }


    @Test
    @Tag("bank-accounts")
    @DisplayName("Can update bank account")
    @Description("To check that user can update bank account")
    public void canUpdateBankAccount() {
        //Create bank account api instance
        BankAccountAPI bankAccountAPI = new BankAccountAPI(TOKEN);
        //Create bank account DTO
        BankAccount fibBankAccount = new BankAccount();
        fibBankAccount.setBank("ПИБ");
        fibBankAccount.setBankEng("First Investment Bank");
        fibBankAccount.setIban("BG98UNCR70006141567379");
        fibBankAccount.setCurrency("BGN");
        fibBankAccount.setIsDefault(true);
        fibBankAccount.setBic("FINV");
        //Create bank account
        Response createRes = bankAccountAPI.createBankAccount(fibBankAccount);
        //Check that the bank account is created
        Assertions.assertEquals(201, createRes.statusCode());
        int id = createRes.then().extract().path("id");
        //Retrieve new bank account
        Response getRes = bankAccountAPI.getBankAccount(id);
        Assertions.assertEquals(200, getRes.statusCode());
        //Update bank account currency
        fibBankAccount.setCurrency("EUR");
        //Update bank account
        Response updateRes = bankAccountAPI.updateBankAccount(id, fibBankAccount);
        Assertions.assertEquals(204, updateRes.statusCode());
        //Get bank account
        getRes = bankAccountAPI.getBankAccount(id);
        String updatedCurrency = getRes.then().extract().path("currency");
        Assertions.assertEquals("EUR", updatedCurrency);
        //Delete bank account
        Response deleteRes = bankAccountAPI.deleteBankAccount(id);
        Assertions.assertEquals(204, deleteRes.statusCode());
    }


    @Test
    @Tag("bank-accounts")
    @Tag("negative")
    @DisplayName("Cant create bank account with duplicate iban")
    @Description("To check that user cant create bank account with duplicate iban")
    public void cantCreateBankAccountWithDuplicateIban() {
        BankAccountAPI bankAccountAPI = new BankAccountAPI(TOKEN);
        BankAccount fibBankAccount = new BankAccount();
        fibBankAccount.setBank("ПИБ");
        fibBankAccount.setBankEng("First Investment Bank");
        fibBankAccount.setIban("BG98UNCR70006141567378");
        fibBankAccount.setCurrency("BGN");
        fibBankAccount.setBic("FINV");
        Response createRes = bankAccountAPI.createBankAccount(fibBankAccount);
        Assertions.assertEquals(201, createRes.statusCode());
        int id = createRes.then().extract().path("id");
        //Try to create duplicate bank account
        Response duplicateRes = bankAccountAPI.createBankAccount(fibBankAccount);
        Assertions.assertEquals(400, duplicateRes.statusCode()); //should be 409 conflict
        String actualError = duplicateRes.path("error");
        Assertions.assertEquals("Вече съществува сметка с такъв IBAN", actualError);
        //Delete bank account
        Response deleteRes = bankAccountAPI.deleteBankAccount(id);
        Assertions.assertEquals(204, deleteRes.statusCode());
    }

}
