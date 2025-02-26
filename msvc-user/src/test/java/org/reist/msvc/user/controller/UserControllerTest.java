package org.reist.msvc.user.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
/*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    private User user;
    private Username username;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        username = new Username("testUser", "12345", new SessionLog());
        user = new User();
        user.setId(1L);
        user.setUsername( username );
        user.setName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        userDto = new UserDto(user);
    }

    @Test
    void testGETListUsers() throws Exception {
        when(userService.findAll()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is("testUser")));

        verify(userService, times(1)).findAll();
    }

    @Test
    void testUserDetail() throws Exception {
        when(userService.findById(1L)).thenReturn(Optional.of(user));

        String tokenTest = "Bearer tu_token_de_prueba";

        mockMvc.perform(get("/api/user/1")
                        .header(HttpHeaders.AUTHORIZATION, tokenTest)
                        .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nickname", is("testUser")));

        verify(userService, times(1)).findById(1L);
    }

    @Test
    void testUserByEmail() throws Exception {
        when(userService.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/user/email/john.doe@example.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("testUser")));

        verify(userService, times(1)).findByEmail("john.doe@example.com");
    }

    @Test
    void testUpdateUser() throws Exception {
        when(userService.findByUsername(username)).thenReturn(Optional.of(user));

        mockMvc.perform(put("/api/user/update-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testUser\",\"name\":\"Jane\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isAccepted());

        verify(userService, times(1)).findByUsername(username);
        verify(userService, times(1)).save(user);
    }

    @Test
    void testDeleteUser() throws Exception {
        when(userService.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(delete("/api/user/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(userService, times(1)).findById(1L);
        verify(userService, times(1)).delete(1L);
    }

    @Test
    void testUsersByIds() throws Exception {
        when(userService.findByIds(Arrays.asList(1L, 2L))).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/user/users-by-ids?ids=1,2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is("testUser")));

        verify(userService, times(1)).findByIds(Arrays.asList(1L, 2L));
    }
    */
}
