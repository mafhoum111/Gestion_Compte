package ma.lydec.gestioncompte.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class t_profil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long profNum;
    private String prof_nom;
    @OneToMany(mappedBy = "t_profil", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<t_actions_profiles> actionsProfiles = new ArrayList<>();

}
