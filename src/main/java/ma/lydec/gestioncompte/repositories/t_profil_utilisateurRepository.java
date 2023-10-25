package ma.lydec.gestioncompte.repositories;

import ma.lydec.gestioncompte.entities.t_profil_utilisateur;
import ma.lydec.gestioncompte.entities.t_profil_utilisateur_id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface t_profil_utilisateurRepository extends JpaRepository<t_profil_utilisateur,t_profil_utilisateur_id> {

    List<t_profil_utilisateur> findAll();
}
