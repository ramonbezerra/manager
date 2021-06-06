package br.edu.uepb.manager.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
    
    @JsonProperty("username")
    @NotBlank
    private String username;
    
    @JsonProperty("password")
    @NotBlank
    private String password;
}
