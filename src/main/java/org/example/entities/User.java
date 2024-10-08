package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String fullName;
    private String password;
    private String email;

    public User(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
