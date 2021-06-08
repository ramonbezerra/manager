package br.edu.uepb.manager.dto;

import javax.validation.constraints.NotEmpty;

import br.edu.uepb.manager.domain.Function;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProjectUserDTO {
    
    @NotEmpty
    private Long projectId;
    
    @NotEmpty
    private String username;

    @NotEmpty
    private Function function;
}
