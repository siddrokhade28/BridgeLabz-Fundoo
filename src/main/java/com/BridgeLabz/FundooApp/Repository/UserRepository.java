package com.BridgeLabz.FundooApp.Repository;

import com.BridgeLabz.FundooApp.DTO.AllUsers;
import com.BridgeLabz.FundooApp.Model.User;
import net.bytebuddy.description.type.TypeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPassword(String password);
    @Query("select new com.BridgeLabz.FundooApp.DTO.AllUsers(u.firstName,u.lastName,u.email) from users u")
    List<AllUsers> FindAllUSer();

}
