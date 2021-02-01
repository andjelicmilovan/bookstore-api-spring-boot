package bookstore.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Kategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nazivKategorije;

}
