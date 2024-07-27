package learnJwt.app.Controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import learnJwt.app.Modle.Product;
import learnJwt.app.Modle.ProductModel;
import learnJwt.app.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {


    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/postProduct")
 public ResponseEntity<String> postProduct( @ModelAttribute Product product ) {
        Product createdProduct;
        try {
            createdProduct = productService.saveProduct(product);
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return new   ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new  ResponseEntity<>("Saved Succefully" + createdProduct,HttpStatus.CREATED);
}


@GetMapping("/getProducts")
    public ResponseEntity<List<ProductModel>> getProduct() {
      List<ProductModel> result =  productService.getAllProducts();
        return ResponseEntity.ok().body(result);
}



}



