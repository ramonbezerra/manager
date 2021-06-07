package br.edu.uepb.manager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private Long registration;

    private String name;

    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Role role;

    @Column(name = "function")
    private Function function;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=true)
    private Project project;
}
