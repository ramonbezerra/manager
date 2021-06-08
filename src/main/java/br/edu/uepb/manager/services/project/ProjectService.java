package br.edu.uepb.manager.services.project;

import java.util.List;

import javax.transaction.Transactional;

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
        
        if (verifyIfUserLoggedIsNotAlumn(currentUser) && verifyIfUserLoggedNotAccumulateProjects(currentUser)) {    
            Project project = projectRepository.save(newProject);
            currentUser.setProject(project);
            currentUser.setFunction(Function.COORDINATOR);
            
            userService.saveUser(currentUser);
            
            return project;
        }

        return null;
    }

    public void linkUser(Long projectId, String username, Function function, String usernameLogged) throws NotAuthorizedRoleException, NotAccumulateProjectException {
        User currentUser = userService.findUserByUsername(usernameLogged);
        User candidateUser = userService.findUserByUsername(username);

        if (verifyIfUserLoggedIsNotAlumn(currentUser) && verifyIfUserLoggedNotAccumulateProjects(candidateUser)) {
            Project project = projectRepository.findById(projectId).get();
            linkOrUnlinkUserToProject(project, function, candidateUser);
        }
    }

    public void unlinkUser(Long projectId, String username, String usernameLogged) throws NotAuthorizedRoleException {
        User currentUser = userService.findUserByUsername(usernameLogged);
        User candidateUser = userService.findUserByUsername(username);
        
        if (verifyIfUserLoggedIsNotAlumn(currentUser)) {
            linkOrUnlinkUserToProject(null, null, candidateUser);
        }
    }

    private void linkOrUnlinkUserToProject(Project project, Function function, User candidateUser) {
        candidateUser.setProject(project);
        candidateUser.setFunction(function);
        
        userService.saveUser(candidateUser);
    }

    private boolean verifyIfUserLoggedIsNotAlumn(User currentUser) throws NotAuthorizedRoleException {
        if (currentUser.getRole() == Role.ALUMN) {
            throw new NotAuthorizedRoleException("Um aluno não pode criar ou se vincular a um projeto!");
        } 
        return true;
    }

    private boolean verifyIfUserLoggedNotAccumulateProjects(User currentUser) throws NotAccumulateProjectException {
        if (currentUser.getFunction() == Function.COORDINATOR || currentUser.getProject() != null) {
            throw new NotAccumulateProjectException("O usuário já possui um projeto!");            
        }
        return true;
    }
        
    public List<Project> listAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).get();
    }
}
