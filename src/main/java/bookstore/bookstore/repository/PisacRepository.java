package bookstore.bookstore.repository;

import bookstore.bookstore.model.Pisac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PisacRepository extends JpaRepository<Pisac,Long> {
}
