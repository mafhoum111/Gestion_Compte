package ma.lydec.gestioncompte.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(t_profil_utilisateur_id.class)

@ToString
public class t_profil_utilisateur {
    @Id
    @ManyToOne
    @JoinColumn(name= "matr")
    private utilisateurs utilisateurs;
    @Id
    @ManyToOne
    @JoinColumn(name= "profNum")

    private t_profil t_profil;

    public t_profil_utilisateur( t_profil t_profil , utilisateurs utilisateurs) {
        this.t_profil = t_profil;
        this.utilisateurs = utilisateurs;
    }
}
