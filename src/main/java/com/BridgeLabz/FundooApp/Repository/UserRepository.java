package com.BridgeLabz.FundooApp.Repository;

import com.BridgeLabz.FundooApp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);


    @Query(value = "select u.first_name,u.last_name,u.email from users u",nativeQuery = true)
    List<User> FindAllUSer();


    Optional<User> findByPassword(String password);
}
