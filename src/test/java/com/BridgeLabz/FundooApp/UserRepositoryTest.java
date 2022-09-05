//package com.BridgeLabz.FundooApp;
//
//import com.BridgeLabz.FundooApp.Model.User;
//import com.BridgeLabz.FundooApp.Repository.UserRepository;
//import static org.assertj.core.api.Assertions.assertThat;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
//public class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @Test
//    public void testCreateUser(){
//        User user= new User();
//        user.setFirstName("siddanth");
//        user.setLastName("rokhade");
//        user.setEmail("sidrokhade@gmail.com");
//        user.setPassword("987654321");
//
//        User savedUser= userRepository.save(user);
//
//        User existUser = testEntityManager.find(User.class,savedUser.getId());
//
//        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
//
//    }
//}
