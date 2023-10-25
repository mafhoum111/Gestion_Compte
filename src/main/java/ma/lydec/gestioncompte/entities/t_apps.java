package ma.lydec.gestioncompte.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class t_apps {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appId;
    private String  app_domaine;
    private String app_version;
    private String app_help;
    private String app_auteur;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date app_creation;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date app_modification;
    private String app_ouverte;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date app_dat_fermeture;
    private String app_typ_msg;
    private String app_lib_msg;
    private String app_lb;


}