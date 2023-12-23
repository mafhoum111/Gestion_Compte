package ma.lydec.gestioncompte.web;

import jakarta.transaction.Transactional;
import ma.lydec.gestioncompte.entities.*;
import ma.lydec.gestioncompte.entities.t_profil;
import ma.lydec.gestioncompte.entities.utilisateurs;
import ma.lydec.gestioncompte.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Controller
@AllArgsConstructor
public class t_profilController {
    private  final t_profilRepository t_profilRepository;
    private final utilisateursRepository utilisateursRepository;
    private  final t_profil_utilisateurRepository t_profil_utilisateurRepository;

    private  t_actions_profilesRepository t_actions_profilesRepository;
    private t_profilRepository repository;





    @Autowired
    public t_profilController(utilisateursRepository utilisateursRepository,
                              t_profilRepository t_profilRepository,
                               t_profil_utilisateurRepository t_profil_utilisateurRepository,
                              t_actions_profilesRepository t_actions_profilesRepository ) {
        this.utilisateursRepository = utilisateursRepository;
        this.t_profilRepository = t_profilRepository;
        this.t_profil_utilisateurRepository = t_profil_utilisateurRepository;
        this.t_actions_profilesRepository = t_actions_profilesRepository;
    }



    @GetMapping("/profil")
    public String profil(Model model,
                         @RequestParam(name="page",defaultValue = "0") int page,
                         @RequestParam(name="size",defaultValue = "5") int size,
                         @RequestParam(name="Keyword",defaultValue = "") String Keyword){

        Page<t_profil> profil = t_profilRepository.findAll( PageRequest.of(page,size));
       // Page<t_profil> profil = t_profilRepository.findByProfNumContains(Keyword , PageRequest.of(page,size));
        model.addAttribute("listprof",profil.getContent());
        model.addAttribute("pages",new int [profil.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("Keyword",Keyword);

        return "profil";
    }

    @GetMapping("/supprimer")
    @Transactional

    public String  supprimer(Long profNum , Long Keyword, int page){
        t_profilRepository.deleteByProfNum(profNum);
        return "redirect:/profil?page="+page+"&Keyword="+Keyword;
    }


    @GetMapping("/formAjouterProfil")
    public String formAjouProf(Model model){
        model.addAttribute("t_profil",new t_profil());
        return "formAjouterProfil";
    }

    @PostMapping(path="/save4")
    public String Save(Model model,@ModelAttribute t_profil profil){
        System.out.println(model.getAttribute("profNum"+"prof_nom"));
        t_profilRepository.save(profil);
        return "formAjouterProfil";
    }




    @GetMapping("/formModProf/{profNum}")
    public String formModProf (@PathVariable Long profNum , Model model ){
        t_profil t_profil = t_profilRepository.findByProfNum(profNum);
        if(t_profil==null){
            return "redirect:/appNotFound";
        }
        model.addAttribute("t_profil",t_profil);
        return "formModProf";
    }



    @PostMapping(path="/formModProf/save5")
    public String save5(@ModelAttribute t_profil profil) {

        System.out.println("prof_nom: " + profil.getProf_nom());
        t_profilRepository.save(profil);
        return "redirect:/profil";
    }




    @GetMapping("/formAjouActProf")
    public String formAjouActProf(){

        return "formAjouActProf";
    }













    @PostMapping("/formAjouProfUtil/{matr}/associer")
    public String associer(@PathVariable("matr") Long matr,
                           @RequestParam(name = "selectedProfils", required = false) List<Long> selectedProfils) {

        utilisateurs utilisateurs = utilisateursRepository.findById(matr).orElse(null);

        if (utilisateurs != null && selectedProfils != null && !selectedProfils.isEmpty()) {

            List<t_profil_utilisateur_id> profilsAssocieesIds = t_profil_utilisateurRepository.findT_profil_utilisateur_idByUtilisateurs(utilisateurs);

            if (profilsAssocieesIds == null) {
                profilsAssocieesIds = new ArrayList<>();
            }

            for (Long profNum : selectedProfils) {

                if (!profilsAssocieesIds.contains(profNum)) {
                    t_profil t_profil = t_profilRepository.findById(profNum).orElse(null);

                    if (t_profil != null) {
                        t_profil_utilisateur t_profil_utilisateur= new t_profil_utilisateur( t_profil,utilisateurs);
                        t_profil_utilisateurRepository.save(t_profil_utilisateur);
                    }
                }
            }
        }

        return "redirect:/utilisateur";
    }

    @GetMapping("/formAjouProfUtil/{matr}")
    public String formAjouProfUtil(Model model,
                                  @PathVariable("matr") Long matr,
                                  @RequestParam(name="page", defaultValue = "0") int page,
                                  @RequestParam(name="size", defaultValue = "5") int size,
                                  @RequestParam(name="Keyword", defaultValue = "") String Keyword) {


        utilisateurs utilisateurs = utilisateursRepository.findByMatr(matr);
        model.addAttribute("utilisateurs", utilisateurs);

        Page<t_profil> profils = null;
        List<t_profil> profilsDisponibles = null;


        List<t_profil_utilisateur> profilsAssociees = t_profil_utilisateurRepository.findT_profiles_utilisateurByUtilisateurs(utilisateurs);

        if (profilsAssociees.isEmpty()) {

            profils = t_profilRepository.findAll(PageRequest.of(page, size));
            model.addAttribute("listProfils", profils.getContent());
        } else {

            profilsDisponibles = t_profilRepository.findProfilNonAssociees(utilisateurs);
            model.addAttribute("listProfils", profilsDisponibles);
        }


        model.addAttribute("pages", new int[profils != null ? profils.getTotalPages() : 0]);
        model.addAttribute("currentPage", page);
        model.addAttribute("Keyword", Keyword);
        model.addAttribute("profNum", matr);

        return "formAjouProfUtil";
    }


    @GetMapping("/associationU/{matr}")
    public String afficherAssociations(Model model, @PathVariable("matr") Long matr) {

        utilisateurs utilisateurs = utilisateursRepository.findByMatr(matr);


        List<t_profil_utilisateur> associations = t_profil_utilisateurRepository.findAssociationsByUtilisateurs(utilisateurs);


        model.addAttribute("utilisateurs", utilisateurs);
        model.addAttribute("associations", associations);

        return "associationU";
    }




































}









