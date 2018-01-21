package com.dpiotr.services;

import com.dpiotr.model.SystemUser;
import com.dpiotr.model.viewmodels.SystemUserViewModel;
import com.dpiotr.repository.SystemUserRepository;
import com.dpiotr.utilities.PasswordUtilities;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpiotr on 04.12.17.
 */
@Component
public class SystemUserService implements UserDetailsService {
    private SystemUserRepository systemUserRepository;

    public SystemUserService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SystemUser applicationUser = systemUserRepository.findByEmail(email);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(email);
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(applicationUser.getRole()));

        return new User(applicationUser.getEmail(), applicationUser.getPassword(), grantedAuthorityList);
    }

    public void editSystemUser(SystemUser su, SystemUserViewModel suvm) {
        su.setName(suvm.getName());
        su.setSurname(suvm.getSurname());
        su.setEmail(suvm.getEmail());
        su.setPassword(PasswordUtilities.getHashFor(suvm.getPassword()));
        su.setRole(suvm.getRole());
        systemUserRepository.save(su);
    }
}
