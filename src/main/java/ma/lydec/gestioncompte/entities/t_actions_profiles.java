package ma.lydec.gestioncompte.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
@IdClass(t_actions_profiles_id.class)
public class t_actions_profiles {



    @Id
    @ManyToOne
    @JoinColumn(name= "actId")
    private t_actions t_actions;
    @Id
    @ManyToOne
    @JoinColumn(name= "profNum")
    private t_profil t_profil;
    public t_actions_profiles(t_actions t_actions, t_profil t_profil) {
        this.t_actions = t_actions;
        this.t_profil = t_profil;
    }
}


