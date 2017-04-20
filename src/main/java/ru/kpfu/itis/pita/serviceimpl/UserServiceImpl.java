package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.entity.UserRole;
import ru.kpfu.itis.pita.repository.UserRepository;
import ru.kpfu.itis.pita.service.UserService;

import java.util.List;

/**
 * Created by 1 on 08.04.2017.
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) {
        user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPasswordHash()));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllWorkers() {
        return userRepository.findAllByRole(UserRole.ROLE_WORKER);
    }

    @Override
    public ru.kpfu.itis.pita.security.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null)
            throw new UsernameNotFoundException("User with this email not found");

        return new ru.kpfu.itis.pita.security.UserDetails(user);
    }
}
