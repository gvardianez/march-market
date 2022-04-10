package ru.geekbrains.march.market.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.dtos.ProfileDto;
import ru.geekbrains.march.market.entities.User;
import ru.geekbrains.march.market.services.UserService;
import ru.geekbrains.march.market.utils.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {

    private final UserService userService;
//    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping()
    public ProfileDto showProfile(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", principal.getName())));
        return new ProfileDto(user.getUsername(), user.getEmail());
    }

// не думаю, что это хороший вариант, записывать профильные данные в токен, а затем при необходимости их извлекать, но здесь мы не делаем запрос в бд, хотя актуальность таких данных под вопросом
//    @GetMapping()
//    public ProfileDto showProfile(Principal principal, HttpServletRequest request) {
//        String authHeader = request.getHeader("Authorization");
//        String email = null;
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String jwt = authHeader.substring(7);
//            try {
//                email = jwtTokenUtil.getEmailFromToken(jwt);
//            } catch (ExpiredJwtException e) {
//                log.debug("The token is expired");
//            }
//        }
//       return new ProfileDto(principal.getName(),email);
//    }

}
