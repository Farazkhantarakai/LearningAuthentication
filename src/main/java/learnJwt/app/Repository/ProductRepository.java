package learnJwt.app.Repository;


import learnJwt.app.Modle.Product;
import learnJwt.app.Modle.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {


}
