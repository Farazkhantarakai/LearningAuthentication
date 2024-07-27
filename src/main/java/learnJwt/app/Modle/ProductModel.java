package learnJwt.app.Modle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.jdbc.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(unique = true,nullable = false)
    private String Sku;
  private String description;
  private Double price;
  private Double salePrice;
  private Double averageRating;
  @Enumerated(EnumType.STRING)
  private Status status;

// private  String mainImage;
 @ElementCollection
 private List<String> images;
  @ElementCollection
  private List<String> colors;
  private Integer stockQuantity;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;





}




