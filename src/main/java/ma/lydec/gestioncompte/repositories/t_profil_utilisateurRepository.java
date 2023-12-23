package ma.lydec.gestioncompte.repositories;

import ma.lydec.gestioncompte.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import ma.lydec.gestioncompte.entities.t_profil_utilisateur_id;
import java.util.List;

@Repository
public interface t_profil_utilisateurRepository extends JpaRepository<t_profil_utilisateur,t_profil_utilisateur_id> {

    List<t_profil_utilisateur> findAll();


    @Query("SELECT new ma.lydec.gestioncompte.entities.t_profil_utilisateur_id( c.utilisateurs,c.t_profil) FROM t_profil_utilisateur c WHERE c.utilisateurs = :utilisateurs")
    List<t_profil_utilisateur_id> findT_profil_utilisateur_idByUtilisateurs(@Param("utilisateurs") utilisateurs utilisateurs);






    @Query("SELECT c FROM t_profil_utilisateur c WHERE c.utilisateurs = :utilisateurs")
    List<t_profil_utilisateur> findT_profiles_utilisateurByUtilisateurs(@Param("utilisateurs") utilisateurs utilisateurs);



    @Query("SELECT cp FROM t_profil_utilisateur cp WHERE cp.utilisateurs = :utilisateurs")
    List<t_profil_utilisateur> findAssociationsByUtilisateurs(@Param("utilisateurs") utilisateurs utilisateurs);

}
