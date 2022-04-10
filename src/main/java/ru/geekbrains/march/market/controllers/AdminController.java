package ru.geekbrains.march.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.dtos.ProfileDto;
import ru.geekbrains.march.market.entities.User;
import ru.geekbrains.march.market.services.UserService;

@RestController
@RequestMapping("/api/v1/admin_panel")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/specific_profile/{userId}")
    public ProfileDto showSpecificProfile(@PathVariable Long userId) {
        User user = userService.findById(userId).orElseThrow(() -> new UsernameNotFoundException(String.format("User with id '%d' not found", userId)));
        return new ProfileDto(user.getUsername(), user.getEmail());
    }

}
