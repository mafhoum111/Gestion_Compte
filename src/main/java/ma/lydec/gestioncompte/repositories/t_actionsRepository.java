package ma.lydec.gestioncompte.repositories;

import ma.lydec.gestioncompte.entities.t_actions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface t_actionsRepository extends JpaRepository<t_actions,Long> {

    Page<t_actions> findByActIdContains(String Kw , Pageable pageable);

    t_actions findByActId(Long actId);





}