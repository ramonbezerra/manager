package br.edu.uepb.manager.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.uepb.manager.domain.Project;
import br.edu.uepb.manager.domain.User;
import br.edu.uepb.manager.dto.ProjectDTO;
import br.edu.uepb.manager.dto.UserProjectDTO;

public class ProjectMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProjectDTO convertToProjectDTO(Project project) {
        List<User> users = project.getUsers();
        users.size();
        List<UserProjectDTO> usersDTO = users.stream()
                                        .map(user -> modelMapper.map(user, UserProjectDTO.class))
                                        .collect(Collectors.toList());

        ProjectDTO projectDTO = new ProjectDTO(project.getName(), project.getDescription(), usersDTO);
        return projectDTO;
    }

    public Project convertFromProjectDTO(ProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);
    
        return project;
    }
}
