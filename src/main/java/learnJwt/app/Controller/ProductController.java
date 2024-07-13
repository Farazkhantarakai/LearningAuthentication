package learnJwt.app.Controller;


import learnJwt.app.Modle.Product;
import learnJwt.app.Modle.ProductModel;
import learnJwt.app.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {


    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/postProduct")
 public ResponseEntity<Product> postProduct(@RequestBody Product product) {
 Product createdProduct=   productService.saveProduct(product);
    return new  ResponseEntity<>(createdProduct,HttpStatus.CREATED);
}


@GetMapping("/getProducts")
    public ResponseEntity<List<ProductModel>> getProduct() {
      List<ProductModel> result =  productService.getAllProducts();
        return ResponseEntity.ok().body(result);
}



}



