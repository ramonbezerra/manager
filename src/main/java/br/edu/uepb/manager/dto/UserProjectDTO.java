package br.edu.uepb.manager.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.uepb.manager.domain.Function;
import br.edu.uepb.manager.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserProjectDTO {

    private Long id;
    
    @JsonProperty("username")
    @NotEmpty
    private String username;
    
    @JsonProperty("role")
    private Role role;
    
    @JsonProperty("function")
    private Function function;

    @JsonProperty("registration")
    private Long registration;
}
