package learnJwt.app.Modle;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product{

    String productName;
    String productDescription;
    Double productPrice;
    Double productSalePrice;
    List<String> colors;
    Integer stockQuantity;


}
