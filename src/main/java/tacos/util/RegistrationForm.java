package tacos.util;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.domain.Role;
import tacos.domain.User;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yuanfu
 * @data 2021/5/20 19:16
 */
@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String email;

    private Boolean enabled = true;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
            username,
            passwordEncoder.encode(password),
            enabled,
            email,
            simpleDateFormat.format(new Date())
        );
    }

}
