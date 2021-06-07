package br.edu.uepb.manager.services.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uepb.manager.domain.Function;
import br.edu.uepb.manager.domain.Project;
import br.edu.uepb.manager.domain.Role;
import br.edu.uepb.manager.domain.User;
import br.edu.uepb.manager.exceptions.NotAccumulateProjectException;
import br.edu.uepb.manager.exceptions.NotAuthorizedRoleException;
import br.edu.uepb.manager.repository.ProjectRepository;
import br.edu.uepb.manager.services.user.UserService;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private UserService userService;

    public Project createProject(Project newProject, String usernameLogged) throws NotAuthorizedRoleException, NotAccumulateProjectException {
        User currentUser = userService.findUserByUsername(usernameLogged);
        if (currentUser.getRole() == Role.ALUMN) {
            throw new NotAuthorizedRoleException("Um aluno não pode criar um projeto!");
        } else if (currentUser.getFunction() == Function.COORDINATOR) {
            throw new NotAccumulateProjectException("Você já possui um projeto!");            
        }

        Project project = projectRepository.save(newProject);
        currentUser.setProject(project);
        currentUser.setFunction(Function.COORDINATOR);

        userService.saveUser(currentUser);

        return project;
    }

    public void linkUser() {

    }

    public List<Project> listAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.getOne(id);
    }
}
