package org.reist.msvc.user.services;

import org.reist.msvc.user.entity.Username;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IUsernameService {

    List<Username> findAll();
    Optional<Username> findById(Long id);

    Optional<Username> findByNickname(String nickname);

    boolean existsByNickname(String nickname);
    List<Username> findByIds(Iterable<Long> ids);

    void save (Username username);
    void delete(Long id);
}
