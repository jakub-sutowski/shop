package com.example.shop.shop.model.request;

import com.example.shop.shop.type.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotEmpty
    @JsonProperty("first_name")
    @Schema(description = "Name", example = "Jan")
    private String firstName;

    @NotEmpty
    @JsonProperty("last_name")
    @Schema(description = "Surname", example = "Nowak")
    private String lastName;

    @NotEmpty
    @Email
    @JsonProperty("email")
    @Schema(description = "Email", example = "admin@admin.pl")
    private String email;

    @NotEmpty
    @JsonProperty("password")
    @Schema(description = "Password", example = "TwojeHaslo123")
    private String password;

    private Role role;
}