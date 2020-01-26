package com.codingviews.services.user.repository;

import com.codingviews.services.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    @Query("select name from User where id in (:pIdList)")
    List<String> findByIdList(@Param("pIdList") List<Long> idList);
}
