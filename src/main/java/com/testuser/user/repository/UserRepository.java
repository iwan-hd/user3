package com.testuser.user.repository;

import com.testuser.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long>{
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

//    @Modifying      // to mark delete or update query
//    @Query(value = "DELETE FROM user_roles e WHERE user_id = :userid")       // it will delete all the record with specific name
//    int deleteUserId(@Param("userid") Long userId);

}
