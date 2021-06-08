package br.edu.uepb.manager.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {
    
    @JsonProperty("name")
    @NotEmpty
    private String name;

    @JsonProperty("description")
    @NotEmpty
    private String description;

    private List<UserProjectDTO> users;
}
