package com.ups.oop.dto;

import com.ups.oop.entity.Author;
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

public class BookDTO {
    private String title;
    private String editorial;
    private String author;
}
