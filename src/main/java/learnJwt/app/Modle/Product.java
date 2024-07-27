package learnJwt.app.Modle;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product{

    @NotBlank(message = "product name is require")
    String productName;
    @NotBlank(message = "description is required")
    String productDescription;
    Double productPrice;
//    @NotBlank(message = "description is required")
    Double productSalePrice;
    @NotBlank(message = "colors  are required")
    List<String> colors;
    Integer stockQuantity;
    private  String prodSize;
    List<MultipartFile> images;
    List<String> imagesPath;

}
