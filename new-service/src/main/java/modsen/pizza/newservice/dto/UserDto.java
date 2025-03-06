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

    public @NotBlank(message = "Email shouldn't be empty!") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email shouldn't be empty!") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password shouldn't be empty!") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password shouldn't be empty!") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Username shouldn't be empty!") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username shouldn't be empty!") String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
