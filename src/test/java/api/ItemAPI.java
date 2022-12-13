package api;

import core.Request;
import dto.Item;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;


public class ItemAPI extends Request {
    private static final String ITEM_ENDPOINT = "/items";

    public ItemAPI(String token) {
        super(token);
    }

    @Step("Create item with name")
    public Response createItem(Item item) {
        return post(ITEM_ENDPOINT, gson.toJson(item));
    }

    @Step("Delete item with id")
    public Response deleteItem(int id) {
        return delete(ITEM_ENDPOINT + "/" + id);
    }

    @Step("Update item by id")
    public Response updateItem(int id, Item item) {
        return patch(ITEM_ENDPOINT, gson.toJson(item));
    }

    @Step("Get single item with id")
    public Response getItem(int id) {
        return get(ITEM_ENDPOINT + "/" + id);
    }

    @Step("Get items based on criteria")
    public Response getItems(Map<String, ?> queryParams) {
        return get(ITEM_ENDPOINT, queryParams);
    }

    @Step("Get all items")
    public Response getItems() {
        return getItems(new HashMap<>());
    }
}
