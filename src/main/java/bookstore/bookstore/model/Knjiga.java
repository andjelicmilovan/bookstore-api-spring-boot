package bookstore.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Knjiga {
    @Id
    @GeneratedValue
    private long id;
    private String naziv;
    @ManyToOne
    @JoinColumn(name = "kategorijaId")
    private Kategorija kategorija;
    @ManyToOne
    @JoinColumn(name = "izdavac")
    private Izdavac izdavac;
    @ManyToOne
    @JoinColumn(name = "mjestoIzdavanja")
    private Mjesto mjesto;
    @ManyToOne
    @JoinColumn(name="pisacId")
    private Pisac pisac;
    private String datum;
    private long brojStranica;
    private String slika;




}
