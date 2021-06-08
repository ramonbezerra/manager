package br.edu.uepb.manager.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserLoginDTO {
    
    @JsonProperty("username")
    @NotEmpty
    private String username;
    
    @JsonProperty("password")
    @NotEmpty
    private String password;
}
