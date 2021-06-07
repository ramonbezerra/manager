package br.edu.uepb.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.uepb.manager.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
