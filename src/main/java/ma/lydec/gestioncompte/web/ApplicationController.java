package ma.lydec.gestioncompte.web;

import jakarta.transaction.Transactional;
import ma.lydec.gestioncompte.entities.t_apps;
import ma.lydec.gestioncompte.repositories.t_appsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ApplicationController {
    @Autowired
    private t_appsRepository t_appsRepository;

    @GetMapping("/App")
    public String apps(Model model,
                       @RequestParam(name="page",defaultValue = "0") int page,
                       @RequestParam(name="size",defaultValue = "5") int size,
                       @RequestParam(name="Keyword",defaultValue = "") String Keyword
    ){
        Page<t_apps> application = t_appsRepository.findAll(PageRequest.of(page,size));

       // Page<t_apps> application = t_appsRepository.findByAppIdContains(Keyword ,PageRequest.of(page,size));

        model.addAttribute("listApps",application.getContent());
        model.addAttribute("pages",new int [application.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("Keyword",Keyword);
        return "App";

    }

    @GetMapping("/delete")
    @Transactional
    public String  delete(Long appId , String Keyword, int page){
        t_appsRepository.deleteByAppId(appId);
        return "redirect:/App?page="+page+"&Keyword="+Keyword;
    }

    @GetMapping("/")
    public String home(){

        return "redirect:/APP";
    }





        @GetMapping("/formAjouterApp")
   public String Ajouter(Model model){
        model.addAttribute("t_apps",new t_apps());
        return "formAjouterApp";
   }


    @PostMapping(path="/save")
    public String Save(Model model,@ModelAttribute t_apps App){
        System.out.println(model.getAttribute("domaine"+"version"+"help"+"auteur"+"creation"+"modification"+"ouverte"+"dat_fermeture"+"typ_msg"+"lib_msg"+"lb"));
        t_appsRepository.save(App);
        return "formAjouterApp";
    }


    @GetMapping("/formModApp/{appId}")
    public String Edit (@PathVariable Long appId , Model model ){
        t_apps t_apps = t_appsRepository.findByAppId(appId);
        if(t_apps==null){
            return "redirect:/appNotFound";
        }
        model.addAttribute("t_apps",t_apps);
        return "formModApp";
    }



    @PostMapping(path="/formModApp/save1")
    public String Save1(@ModelAttribute t_apps App) {

        System.out.println("Domaine: " + App.getApp_domaine());
        System.out.println("Version: " + App.getApp_version());
        System.out.println("help: " + App.getApp_help());
        System.out.println("Auteur: " + App.getApp_auteur());
        System.out.println("creation: " + App.getApp_creation());
        System.out.println("modification: " + App.getApp_modification());
        System.out.println("ouverte: " + App.getApp_ouverte());
        System.out.println("dat_fermeture: " + App.getApp_dat_fermeture());
        System.out.println("typ_msg: " + App.getApp_typ_msg());
        System.out.println("lib_msg: " + App.getApp_lib_msg());
        System.out.println("lb: " + App.getApp_lb());


        t_appsRepository.save(App);

        return "redirect:/App";
    }







}