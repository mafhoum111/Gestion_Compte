package ma.lydec.gestioncompte.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor

@ToString
public class utilisateurs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long matr;
    private String login;
    private String prenom;
    private String nom;
    @OneToMany(mappedBy = "utilisateurs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<t_profil_utilisateur> profilUtilisateurs = new ArrayList<>();
}