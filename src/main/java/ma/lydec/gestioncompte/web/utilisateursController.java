package ma.lydec.gestioncompte.web;

import jakarta.transaction.Transactional;
import ma.lydec.gestioncompte.entities.*;
import ma.lydec.gestioncompte.repositories.utilisateursRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ma.lydec.gestioncompte.repositories.t_profil_utilisateurRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@NoArgsConstructor
public class utilisateursController {
    @Autowired
    private t_profil_utilisateurRepository t_profil_utilisateurRepository;
    @Autowired
    private utilisateursRepository utilisateursRepository;
    @GetMapping("/utilisateur")
    public String utilisateur(Model model,
                              @RequestParam(name="page",defaultValue = "0") int page,
                              @RequestParam(name="size",defaultValue = "5") int size,
                              @RequestParam(name="Keyword",defaultValue = "") String Keyword){

        Page<utilisateurs> utlisateur = utilisateursRepository.findAll( PageRequest.of(page,size));
       // Page<utilisateurs> utlisateur = utilisateursRepository.findByMatrContains(Keyword , PageRequest.of(page,size));
        model.addAttribute("listUtil",utlisateur.getContent());
        model.addAttribute("pages",new int [utlisateur.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("Keyword",Keyword);

        return "utilisateur";
    }


    @GetMapping("/deletee")
    @Transactional
    public String  deletee(Long matr , String Keyword, int page){
        utilisateursRepository.deleteByMatr(matr);
        return "redirect:/utilisateur?page="+page+"&Keyword="+Keyword;
    }




    @GetMapping("/formAjouterUtil")
    public String Ajouter(Model model){
        model.addAttribute("utilisateur",new utilisateurs());
        return "formAjouterUtil";
    }






    @GetMapping("/formModUtil/{matr}")
    public String formModUtil (@PathVariable Long matr , Model model ){
        utilisateurs utilisateurs = utilisateursRepository.findByMatr(matr);
        if(utilisateurs==null){
            return "redirect:/appNotFound";
        }
        model.addAttribute("utilisateurs",utilisateurs);
        return "formModUtil";
    }



    @PostMapping(path="/formModUtil/save7")
    public String save5(@ModelAttribute utilisateurs utilisateur) {
        System.out.println("login: " + utilisateur.getLogin());
        System.out.println("prenom: " + utilisateur.getPrenom());
        System.out.println("nom: " + utilisateur.getNom());
        utilisateursRepository.save(utilisateur);
        return "redirect:/utilisateur";
    }










    @PostMapping(path="/save6")
    public String Save(Model model, @ModelAttribute utilisateurs utilisateur){
        System.out.println(model.getAttribute("matr"+"login"+"prenom"+"nom"));

        utilisateursRepository.save(utilisateur);
        model.addAttribute("utilisateur",utilisateur);

        return "formAjouterUtil";
    }



    @GetMapping("/formAjouProfUtil")
    public String formAjouProfUtil(){

        return "formAjouProfUtil";
    }

    @GetMapping("/verifExisUtil")
    public String verifExisUtil(){
        return "verifExisUtil";
    }

    @GetMapping("/verifAppartenance")
    public String verifAppartenance(){

        return "verifAppartenance";
    }


   @GetMapping("/associationU")
   public String afficherUtilisateurs(Model model,
                             @RequestParam(name="page",defaultValue = "0") int page,
                             @RequestParam(name="size",defaultValue = "5") int size,
                             @RequestParam(name="Keyword",defaultValue = "") String Keyword){

       Page<t_profil_utilisateur> utlisateure = t_profil_utilisateurRepository.findAll( PageRequest.of(page,size));

       model.addAttribute("t_profil_utilisateur",utlisateure.getContent());
       model.addAttribute("pages",new int [utlisateure.getTotalPages()]);
       model.addAttribute("currentPage",page);
       model.addAttribute("Keyword",Keyword);

       return "associationU";
   }




}