package lk.ijse.appspringboot.dao;

import lk.ijse.appspringboot.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, String> {
}
