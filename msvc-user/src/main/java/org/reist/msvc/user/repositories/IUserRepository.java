package org.reist.msvc.user.repositories;

import org.reist.msvc.user.entity.User;
import org.reist.msvc.user.entity.Username;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository <User, Long>{

    @Query("	select u "
            + "	from User u "
            + " where u.name like %?1% "
            + "		or u.lastName like %?1% "
            + "		or u.identification like %?1% "
            + "		or u.email like %?1% "
            + " 	or u.phone like %?1%")
    List<User> findAll(String find);

    @Query("SELECT u FROM User u JOIN u.username us WHERE us.nickname = ?1")
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByName(String name);

    Optional<User> findByLastName(String lastName);

    Optional<User> findByPhone(String phone);
}
