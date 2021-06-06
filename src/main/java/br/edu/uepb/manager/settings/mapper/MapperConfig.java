package br.edu.uepb.manager.settings.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.uepb.manager.mapper.UserMapper;

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
}
