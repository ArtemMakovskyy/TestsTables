package com.winestoreapp.model.projection;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceCloseProjection {
    private final UserRepositoryCloseProjection userRepository;

    public List<UserCloseProjectionShort> getAllUsers() {
        return userRepository.findAllProjectedBy();
    }

    public Optional<UserCloseProjectionShort> getUserById(Long id) {
        return userRepository.findProjectedById(id);
    }

    public UserCloseProjection createUser(UserCloseProjection user) {
        return userRepository.save(user);
    }

    @PostConstruct
    public void init() {
        UserCloseProjection user = new UserCloseProjection();
        OrderCloseProjection order = new OrderCloseProjection();
        order.setOrderNumber("123456");
        user.setEmail("email");
        user.setUsername("Artem");
        user.setOrders(List.of(order));
        createUser(user);

        final List<UserCloseProjectionShort> allUsers = getAllUsers();
//        final UserCloseProjectionShort userCloseProjectionShort = allUsers.get(1);
//        System.out.println(userCloseProjectionShort.getUsername());
//        System.out.println(userCloseProjectionShort.getUsername());
    }
}