package bookstore.bookstore.repository;

import bookstore.bookstore.model.Mjesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MjestoRepository  extends JpaRepository<Mjesto,Long> {

}
