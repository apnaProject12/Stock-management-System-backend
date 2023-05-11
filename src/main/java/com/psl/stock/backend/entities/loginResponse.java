package com.psl.stock.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class loginResponse {
    private String message;
    private String user;
    private String role;
    private String token;

}
