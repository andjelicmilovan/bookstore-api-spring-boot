package bookstore.bookstore.controller;

import bookstore.bookstore.model.*;
import bookstore.bookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.lang.System.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")

public class RestApiController {
    @Autowired
    private IzdavacRepository izdavacRepository;
    @Autowired
    private KategorijaRepository kategorijaRepository;
    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private PisacRepository pisacRepository;
    @Autowired
    private MjestoRepository mjestoRepository;

    @GetMapping("/lista-knjiga")
    public List<Knjiga> getKnjige(){
        return  knjigaRepository.findAll();
    }
    @GetMapping("/lista-kategorija")
    public  List<Kategorija> getListaKategorija(){
        return  kategorijaRepository.findAll();

    }
    @GetMapping("/lista-izdavaca")
    public  List<Izdavac> getIzdavaci(){
        return izdavacRepository.findAll();
    }
    @GetMapping("/lista-mjesta")
    public  List<Mjesto> getMjesta(){
        return mjestoRepository.findAll();
    }
    @GetMapping("/lista-pisaca")
    public List<Pisac> getPisci(){
        return pisacRepository.findAll();
    }
    @PostMapping("/dodaj-kategoriju")
    public Kategorija posaljiPorudzinu(@RequestBody Kategorija kategorija){
        return kategorijaRepository.save(kategorija);
    }
    @PostMapping("/dodaj-mjesto")
    public Mjesto dodajMjesto(@RequestBody Mjesto mjesto){
        return mjestoRepository.save(mjesto);
    }
    @PostMapping("/dodaj-pisca")
    public Pisac dodajPisca(@RequestParam("mjestoId") long mjestoId,@RequestBody Pisac pisac){

        Pisac pisac1 = new Pisac();
        Optional<Mjesto> mjestoObs = mjestoRepository.findById(mjestoId);
        Mjesto mjesto=mjestoObs.get();
        out.println(mjesto);
        pisac1.setIme(pisac.getIme());
        pisac1.setPrezime(pisac.getPrezime());
        pisac1.setGodinaRodjenja(pisac.getGodinaRodjenja());
     pisac1.setMjesto(mjesto);
        out.println(pisac1);
       return pisacRepository.save(pisac1);

    }
    @PostMapping("/dodaj-knjigu")
    public  Knjiga dodajKnjigu(@RequestBody Knjiga knjiga,@RequestParam("pisacId") long pisacId,
                               @RequestParam("izdavacId") long izdavacId,
                               @RequestParam("kategorijaId") long kategorijaId,
                               @RequestParam("mjestoId") long mjestoId){
        Knjiga knjiga1 = new Knjiga();
        Optional<Pisac> pisacOptional = pisacRepository.findById(pisacId);
        Pisac pisac = pisacOptional.get();
        Optional<Izdavac> izdavacOptional = izdavacRepository.findById(izdavacId);
        Izdavac izdavac = izdavacOptional.get();
        Optional<Kategorija> kategorijaOptional = kategorijaRepository.findById(kategorijaId);
        Kategorija kategorija = kategorijaOptional.get();
        Optional<Mjesto> mjestoOptional = mjestoRepository.findById(mjestoId);
        Mjesto mjesto = mjestoOptional.get();
        knjiga1.setMjesto(knjiga.getMjesto());
        knjiga1.setBrojStranica(knjiga.getBrojStranica());
        knjiga1.setDatum(knjiga.getDatum());
        knjiga1.setSlika(knjiga.getSlika());
        knjiga1.setIzdavac(izdavac);
        knjiga1.setKategorija(kategorija);
        knjiga1.setPisac(pisac);
        knjiga1.setMjesto(mjesto);
        knjiga1.setNaziv(knjiga.getNaziv());


       return  knjigaRepository.save(knjiga1);
    }
    @GetMapping("/uzmi-knjigu/{id}")
    public ResponseEntity<Knjiga> getKnjigaById(@PathVariable("id") long id){

        return knjigaRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());

    }
    @GetMapping("/uzmi-pisca/{id}")
    public ResponseEntity<Pisac> uzmiPisca(@PathVariable("id") long id){
        return pisacRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/obrisi-knjigu/{id}"})
    public ResponseEntity<?> deleteKnjigu(@PathVariable("id") long id) {
        return knjigaRepository.findById(id)
                .map(record -> {
                    knjigaRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping(value="/knjiga/{id}")
    public ResponseEntity<Knjiga> updateProductCategories(@PathVariable("id") long id,
                                                                   @RequestBody Knjiga knjiga){
        return knjigaRepository.findById(id)
                .map(record -> {
                    record.setNaziv(knjiga.getNaziv());
                    record.setPisac(knjiga.getPisac());
                    record.setIzdavac(knjiga.getIzdavac());
                    record.setMjesto(knjiga.getMjesto());
                    record.setKategorija(knjiga.getKategorija());
                    record.setBrojStranica(knjiga.getBrojStranica());
                    Knjiga updated = knjigaRepository.save(record);
                    out.println(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

}
