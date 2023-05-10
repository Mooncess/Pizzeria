package ru.mooncess.Pizzeria.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.mooncess.Pizzeria.controllers.DrinkController;
import ru.mooncess.Pizzeria.dto.drink.DrinkCreateDTO;
import ru.mooncess.Pizzeria.repositories.UserRepository;
import ru.mooncess.Pizzeria.services.BasketService;
import ru.mooncess.Pizzeria.services.DrinkService;

import java.util.Optional;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DrinkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BasketService basketService;

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/drink/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("allDrink", notNullValue()))
                .andExpect(model().attribute("isAdmin", notNullValue()))
                .andExpect(model().attribute("isAuthenticated", notNullValue()))
                .andExpect(content().string(notNullValue()));
    }



    @Test
    @WithMockUser
    public void testAddToBasketIsAuthenticated() throws Exception {
        // given
        UserDetails userDetails = User.builder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/drink/addToBasket/1452")
                .param("quantity", "2")
                .with(SecurityMockMvcRequestPostProcessors.user(userDetails)));

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/drink/list"));
    }
}
