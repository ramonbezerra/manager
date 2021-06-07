package br.edu.uepb.manager.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.uepb.manager.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
    
    @JsonProperty("username")
    @NotEmpty
    private String username;
    
    @JsonProperty("password")
    @NotEmpty
    private String password;
    
    @JsonProperty("role")
    private Role role;

    @JsonProperty("registration")
    private Long registration;
}
