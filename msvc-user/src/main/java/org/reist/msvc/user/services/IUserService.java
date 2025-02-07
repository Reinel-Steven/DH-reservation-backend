package org.reist.msvc.user.services;

import org.reist.msvc.user.entity.User;
import org.reist.msvc.user.entity.Username;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    List<User> findAll(String str);
    Optional<User> findById(Long id);
    List<User> findByIds(Iterable<Long> ids);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByName(String name);
    Optional<User> findByLastName(String lastName);
    Optional<User> findByPhone(String phone);
    void save (User user);
    void delete(Long id);
}
