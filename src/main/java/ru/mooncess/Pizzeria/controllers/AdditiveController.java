package ru.mooncess.Pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.Pizzeria.dto.additive.AdditiveCreateDTO;
import ru.mooncess.Pizzeria.dto.additive.AdditiveDTO;
import ru.mooncess.Pizzeria.mappers.AdditiveMapper;
import ru.mooncess.Pizzeria.services.AdditiveService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/additive")
public class AdditiveController {
    private final AdditiveService service;
    private final AdditiveMapper mapper;

    @GetMapping(value = "/list")
    public String getAll(Authentication authentication, Model model) {
        List<AdditiveDTO> additiveList = service.findAll().stream().map(mapper::toDto).toList();
        model.addAttribute("additiveList", additiveList);

        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        boolean isAdmin = false;
        if (isAuthenticated) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            isAdmin = userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "admin/additives";
    }
}
