package com.scm.forms;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank
    @Size(min=3,message="Min 3 Character is Required")
    private String name;
    @Email(message="Invalid Email Address")
    @NotBlank(message="Email is Required")
    private String email;

    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    @Size(min =8,message="Min 8 Character is Required")
    private String password;

    @Size(min=50,message="Min 50 Character is Required")
    private String about;
    @Size(min=10,max=10, message="Invalid Phone Number")
    private String phoneNumber;

   
}
