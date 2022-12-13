package dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
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
    @SerializedName("quantity_unit")
    private String unit;
    @SerializedName("price_for_quantity")
    private Double priceForQuantity;
    private String currency;
}
