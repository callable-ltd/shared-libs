package uk.co.vibe.viva.shared.auth;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;
import uk.co.vibe.viva.shared.dto.user.UserCustomerDTO;
import uk.co.vibe.viva.shared.dto.user.UserDTO;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@ToString
public class VivaUserDetails implements UserDetails {

    private String id;
    private String name;
    private List<String> roles;
    private List<String> customers;

    public VivaUserDetails() {
        this.id = "";
        this.name = "public";
        this.roles = Collections.singletonList("ROLE_PUBLIC");
        this.customers = new ArrayList<>();
    }

    public VivaUserDetails(UserDTO user) {
        this.id = user.getId();
        this.name = user.getUsername();
        this.roles = user.getRoles();
        this.customers = Optional.ofNullable(user.getCustomers())
                .orElse(new ArrayList<>())
                .stream()
                .map(UserCustomerDTO::getId)
                .collect(Collectors.toList());
        this.customers.add(user.getCustomer().getId());
    }

    public VivaUserDetails(CustomerDTO customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.roles = Collections.singletonList("ROLE_CUSTOMER");
        this.customers = Stream.of(customer)
                .map(CustomerDTO::getId)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isAdmin() {
        return roles.contains("ROLE_ADMIN");
    }

    public boolean isTenant(String customerId) {
        return isAdmin() || customers.contains(customerId);
    }
}
