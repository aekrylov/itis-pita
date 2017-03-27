package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.misc.UserDetails;
import ru.kpfu.itis.pita.repository.UserRepository;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/23/17 10:28 PM
 */

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository ur;

    @Autowired
    public void setUr(UserRepository ur) {
        this.ur = ur;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = ur.findByEmail(email);
        if(user == null)
            throw new UsernameNotFoundException("User with this email not found");
        
        return new UserDetails(user);
    }
}
