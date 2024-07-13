package learnJwt.app.Modle;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
private int id;

     @Column(unique=true , nullable=false)
    private String username;

    private String password;
    @Column(unique=true , nullable=false)
    private String roles;

}
