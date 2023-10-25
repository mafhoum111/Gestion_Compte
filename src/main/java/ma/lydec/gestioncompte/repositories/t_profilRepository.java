package ma.lydec.gestioncompte.repositories;

import ma.lydec.gestioncompte.entities.t_actions;
import ma.lydec.gestioncompte.entities.t_profil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface t_profilRepository extends JpaRepository<t_profil,Long> {
    Page<t_profil> findByProfNumContains(String Kw , Pageable pageable);
    void deleteByProfNum(Long profNum);

    t_profil findByProfNum(Long profNum);




}