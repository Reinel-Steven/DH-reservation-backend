package org.reist.msvc.user.services.impl;

import org.hibernate.Hibernate;
import org.reist.msvc.user.entity.Username;
import org.reist.msvc.user.repositories.IUsernameRepository;
import org.reist.msvc.user.services.IUsernameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsernameServiceImpl implements IUsernameService {

    @Autowired
    private IUsernameRepository dao;
    @Transactional(readOnly = true)
    @Override
    public List<Username> findAll() {
        return dao.findAll();
    }
    @Transactional
    @Override
    public Optional<Username> findById(Long id) {
        Optional<Username> username = dao.findById(id);
        username.ifPresent(value -> Hibernate.initialize(value.getRoles()));
        return username;

    }
    @Transactional
    @Override
    public Optional<Username> findByNickname(String nickname) {
        Optional<Username> username =dao.findByNickname(nickname);
        username.ifPresent(value -> Hibernate.initialize(value.getRoles()));
        return username;
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return dao.existsByNickname(nickname);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Username> findByIds(Iterable<Long> ids) {
        return dao.findAllById(ids);
    }
    @Transactional
    @Override
    public void save(Username username) {
        dao.save(username);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }
}
