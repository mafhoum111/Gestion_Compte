package ma.lydec.gestioncompte.web;
import ma.lydec.gestioncompte.repositories.t_appsRepository;

import ma.lydec.gestioncompte.entities.t_apps;
import ma.lydec.gestioncompte.repositories.t_actions_profilesRepository;
import ma.lydec.gestioncompte.repositories.t_appsRepository;
import jakarta.transaction.Transactional;
import ma.lydec.gestioncompte.entities.t_actions;
import ma.lydec.gestioncompte.entities.t_actions_profiles;
import ma.lydec.gestioncompte.entities.t_actions_profiles_id;
import ma.lydec.gestioncompte.entities.t_profil;
import ma.lydec.gestioncompte.repositories.t_actionsRepository;
import ma.lydec.gestioncompte.repositories.t_profilRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class t_actionsController {

    private t_actionsRepository t_actionsRepository;
    @Autowired
    private  t_appsRepository t_appsRepository;
    private static t_actions_profilesRepository t_actions_profilesRepository;
    public static t_profilRepository t_profilRepository;



    @Autowired
    public t_actionsController(t_profilRepository t_profilRepository,
                               t_actionsRepository t_actionsRepository,
                               t_actions_profilesRepository t_actions_profilesRepository) {
        this.t_profilRepository = t_profilRepository;
        this.t_actionsRepository = t_actionsRepository;
        this.t_actions_profilesRepository = t_actions_profilesRepository;
    }
    @GetMapping("/Actions")
    public String Actions(Model model,
                          @RequestParam(name="page",defaultValue = "0") int page,
                          @RequestParam(name="size",defaultValue = "5") int size,
                          @RequestParam(name="Keyword",defaultValue = "") String Keyword){

        Page<t_actions> actions = t_actionsRepository.findAll( PageRequest.of(page,size));
        model.addAttribute("listActions",actions.getContent());
        model.addAttribute("pages",new int [actions.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("Keyword",Keyword);

        return "Actions";
    }




    @GetMapping("/formAjouterAction")
    public String Ajouter(Model model){
        model.addAttribute("t_actions",new t_actions());
        model.addAttribute("listApp",t_appsRepository.findAll());


        return "formAjouterAction";
    }


    @PostMapping(path="/save2")
    public String Save(Model model,@ModelAttribute t_actions Actions){
        System.out.println(model.getAttribute("actId"+"act_lib"+"t_apps"));
        t_actionsRepository.save(Actions);
        return "formAjouterAction";
    }





    @GetMapping("/formModAct/{actId}")
    public String Modifier(@PathVariable Long actId, Model model) {
        t_actions t_actions = t_actionsRepository.findByActId(actId);
        if (t_actions == null) {
            return "redirect:/appNotFound";
        }
        model.addAttribute("t_actions", t_actions);
        model.addAttribute("listApp", t_appsRepository.findAll());
        return "formModAct";
    }

    @PostMapping(path="/formModAct/save3")
    public String Save3(Model model,@ModelAttribute t_actions Actions){
        System.out.println(model.getAttribute("actId"+"act_lib"+"t_apps"));
        t_actionsRepository.save(Actions);
        return "redirect:/Actions";
    }







    @GetMapping("/formAjouActProf/{profNum}")
    public String formAjouActProf(Model model,
                                                            @PathVariable("profNum") Long profNum,
                                                            @RequestParam(name="page", defaultValue = "0") int page,
                                                            @RequestParam(name="size", defaultValue = "5") int size,
                                                            @RequestParam(name="Keyword", defaultValue = "") String Keyword) {
        Page<t_actions> actions = t_actionsRepository.findAll(PageRequest.of(page, size));
        model.addAttribute("listActions", actions.getContent());
        model.addAttribute("pages", new int[actions.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("Keyword", Keyword);
        model.addAttribute("profNum", profNum);
        t_profil t_profil = t_profilRepository.findByProfNum(profNum);
        model.addAttribute("t_profil", t_profil);

        return "formAjouActProf";
    }





    @PostMapping("/formAjouActProf/{profNum}/associer")
    public String associer(@PathVariable("profNum") Long profNum,
                           @RequestParam(name = "selectedActions", required = false) List<Long> selectedActions) {

        t_profil t_profil = t_profilRepository.findById(profNum).orElse(null);

        if (t_profil != null && selectedActions != null && !selectedActions.isEmpty()) {
            for (Long actId : selectedActions) {

                t_actions t_actions = t_actionsRepository.findById(actId).orElse(null);

                if (t_actions != null) {

                    t_actions_profiles t_actions_profiles = new t_actions_profiles(t_actions, t_profil);
                    t_actions_profilesRepository.save(t_actions_profiles);
                }
            }
        }

        return "redirect:/profil";
    }
























}
