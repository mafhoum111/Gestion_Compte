package ma.lydec.gestioncompte.repositories;

import ma.lydec.gestioncompte.entities.t_actions_profiles;
import ma.lydec.gestioncompte.entities.t_actions_profiles_id;
import ma.lydec.gestioncompte.entities.t_profil_utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface t_actions_profilesRepository extends JpaRepository<t_actions_profiles , t_actions_profiles_id> {
    List<t_actions_profiles> findAll();
}