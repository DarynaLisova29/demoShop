package com.example.demoShop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ім'я користувача не може бути порожнім")
    @Size(min = 3, max = 20, message = "Ім'я користувача повинно містити від 3 до 20 символів")
    private String username;

    @NotBlank(message = "Пароль не може бути порожнім")
    @Size(min = 6, message = "Пароль повинен містити принаймні 6 символів")
    private String password;

    @NotBlank(message = "Email не може бути порожнім")
    @Email(message = "Введіть дійсну електронну адресу")
    private String email;

    @Min(value = 18, message = "Вік повинен бути не менше 18 років")
    private Integer age;

    public User(String username, String email, Integer age, String password) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public @NotBlank(message = "Email не може бути порожнім") @Email(message = "Введіть дійсну електронну адресу") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email не може бути порожнім") @Email(message = "Введіть дійсну електронну адресу") String email) {
        this.email = email;
    }

    @Min(value = 18, message = "Вік повинен бути не менше 18 років")
    public Integer getAge() {
        return age;
    }

    public void setAge(@Min(value = 18, message = "Вік повинен бути не менше 18 років") Integer age) {
        this.age = age;
    }
}


