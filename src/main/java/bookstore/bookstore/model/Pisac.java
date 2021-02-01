package bookstore.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Pisac {
    @Id
    @GeneratedValue
    private long id;
    private String ime;
    private String prezime;
    private long godinaRodjenja;
    @ManyToOne
    @JoinColumn(name = "mjesto_id")
    private Mjesto mjesto;

}
