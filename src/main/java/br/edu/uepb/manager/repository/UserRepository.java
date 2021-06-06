package br.edu.uepb.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.uepb.manager.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    
}
