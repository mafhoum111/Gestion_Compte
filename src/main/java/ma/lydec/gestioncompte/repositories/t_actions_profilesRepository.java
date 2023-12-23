package ma.lydec.gestioncompte.repositories;

import ma.lydec.gestioncompte.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface t_actions_profilesRepository extends JpaRepository<t_actions_profiles, t_actions_profiles_id> {
    List<t_actions_profiles> findAll();





    @Query("SELECT new ma.lydec.gestioncompte.entities.t_actions_profiles_id(a.t_actions, a.t_profil) FROM t_actions_profiles a WHERE a.t_profil = :t_profil")
    List<t_actions_profiles_id> findT_actions_profiles_idByT_profil(@Param("t_profil") t_profil t_profil);






    @Query("SELECT a FROM t_actions_profiles a WHERE a.t_profil = :t_profil")
    List<t_actions_profiles> findT_actions_profilesByT_profil(@Param("t_profil") t_profil t_profil);



    @Query("SELECT ap FROM t_actions_profiles ap WHERE ap.t_profil = :t_profil")
    List<t_actions_profiles> findAssociationsByProfil(@Param("t_profil") t_profil t_profil);

}







