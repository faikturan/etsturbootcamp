package com.etstour.hotelbooking.services;

import com.etstour.hotelbooking.entity.Role;
import com.etstour.hotelbooking.entity.User;
import com.etstour.hotelbooking.repository.RoleRepository;
import com.etstour.hotelbooking.repository.UserRepository;
import com.etstour.hotelbooking.temp.CurrentUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    //service pattern to manage transactions
    //and handle services for user between server and client

    private static UserRepository userRepository;

    private static RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    private static BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void saveUser(CurrentUser currentUser) {
        User user = new User();

        //bcrypt password to save it hashing in database
        user.setPassword(passwordEncoder.encode(currentUser.getPassword()));

        user.setUsername(currentUser.getUsername());
        user.setEmail(currentUser.getEmail());

        //give user default role of "employee"
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_EMPLOYEE")));

        userRepository.save(user);
    }

    @Override
    @Transactional
    public int getLoggedUserId() {
        User user = userRepository.findByUsername(loggedUserEmail());
        return user.getId();
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(email);

       if(user == null){
           throw new UsernameNotFoundException("Invalid username or password.");
       }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));

    }

    //authority role for user
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    //get current logged user email using security user details principal
    private String loggedUserEmail(){
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }
}
