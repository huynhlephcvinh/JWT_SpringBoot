package com.example.SpringJwtAuthentication.security.userprincal;

import com.example.SpringJwtAuthentication.model.User;
import com.example.SpringJwtAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    // hàm tìm xem user có tồn tại trên db hay không
    @Override
    @Transactional // liên quan tới db
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found -> username or password"+username));
        return UserPrinciple.build(user);
    }
}
