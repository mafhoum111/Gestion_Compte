package ma.lydec.gestioncompte.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable

@ToString
public class  t_actions_profiles_id implements Serializable{



    private t_actions t_actions;
    private t_profil t_profil;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        t_actions_profiles_id that = (t_actions_profiles_id) o;
        return Objects.equals(t_actions, that.t_actions) &&
                Objects.equals(t_profil, that.t_profil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t_actions, t_profil);
    }
}