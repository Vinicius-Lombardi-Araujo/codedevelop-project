package com.vinicius.codedevelop_project_api.domain.dtos;

import com.vinicius.codedevelop_project_api.domain.entities.User;

public record LoginResponseDTO(String type, String token) {
}
