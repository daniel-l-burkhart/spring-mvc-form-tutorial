package com.daniel.form.web;

import com.daniel.form.model.User;
import com.daniel.form.service.UserService;
import com.daniel.form.validator.UserFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFormValidator userFormValidator;
    private UserService userService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.userFormValidator);
    }

    /**
     * Sets up the user service
     *
     * @param userService The user service param
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Redirects from index to users list
     *
     * @param model The spring model
     * @return The redirect to users list
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        this.logger.debug("index()");
        return "redirect:/users";
    }

    /**
     * Show all users
     *
     * @param model The spring model
     * @return Returns the list of users
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        this.logger.debug("showAllUsers()");
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    /**
     * Saves or updates user
     *
     * @param user               The user object
     * @param result             The binding result
     * @param model              The spring model
     * @param redirectAttributes The redirect parameter
     * @return Redirects the user to the show user after save or update
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated User user, BindingResult result, Model model,
                                   final RedirectAttributes redirectAttributes) {

        this.logger.debug("saveOrUpdateUser() : {}", user);

        if (result.hasErrors()) {
            populateDefaultModel(model);
            return "users/userform";
        } else {

            this.userService.saveOrUpdate(user);

            redirectAttributes.addFlashAttribute("css", "success");
            if (user.isNew()) {
                redirectAttributes.addFlashAttribute("msg", "User added successfully!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
            }

            return "redirect:/users/" + user.getId();
        }

    }

    /**
     * Shows add user form
     *
     * @param model The model
     * @return Returns the form for the user
     */
    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String showAddUserForm(Model model) {

        logger.debug("showAddUserForm()");

        User user = new User();

        // set default value
        user.setName("daniel123");
        user.setEmail("test@gmail.com");
        user.setAddress("abc 88");
        user.setNewsletter(true);
        user.setSex("M");
        user.setFramework(new ArrayList<String>(Arrays.asList("Spring MVC", "GWT")));
        user.setSkill(new ArrayList<String>(Arrays.asList("Spring", "Grails", "Groovy")));
        user.setCountry("SG");
        user.setNumber(2);

        model.addAttribute("userForm", user);

        populateDefaultModel(model);

        return "users/userform";

    }

    /**
     * Show update form
     *
     * @param id    The user's id
     * @param model The model
     * @return return the user's form for update
     */
    @RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
    public String showUpdateUserForm(@PathVariable("id") int id, Model model) {

        this.logger.debug("showUpdateUserForm() : {}", id);

        User user = userService.findById(id);
        model.addAttribute("userForm", user);

        populateDefaultModel(model);

        return "users/userform";

    }

    /**
     * Deletes user
     *
     * @param id                 The user's id
     * @param redirectAttributes redirect parameter for redirecting after deltion
     * @return redirect to users list
     */
    @RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        this.logger.debug("deleteUser() : {}", id);

        this.userService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "User is deleted!");

        return "redirect:/users";

    }

    /**
     * User detail page
     *
     * @param id    The id of the user
     * @param model The model
     * @return Shows the user
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") int id, Model model) {

        this.logger.debug("showUser() id: {}", id);

        User user = this.userService.findById(id);
        if (user == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "User not found");
        }
        model.addAttribute("user", user);

        return "users/show";

    }

    /**
     * Handler for empty data
     *
     * @param req The request
     * @param ex  The exception
     * @return Returns the model
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("user/show");
        model.addObject("msg", "user not found");

        return model;

    }

    private void populateDefaultModel(Model model) {

        List<String> frameworksList = new ArrayList<String>();
        frameworksList.add("Spring MVC");
        frameworksList.add("Struts 2");
        frameworksList.add("JSF 2");
        frameworksList.add("GWT");
        frameworksList.add("Play");
        frameworksList.add("Apache Wicket");
        model.addAttribute("frameworkList", frameworksList);

        Map<String, String> skill = new LinkedHashMap<String, String>();
        skill.put("Hibernate", "Hibernate");
        skill.put("Spring", "Spring");
        skill.put("Struts", "Struts");
        skill.put("Groovy", "Groovy");
        skill.put("Grails", "Grails");
        model.addAttribute("javaSkillList", skill);

        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        model.addAttribute("numberList", numbers);

        Map<String, String> country = new LinkedHashMap<String, String>();
        country.put("US", "United Stated");
        country.put("CA", "Canada");
        country.put("MX", "Mexico");
        country.put("UK", "United Kingdom");
        model.addAttribute("countryList", country);
    }

}