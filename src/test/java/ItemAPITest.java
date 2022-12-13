import api.ItemAPI;
import api.LoginAPI;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Item;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

@Epic("Some EPIC")
@Feature("Item")
public class ItemAPITest {
    private static String TOKEN = LoginAPI.getToken();

   @BeforeEach
   public  void beforeEach(){
      //Create item
   }


    @Test
    @DisplayName("Can create item")
    public void canCreateItem(){
       //Create item dto
        Item coffee = Item.builder()
                .name("Some coffee brand2")
                .price(20.20)
                .priceForQuantity(1.0)
                .currency("BGN")
                .unit("kg.")
                .build();
        ItemAPI itemAPI = new ItemAPI(TOKEN);
        Response createRes = itemAPI.createItem(coffee);
        Assertions.assertEquals(201, createRes.statusCode());
        int id = createRes.then().extract().path("id");
        //Get current item
        Response getRes = itemAPI.getItem(id);
        Assertions.assertEquals(200, getRes.statusCode());
        //Item createdItem = getRes.then().extract().as(Item.class);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Item createdItem = gson.fromJson(getRes.asString(), Item.class);
        Assertions.assertEquals(coffee.getName(), createdItem.getName());
        Assertions.assertEquals(coffee.getUnit(), createdItem.getUnit());
        Assertions.assertEquals(coffee.getCurrency(), createdItem.getCurrency());
        //Delete item
        Response deleteRes = itemAPI.deleteItem(id);
        Assertions.assertEquals(204, deleteRes.statusCode());
    }
}
