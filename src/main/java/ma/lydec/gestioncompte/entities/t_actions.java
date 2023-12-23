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

@ToString
@AllArgsConstructor
public class t_actions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long actId;
    private String act_lib;
    @ManyToOne
    @JoinColumn(name = "appId")
    private t_apps t_apps;
}


