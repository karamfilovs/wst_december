package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Item {
    private String id;
    private String name;
    private Double price;
    private Integer quantity;

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Item beer = Item.builder().name("Zagorka").build();
        String beerJson = gson.toJson(beer);
        System.out.println(beerJson);
        String responseItem = "{\n" +
                "\"name\": \"Zagorka\",\n" +
                "\"id\": 5\n" +
                "}";
        String responseItem2 = "{\n" +
                "\"message\": \"Successfully created item\",\n" +
                "\"id\": 5\n" +
                "}";
        Item createdItem = gson.fromJson(responseItem, Item.class);
        System.out.println(createdItem.getId());
        EntityRes createdItem2 = gson.fromJson(responseItem2, EntityRes.class);
        System.out.println(createdItem2.getMessage());
        System.out.println(createdItem2.getId());

    }
}
