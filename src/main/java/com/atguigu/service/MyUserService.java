package com.atguigu.service;

import com.atguigu.bean.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Component
public class MyUserService implements UserDetailsService {
    static HashMap<String, User> map =new HashMap<String, User>();
    static {
       map.put("zhangsan",new User("zhangsan","$2a$10$8TUy4NYqDxluPoW9XnapguiCf9qyeb1HscaxF.4T8pC4Sur92Fiki",1));
       map.put("lisi",new User("lisi","123",1));
       map.put("zhaowu",new User("zhaowu","123",0));
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        System.out.println(name);
        User user = map.get(name);
        System.out.println(user);
    if (user==null){
        return null;
    }

        Collection<SimpleGrantedAuthority> collection=new ArrayList<SimpleGrantedAuthority>();
        collection.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),collection);
    }
}
