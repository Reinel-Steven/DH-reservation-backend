package org.reist.msvc.user.repositories;

import org.reist.msvc.user.entity.Username;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsernameRepository extends JpaRepository<Username, Long> {

    boolean existsByNickname(String nickname);

    Optional<Username> findByNickname(String nickname);
}
