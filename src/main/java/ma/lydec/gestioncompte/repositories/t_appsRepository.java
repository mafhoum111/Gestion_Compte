package ma.lydec.gestioncompte.repositories;

import ma.lydec.gestioncompte.entities.t_apps;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface t_appsRepository extends JpaRepository<t_apps,Long> {

    Page<t_apps> findByAppIdContains(String Kw , Pageable pageable);

    void deleteByAppId(Long appId);

    t_apps findByAppId(Long appId);
}