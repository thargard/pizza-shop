package modsen.pizza.newservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @NotBlank(message = "Email shouldn't be empty!")
    private String email;
    @NotBlank(message = "Password shouldn't be empty!")
    private String password;
    @NotBlank(message = "Username shouldn't be empty!")
    private String username;
    private String role;
}
