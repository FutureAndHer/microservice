package com.vi.cloud.microservicesimpleprovideruser.repository;

import com.vi.cloud.microservicesimpleprovideruser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
