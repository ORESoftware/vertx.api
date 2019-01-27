package huru.repository;

import huru.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository to connect our service bean to data
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
