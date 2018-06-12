package team.project.dairymanagementsystem.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.project.dairymanagementsystem.model.User;

import java.util.*;

public class DairyUserPrincipal implements UserDetails{
    private User user;
    private List<RoleGroup> roleGroups;

    public DairyUserPrincipal(User user, List<RoleGroup> roleGroups) {
        this.user = user;
        this.roleGroups = roleGroups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roleGroups == null){
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (RoleGroup roleGroup: roleGroups ) {
            authorities.add(new SimpleGrantedAuthority(roleGroup.getRole()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getNationalId()+"";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
