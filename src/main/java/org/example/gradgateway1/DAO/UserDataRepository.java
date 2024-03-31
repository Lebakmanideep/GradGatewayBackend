package org.example.gradgateway1.DAO;

import org.example.gradgateway1.Entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

    @Query("SELECT u FROM UserData u WHERE u.id = :id")
    UserData findByUserId(Long id);
}
