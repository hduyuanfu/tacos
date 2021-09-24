package tacos.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.service.UserRoleService;
import tacos.service.UserService;
import tacos.util.RegistrationForm;

/**
 * @author yuanfu
 * @data 2021/5/20 19:12
 */
@Controller
@Data
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    private final UserRoleService userRoleService;

    private PasswordEncoder passwordEncoder;

    private int rid = 2;

    @Autowired
    public RegistrationController(UserService userService,
                                  UserRoleService userRoleService,
                                  PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm registrationForm) {
        userService.saveUser(registrationForm.toUser(passwordEncoder));
        int uid = userService.lastInsertId();
        userRoleService.add(uid, rid);
        return "redirect:/login";
    }

}
