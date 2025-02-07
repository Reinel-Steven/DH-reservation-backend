package org.reist.msvc.user.services.impl;

import org.reist.msvc.user.entity.User;
import org.reist.msvc.user.entity.Username;
import org.reist.msvc.user.repositories.IUserRepository;
import org.reist.msvc.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userDao;
    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> findAll(String str) {
        return userDao.findAll(str);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findByIds(Iterable<Long> ids) {
        return userDao.findAllById(ids);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByName(String name) {
        return userDao.findByName(name);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByLastName(String lastName) {
        return  userDao.findByLastName(lastName);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByPhone(String phone) {
        return userDao.findByPhone(phone);
    }
    @Transactional
    @Override
    public void save(User user) {
        userDao.save(user);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }
}
