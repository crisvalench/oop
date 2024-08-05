package com.ups.oop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class AnimalDTO {
    private String id;
    private String animalCode; //name-breed-color
    private String petName;
    private Double weight;
    private Double height;
    private Double length;

    public AnimalDTO(String animalCode, String petName, Double weight, Double height, Double length) {
        this.animalCode = animalCode;
        this.petName = petName;
        this.weight = weight;
        this.height = height;
        this.length = length;
    }
}
