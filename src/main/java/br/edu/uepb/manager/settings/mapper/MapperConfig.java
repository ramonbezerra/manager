package br.edu.uepb.manager.settings.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.uepb.manager.mapper.ProjectMapper;
import br.edu.uepb.manager.mapper.UserMapper;
import br.edu.uepb.manager.mapper.UserProjectMapper;

@Configuration
public class MapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public UserProjectMapper userProjectMapper() {
        return new UserProjectMapper();
    }

    @Bean
    public ProjectMapper projectMapper() {
        return new ProjectMapper();
    }
}
