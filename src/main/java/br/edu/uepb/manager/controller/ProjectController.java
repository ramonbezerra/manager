package br.edu.uepb.manager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uepb.manager.domain.Project;
import br.edu.uepb.manager.domain.User;
import br.edu.uepb.manager.dto.GenericResponseErrorDTO;
import br.edu.uepb.manager.dto.ProjectDTO;
import br.edu.uepb.manager.dto.ProjectUserDTO;
import br.edu.uepb.manager.exceptions.NotAccumulateProjectException;
import br.edu.uepb.manager.exceptions.NotAuthorizedRoleException;
import br.edu.uepb.manager.mapper.ProjectMapper;
import br.edu.uepb.manager.services.project.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/projects", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
@Api(value = "Project", tags = "Project")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectMapper projectMapper;

    @GetMapping
    @ApiOperation(value = "Lista todos os projetos")
    public List<ProjectDTO> getAll() {
        List<Project> projects = projectService.listAll();
        return projects.stream()
                        .map(projectMapper::convertToProjectDTO)
                        .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um projeto pelo seu identificador")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(projectMapper.convertToProjectDTO(projectService.findById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProjectDTO projectDTO, Authentication auth){
        try {
            Project newProject = projectMapper.convertFromProjectDTO(projectDTO);
            String usernameLogged = auth.getPrincipal().toString();
            return new ResponseEntity<>(projectService.createProject(newProject, usernameLogged), HttpStatus.CREATED);
        } catch (NotAuthorizedRoleException | NotAccumulateProjectException e) {
            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
        }
    }

    @PostMapping("/link")
    public ResponseEntity<?> linkUser(@RequestBody ProjectUserDTO userProjectDTO, Authentication auth){
        try {
            String usernameLogged = auth.getPrincipal().toString();
            projectService.linkUser(userProjectDTO.getProjectId(), userProjectDTO.getUsername(), userProjectDTO.getFunction(), usernameLogged);
            return ResponseEntity.noContent().build();
        } catch (NotAuthorizedRoleException | NotAccumulateProjectException e) {
            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
        }
    }

    @PostMapping("/unlink")
    public ResponseEntity<?> unlinkUser(@RequestBody ProjectUserDTO userProjectDTO, Authentication auth){
        try {
            String usernameLogged = auth.getPrincipal().toString();
            projectService.unlinkUser(userProjectDTO.getProjectId(), userProjectDTO.getUsername(), usernameLogged);
            return ResponseEntity.noContent().build();
        } catch (NotAuthorizedRoleException e) {
            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
        }
    }
}
