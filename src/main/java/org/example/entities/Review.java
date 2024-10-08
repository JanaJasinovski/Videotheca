package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Integer id;
    private Integer filmId;
    private Integer userId;
    private String text;
    private int rating;
}
