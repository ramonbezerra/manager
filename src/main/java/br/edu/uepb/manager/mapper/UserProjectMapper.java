package br.edu.uepb.manager.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.uepb.manager.domain.User;
import br.edu.uepb.manager.dto.UserProjectDTO;

public class UserProjectMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public UserProjectDTO convertToUserProjectDTO(User user) {
        UserProjectDTO userDTO = modelMapper.map(user, UserProjectDTO.class);

        return userDTO;
    }

    public User convertFromUserProjectDTO(UserProjectDTO userProjectDTO) {
        User user = modelMapper.map(userProjectDTO, User.class);
    
        return user;
    }
}
