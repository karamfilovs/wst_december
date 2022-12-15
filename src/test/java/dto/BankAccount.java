package dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BankAccount {
    private Integer id;
    private String bank;
    @SerializedName("bank_en")
    private String bankEng;
    private String iban;
    private String bic;
    private String currency;
    private String address;
    @SerializedName("default")
    private Boolean isDefault;
    private Boolean deleted;

}
