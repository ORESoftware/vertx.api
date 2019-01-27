package huru.service;

import huru.entity.User;
import huru.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Simple Spring service bean to expose the results of a trivial database call
 */
@Service
public class UserService {
  
  @Autowired
  private UserRepository repo;
  
  public List<User> getAllUsers() {
    return repo.findAll();
  }
  
}
