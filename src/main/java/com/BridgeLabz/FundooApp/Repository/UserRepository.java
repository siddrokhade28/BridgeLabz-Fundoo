package com.BridgeLabz.FundooApp.Repository;

import com.BridgeLabz.FundooApp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

//Repository for to Store and retrieve User
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);


//    @Query("select new com.BridgeLabz.FundooApp.DTO.AllUsers(u.firstName,u.lastName,u.email) from users u")
//    List<AllUsers> FindAllUSer();

}
