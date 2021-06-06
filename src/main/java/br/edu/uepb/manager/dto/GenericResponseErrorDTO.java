package br.edu.uepb.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponseErrorDTO {
    private String error;
}