package org.reist.msvc.user.services.impl;

import org.reist.msvc.user.entity.Role;
import org.reist.msvc.user.repositories.IRoleRepository;
import org.reist.msvc.user.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository dao;

    @Transactional(readOnly = true)
    @Override
    public List<Role> findAll() {
        return dao.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Role> findById(Long id) {
        return dao.findById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Role> findByNameRole(String nameRole) {
        return dao.findByNameRole(nameRole);
    }
    @Transactional
    @Override
    public void save(Role role) {
        dao.save(role);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }
}
