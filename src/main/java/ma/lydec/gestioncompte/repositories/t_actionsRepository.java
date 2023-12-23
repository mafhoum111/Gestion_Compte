package ma.lydec.gestioncompte.repositories;
import ma.lydec.gestioncompte.entities.t_profil;
import ma.lydec.gestioncompte.entities.t_actions;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface t_actionsRepository extends JpaRepository<t_actions,Long> {

    Page<t_actions> findByActIdContains(String Kw , Pageable pageable);

    t_actions findByActId(Long actId);





    @Query("SELECT a FROM t_actions a LEFT JOIN t_actions_profiles ap ON a = ap.t_actions AND ap.t_profil = :t_profil WHERE ap.t_actions IS NULL")
    List<t_actions> findActionsNonAssociees(@Param("t_profil") t_profil t_profil);





}