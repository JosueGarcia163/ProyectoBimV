package com.losscrums.ProyectoHoteleria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponseDTO {
    private Long idHotel;
    private String name; 
    private String address;
    private Long numStars; 
    private String comfort; 
    private String profilePicture;
}
