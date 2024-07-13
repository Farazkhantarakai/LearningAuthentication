package learnJwt.app.Modle;

import jakarta.persistence.*;

@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;




}
