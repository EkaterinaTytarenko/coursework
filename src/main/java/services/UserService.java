package services;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repos.UserRepo;

@Service
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFindByUsername = userRepo.findByUsername(username);

        if(userFindByUsername != null)
        {
            return (UserDetails) userFindByUsername;
        }
        return null;
    }

    public UserDetails loadUserByUserId(Long id) throws UsernameNotFoundException {

        User user = userRepo.findById(id).get();
            if(user!= null)
            {
                return (UserDetails) user;
            }
            return null;
        }

    public User addUser(User user) {
        User savedUser = userRepo.saveAndFlush(user);

        return savedUser;

    }


}
