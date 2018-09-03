package team.project.dairymanagementsystem.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;


public class Authenticate {
    public Collection<? extends GrantedAuthority> getAuthorities(String userRole) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        Set<String> roles = new HashSet<>();

        roles.add(userRole);
        roles.forEach(role -> {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        });

        return simpleGrantedAuthorities;
    }
}
