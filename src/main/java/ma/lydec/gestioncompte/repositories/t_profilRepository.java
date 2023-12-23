package ma.lydec.gestioncompte.repositories;

import ma.lydec.gestioncompte.entities.t_actions;
import ma.lydec.gestioncompte.entities.t_profil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ma.lydec.gestioncompte.entities.utilisateurs;
import java.util.List;


@Repository
public interface t_profilRepository extends JpaRepository<t_profil,Long> {
    Page<t_profil> findByProfNumContains(String Kw , Pageable pageable);
    void deleteByProfNum(Long profNum);

    t_profil findByProfNum(Long profNum);


    @Query("SELECT c FROM t_profil c LEFT JOIN t_profil_utilisateur cp ON c = cp.t_profil AND cp.utilisateurs = :utilisateurs WHERE cp.t_profil IS NULL")
    List<t_profil> findProfilNonAssociees(@Param("utilisateurs") utilisateurs utilisateurs);



}