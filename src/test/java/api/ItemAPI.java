package api;

import core.Request;
import dto.Item;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ItemAPI extends Request {
    private static final String ITEM_ENDPOINT = "/items";

    public ItemAPI(String token) {
        super(token);
    }

    /**
     * Creates item
     * @param item item information
     * @return Response
     */
    @Step("Create item with name")
    public Response createItem(Item item) {
        return post(ITEM_ENDPOINT, gson.toJson(item));
    }

    /**
     * Deletes item by id
     * @param id item id
     * @return Response
     */
    @Step("Delete item with id")
    public Response deleteItem(int id) {
        return delete(ITEM_ENDPOINT + "/" + id);
    }

    /**
     * Updates item by id
     * @param id item id
     * @param item updated information
     * @return Response
     */
    @Step("Update item by id")
    public Response updateItem(int id, Item item) {
        return patch(ITEM_ENDPOINT, gson.toJson(item));
    }

    /**
     * Retrieves single item by id
     * @param id item id
     * @return Response
     */
    @Step("Get single item with id")
    public Response getItem(int id) {
        return get(ITEM_ENDPOINT + "/" + id);
    }

    /**
     * Retrieves multiple items by matching criteria
     * @param queryParams filters like (order_by, order, per_page)
     * @return Response
     */
    @Step("Get items based on criteria")
    public Response getItems(Map<String, ?> queryParams) {
        return get(ITEM_ENDPOINT, queryParams);
    }

    /**
     * Retrieves all items from the system
     * @return Response
     */
    @Step("Get all items")
    public Response getItems() {
        return getItems(new HashMap<>());
    }

    /**
     * Deletes all items
     */
    @Step("Delete all items via API")
    public void deleteAllItems(){
        //System does not provide bulk delete
        Response getAllRes = getItems();
        List<Integer> ids = getAllRes.path("items.id");
        System.out.println("Ids for deletion:" + ids.toString());
        //Delete all ids one by one
        ids.forEach(id -> deleteItem(id));
    }

}
