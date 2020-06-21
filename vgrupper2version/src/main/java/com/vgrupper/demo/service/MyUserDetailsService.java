package com.vgrupper.demo.service;
import com.vgrupper.demo.entity.User;
import com.vgrupper.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//In order to provide our own user service, we will need to implement the UserDetailsService interface.
//
//We'll create a class called MyUserDetailsService that overrides the method loadUserByUsername() of the interface.
//
//In this method, we retrieve the User object using the DAO, and if it exists, wrap it into a MyUserPrincipal object, which implements UserDetails, and returns it:

//Основной интерфейс, который загружает пользовательские данные.
//Он используется во всей структуре как пользовательский DAO и является стратегией, используемой DaoAuthenticationProvider.
//
//Для интерфейса требуется только один метод только для чтения, что упрощает поддержку новых стратегий доступа к данным.
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //Находит пользователя на основе имени пользователя. В реальной реализации поиск может быть чувствительным к регистру или нечувствительным к регистру в
    // зависимости от того, как сконфигурирован экземпляр реализации.
    // В этом случае UserDetails возвращаемый объект может иметь имя пользователя, отличное от того, которое было запрошено.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}