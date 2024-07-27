package learnJwt.app.Services;

import jakarta.validation.constraints.NotNull;
import learnJwt.app.Modle.Product;
import learnJwt.app.Modle.ProductModel;
import learnJwt.app.Repository.ProductRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Service
public class ProductService {


    String directory="src/main/resources/static/images";
     private final ProductRepository productRepository;
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
public Product saveProduct(@NotNull Product product) throws IOException {
  List<String>  allUrls=     imagesUploader(product.getImages());

ProductModel productModel= mapToEntity(product,allUrls);
productModel.setImages(allUrls);
ProductModel result=    productRepository.save(productModel);
  return  mapToModel(result);
}


private ProductModel mapToEntity(Product product,List<String> images) {
    ProductModel productModel=new ProductModel();
    productModel.setProductName(product.getProductName());
    productModel.setDescription(product.getProductDescription());
    productModel.setPrice(product.getProductPrice());
    productModel.setSalePrice(product.getProductSalePrice());
    productModel.setColors(product.getColors());
    productModel.setStockQuantity(product.getStockQuantity());
    //this will set sku unit of the product item
    String sku=  setSku(product.getProductName(),product.getColors(),product.getProdSize());
    productModel.setSku(sku);
    productModel.setCreatedDate(Date.from(Instant.now()));
    productModel.setUpdatedDate(Date.from(Instant.now()));
    productModel.setImages( images);
//    productModel.setSize(product.getProdSize());


return productModel;
}




private String setSku(@NotNull String productName,@NotNull List<String> colors,String size) {
  String randomValues=  UUID.randomUUID().toString().substring(0,8);
    return randomValues.toUpperCase() + productName.replaceAll("/,","").toUpperCase()  + "-" ;
//            +    getSize(size).toUpperCase() + "-" ;

}


    private List<String> imagesUploader(List<MultipartFile> images) throws IOException {

      if(images.isEmpty()){
          throw new FileNotFoundException("File not found");
      }

       List<String>  allUrls= new ArrayList<>();

            for (MultipartFile file : images) {
//            this will convert the name  to path of the variable
                Path path = Path.of(directory + file.getOriginalFilename());

                if (Files.exists(path)) {
                    throw new FileAlreadyExistsException("File already exists");
                }

                Files.createDirectory(path); //this will create directory if it does not exist
                Path newPath = path.resolve(Objects.requireNonNull(file.getOriginalFilename()));
                //this will copy the image to this p
                Files.write(newPath,file.getBytes());
                allUrls.add(directory + file.getOriginalFilename());
            }


return  allUrls;
    }


    private String getSize(String size) {
        switch (size){
            case "X-SMALL":
                return "XSM";
            case "SMALL":
                return "SML";

                case "MEDIUM":
                    return "MED";
                    case "LARGE":
                        return "LRG";
                        case "X-LARGE":
                            return "XLG";
            case "XX-LARGE":
                return "XXL";

            default:
                return size;
        }
    }


private Product mapToModel(ProductModel productModel)  {
        Product product=new Product();
        product.setProductName(productModel.getProductName());
        product.setProductDescription(productModel.getDescription());
        product.setProductPrice(productModel.getPrice());
        product.setProductSalePrice(productModel.getSalePrice());
        product.setColors(productModel.getColors());
        product.setStockQuantity(productModel.getStockQuantity());
        product.setImagesPath(productModel.getImages());
//        product.setProdSize(productModel.getSize().toString());
        return product;
}
    public List<ProductModel> getAllProducts() {

        return productRepository.findAll();
    }
}
