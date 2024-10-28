package com.example.demoShop.controller;

import com.example.demoShop.model.User;
import com.example.demoShop.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Відображення сторінки реєстрації
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());  // Додаємо пустий об'єкт User для форми
        return "register";
    }

    // Обробка реєстрації з валідацією
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        // Перевіряємо на помилки валідації
        if (result.hasErrors()) {
            return "register";  // Якщо є помилки, повертаємося до форми
        }

        // Перевіряємо, чи існує вже користувач з таким ім'ям
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Користувач вже існує");
            return "register";
        }

        // Реєструємо нового користувача
        userService.registerUser(user);
        return "redirect:/login";  // Після успішної реєстрації перенаправляємо на логін
    }

    // Відображення сторінки логіну
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        // Додаємо пустий атрибут для користувацької помилки, щоб уникнути NullPointerException
        if (!model.containsAttribute("usernameValue")) {
            model.addAttribute("usernameValue", "");
        }
        return "login";
    }

    // Обробка логіну
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        // Перевіряємо наявність користувача і правильність пароля
        if (userService.authenticate(username, password)) {
            session.setAttribute("user", username);  // Зберігаємо користувача в сесії
            return "redirect:/products";
        } else {
            model.addAttribute("error", "Невірне ім'я користувача або пароль");
            model.addAttribute("usernameValue", username);  // Додаємо значення імені користувача до моделі
            return "login";
        }
    }

    // Вихід з системи
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Завершуємо сесію
        return "redirect:/login";
    }
}