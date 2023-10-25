package ma.lydec.gestioncompte.repositories;

import ma.lydec.gestioncompte.entities.utilisateurs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface utilisateursRepository extends JpaRepository<utilisateurs,Long> {
    Page<utilisateurs> findByMatrContains(String Kw , Pageable pageable);
    void deleteByMatr(Long matr);

    utilisateurs findByMatr(Long matr);
}