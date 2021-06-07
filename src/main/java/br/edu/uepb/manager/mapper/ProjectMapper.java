package br.edu.uepb.manager.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.uepb.manager.domain.Project;
import br.edu.uepb.manager.dto.ProjectDTO;

public class ProjectMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProjectDTO convertToProjectDTO(Project project) {
        ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);

        return projectDTO;
    }

    public Project convertFromProjectDTO(ProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);
    
        return project;
    }
}
