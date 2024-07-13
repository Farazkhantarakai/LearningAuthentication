package learnJwt.app.Services;

import jakarta.validation.constraints.NotNull;
import learnJwt.app.Modle.Product;
import learnJwt.app.Modle.ProductModel;
import learnJwt.app.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
     private final ProductRepository productRepository;
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
public Product saveProduct(@NotNull Product product) {
ProductModel productModel= mapToEntity(product);
ProductModel result=    productRepository.save(productModel);
  return  mapToModel(result);
}


private ProductModel mapToEntity(Product product) {
    ProductModel productModel=new ProductModel();
    productModel.setProductName(product.getProductName());
    productModel.setDescription(product.getProductDescription());
    productModel.setPrice(product.getProductPrice());
    productModel.setSalePrice(product.getProductSalePrice());
    productModel.setColors(product.getColors());
    productModel.setStockQuantity(product.getStockQuantity());
    String sku=  setSku(product.getProductName(),product.getColors());
    productModel.setSku(sku);
    productModel.setCreatedDate(Date.from(Instant.now()));
    productModel.setUpdatedDate(Date.from(Instant.now()));


return productModel;
}


private String setSku(@NotNull String productName,@NotNull List<String> colors) {
  String randomValues=  UUID.randomUUID().toString().substring(0,8);
    return randomValues.toUpperCase() + productName.replaceAll("/,","").toUpperCase()+ colors.get(1).replaceAll(",/","").toUpperCase();
}



private Product mapToModel(ProductModel productModel) {
        Product product=new Product();
        product.setProductName(productModel.getProductName());
        product.setProductDescription(productModel.getDescription());
        product.setProductPrice(productModel.getPrice());
        product.setProductSalePrice(productModel.getSalePrice());
        product.setColors(productModel.getColors());
        product.setStockQuantity(productModel.getStockQuantity());
        return product;
}
    public List<ProductModel> getAllProducts() {

        return productRepository.findAll();
    }
}
