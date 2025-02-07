package org.reist.msvc.user.services;

import org.reist.msvc.user.entity.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    List<Role> findAll();
    Optional<Role> findById(Long id);

    Optional<Role> findByNameRole(String nameRole);
    void save (Role role);
    void delete(Long id);

}
