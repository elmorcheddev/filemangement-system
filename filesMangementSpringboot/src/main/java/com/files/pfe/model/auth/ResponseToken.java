package com.files.pfe.model.auth;

import com.files.pfe.model.Utilisateurs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseToken {
private String token;
private Utilisateurs utilisateur;
}
