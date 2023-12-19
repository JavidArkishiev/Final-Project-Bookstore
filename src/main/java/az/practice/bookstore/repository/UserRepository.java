package az.practice.bookstore.repository;

import az.practice.bookstore.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {


    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
