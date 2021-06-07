package br.edu.uepb.manager.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProjectDTO {
    
    @JsonProperty("name")
    @NotEmpty
    private String name;

    @JsonProperty("description")
    @NotEmpty
    private String description;

    // private Set<UserDTO> users;
}
