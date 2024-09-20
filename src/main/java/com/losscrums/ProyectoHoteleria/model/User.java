package com.losscrums.ProyectoHoteleria.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Automatically generates a constructor without arguments for the class being applied
@Entity //The Entity annotation is used to map the model from Hibernate
@AllArgsConstructor //Automatically generates a constructor that accepts the parameters of the class
@NoArgsConstructor //Generate constructs without arguments
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Column(unique =true)
    private String username;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Long nit;

    @NotBlank
    private String personalImage;
    
}
