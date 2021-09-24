package tacos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/20 16:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    private int id;
    private String username;
    private String password;
    // mysql中没有boolean,会自动把tinyint中的1,0 对应成true,false。所以数据库中是tingint,而这里是boolean,不会出错，会对应起来
    private boolean enabled;
    private String email;
    private String regtime;
    private Role role;

    public User(String username, String password, Boolean enabled,
                String email, String regtime) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.regtime = regtime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 用户账号是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户账号是否被锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
