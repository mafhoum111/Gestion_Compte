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
public class t_profil_utilisateur_id implements Serializable{
    private utilisateurs utilisateurs;
    private t_profil t_profil;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        t_profil_utilisateur_id that = (t_profil_utilisateur_id) o;
        return Objects.equals(utilisateurs, that.utilisateurs) &&
                Objects.equals(t_profil, that.t_profil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utilisateurs, t_profil);
    }}
