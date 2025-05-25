package pl.wsb.fitnesstracker.user.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.IntegrationTest;
import pl.wsb.fitnesstracker.IntegrationTestBase;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
class UserApiIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    public static User generateUser() {
        return new User(randomUUID().toString(), randomUUID().toString(), LocalDate.now(), randomUUID().toString());
    }

    private static User generateUserWithDate(LocalDate date) {
        return new User(randomUUID().toString(), randomUUID().toString(), date, randomUUID().toString());
    }

    @Test
    void shouldReturnAllUsers_whenGettingAllUsers() throws Exception {
        User user1 = existingUser(generateUser());
        User user2 = existingUser(generateUser());

        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$[0].birthdate").value(ISO_DATE.format(user1.getBirthdate())))

                .andExpect(jsonPath("$[1].firstName").value(user2.getFirstName()))
                .andExpect(jsonPath("$[1].lastName").value(user2.getLastName()))
                .andExpect(jsonPath("$[1].birthdate").value(ISO_DATE.format(user2.getBirthdate())))

                .andExpect(jsonPath("$[2]").doesNotExist());
    }

    @Test
    void shouldReturnAllSimpleUsers_whenGettingAllUsers() throws Exception {
        User user1 = existingUser(generateUser());
        User user2 = existingUser(generateUser());

        mockMvc.perform(get("/v1/users/simple").contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(user1.getLastName()))

                .andExpect(jsonPath("$[1].firstName").value(user2.getFirstName()))
                .andExpect(jsonPath("$[1].lastName").value(user2.getLastName()))

                .andExpect(jsonPath("$[2]").doesNotExist());
    }

    @Test
    void shouldReturnDetailsAboutUser_whenGettingUserById() throws Exception {
        User user1 = existingUser(generateUser());

        mockMvc.perform(get("/v1/users/{id}", user1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$.birthdate").value(ISO_DATE.format(user1.getBirthdate())))
                .andExpect(jsonPath("$.email").value(user1.getEmail()));

    }

    @Test
    void shouldReturnDetailsAboutUser_whenGettingUserByEmail() throws Exception {
        User user1 = existingUser(generateUser());

        mockMvc.perform(get("/v1/users/email").param("email", user1.getEmail()).contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(user1.getId().intValue()))
                .andExpect(jsonPath("$[0].email").value(user1.getEmail()));
    }

    @Test
    void shouldReturnAllUsersOlderThan_whenGettingAllUsersOlderThan() throws Exception {
        User user1 = existingUser(generateUserWithDate(LocalDate.of(2000, 8, 11)));
        existingUser(generateUserWithDate(LocalDate.of(2024, 8, 11)));


        mockMvc.perform(get("/v1/users/older/{time}", LocalDate.of(2024, 8, 10)).contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$[0].birthdate").value(ISO_DATE.format(user1.getBirthdate())))

                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void shouldRemoveUserFromRepository_whenDeletingClient() throws Exception {
        User user1 = existingUser(generateUser());


        mockMvc.perform(delete("/v1/users/{userId}", user1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isNoContent());

        List<User> allUser = getAllUsers();
        assertThat(allUser).isEmpty();

    }

    @Test
    void shouldPersistUser_whenCreatingUser() throws Exception {

        final String USER_NAME = "Mike";
        final String USER_LAST_NAME = "Scott";
        final String USER_BIRTHDATE = "1999-09-29";
        final String USER_EMAIL = "mike.scott@domain.com";

        String creationRequest = """
                
                {
                "firstName": "%s",
                "lastName": "%s",
                "birthdate": "%s",
                "email": "%s"
                }
                """.formatted(
                USER_NAME,

                USER_LAST_NAME,
                USER_BIRTHDATE,
                USER_EMAIL);

        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(creationRequest))
                .andDo(log())
                .andExpect(status().isCreated());

        List<User> allUsers = getAllUsers();
        User user = allUsers.get(0);

        assertThat(user.getFirstName()).isEqualTo(USER_NAME);
        assertThat(user.getLastName()).isEqualTo(USER_LAST_NAME);
        assertThat(user.getBirthdate()).isEqualTo(LocalDate.parse(USER_BIRTHDATE));
        assertThat(user.getEmail()).isEqualTo(USER_EMAIL);

    }

    @Test
    void shouldUpdateUser_whenUpdatingUser() throws Exception {
        User user1 = existingUser(generateUser());

        final String USER_NAME = "Mike";
        final String USER_LAST_NAME = "Scott";
        final String USER_BIRTHDATE = "1999-09-29";
        final String USER_EMAIL = "mike.scott@domain.com";

        String updateRequest = """
                
                {
                "firstName": "%s",
                "lastName": "%s",
                "birthdate": "%s",
                "email": "%s"
                }
                """.formatted(
                USER_NAME,

                USER_LAST_NAME,
                USER_BIRTHDATE,
                USER_EMAIL);

        mockMvc.perform(put("/v1/users/{userId}", user1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateRequest));

        List<User> allUsers = getAllUsers();
        User user = allUsers.get(0);

        assertThat(user.getFirstName()).isEqualTo(USER_NAME);
        assertThat(user.getLastName()).isEqualTo(USER_LAST_NAME);
        assertThat(user.getBirthdate()).isEqualTo(LocalDate.parse(USER_BIRTHDATE));
        assertThat(user.getEmail()).isEqualTo(USER_EMAIL);
    }

//    @Test
//    void testDeleteUser_ShouldReturnDeletedUser() throws Exception {
//        Long userId = 1L;
//
//        User deletedUser = new User(userId, "John", "Doe", "john@example.com", 30);
//        UserDto deletedUserDto = new UserDto(userId, "John", "Doe", "john@example.com", 30);
//
//        when(userService.deleteUserById(userId)).thenReturn(deletedUser);
//        when(userMapper.toDto(deletedUser)).thenReturn(deletedUserDto);
//
//        mockMvc.perform(delete("/delete/{userId}", userId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(userId))
//                .andExpect(jsonPath("$.firstName").value("John"))
//                .andExpect(jsonPath("$.email").value("john@example.com"));
//
//        verify(userService).deleteUserById(userId);
//    }
//
//    @Test
//    void testFindUsersByEmailFragment_ShouldReturnMatchingUsers() throws Exception {
//        String fragment = "example";
//
//        User user1 = new User(1L, "Alice", "Smith", "alice@example.com", 25);
//        User user2 = new User(2L, "Bob", "Brown", "bob@example.com", 30);
//
//        UserEmailDto dto1 = new UserEmailDto(1L, "alice@example.com");
//        UserEmailDto dto2 = new UserEmailDto(2L, "bob@example.com");
//
//        List<User> users = List.of(user1, user2);
//        List<UserEmailDto> expectedDtos = List.of(dto1, dto2);
//
//        when(userService.findUsersByEmailFragment(fragment)).thenReturn(users);
//        when(userMapper.toEmailDto(user1)).thenReturn(dto1);
//        when(userMapper.toEmailDto(user2)).thenReturn(dto2);
//
//        mockMvc.perform(get("/email/fragment").param("fragment", fragment))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].id").value(1L))
//                .andExpect(jsonPath("$[0].email").value("alice@example.com"))
//                .andExpect(jsonPath("$[1].id").value(2L))
//                .andExpect(jsonPath("$[1].email").value("bob@example.com"));
//
//        verify(userService).findUsersByEmailFragment(fragment);
//    }
//
//    @Test
//    void testFindUsersOlderThan_ShouldReturnUserList() throws Exception {
//        int ageThreshold = 30;
//        User mockUser = new User(1L, "John", "Doe", "john@example.com", 35);
//        UserDto mockDto = new UserDto(1L, "John", "Doe", "john@example.com", 35);
//
//        when(userService.findAllUsersOlderThan(ageThreshold)).thenReturn(List.of(mockUser));
//        when(userMapper.toDto(mockUser)).thenReturn(mockDto);
//
//        mockMvc.perform(get("/olderThan/{ageThreshold}", ageThreshold))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].id").value(1L))
//                .andExpect(jsonPath("$[0].firstName").value("John"))
//                .andExpect(jsonPath("$[0].email").value("john@example.com"));
//
//        verify(userService).findAllUsersOlderThan(ageThreshold);
//    }
//
//    @Test
//    void testUpdateUserAttribute_ShouldReturnUpdatedUser() throws Exception {
//        Long userId = 1L;
//        String attribute = "firstName";
//        String newValue = "Jane";
//
//        User updatedUser = new User(userId, "Jane", "Doe", "jane@example.com", 30);
//        UserDto updatedDto = new UserDto(userId, "Jane", "Doe", "jane@example.com", 30);
//
//        when(userService.updateUserAttribute(userId, attribute, newValue)).thenReturn(updatedUser);
//        when(userMapper.toDto(updatedUser)).thenReturn(updatedDto);
//
//        mockMvc.perform(patch("/" + userId)
//                        .param("attribute", attribute)
//                        .param("value", newValue))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(userId))
//                .andExpect(jsonPath("$.firstName").value("Jane"))
//                .andExpect(jsonPath("$.email").value("jane@example.com"));
//
//        verify(userService).updateUserAttribute(userId, attribute, newValue);
//    }
//
//    @Test
//    void testUpdateUserAttribute_ShouldReturnBadRequest_WhenUserNotFound() throws Exception {
//        Long userId = 99L;
//        String attribute = "email";
//        String value = "missing@example.com";
//
//        when(userService.updateUserAttribute(userId, attribute, value))
//                .thenThrow(new UserNotFoundException("User not found"));
//
//        mockMvc.perform(patch("/" + userId)
//                        .param("attribute", attribute)
//                        .param("value", value))
//                .andExpect(status().isBadRequest())
//                .andExpect(status().reason("User not found"));
//
//        verify(userService).updateUserAttribute(userId, attribute, value);
//    }
//
//    @Test
//    void testUpdateUserAttribute_ShouldReturnBadRequest_WhenAttributeIsInvalid() throws Exception {
//        Long userId = 1L;
//        String attribute = "invalidField";
//        String value = "value";
//
//        when(userService.updateUserAttribute(userId, attribute, value))
//                .thenThrow(new IllegalArgumentException("Invalid attribute"));
//
//        mockMvc.perform(patch("/" + userId)
//                        .param("attribute", attribute)
//                        .param("value", value))
//                .andExpect(status().isBadRequest())
//                .andExpect(status().reason("Invalid attribute"));
//
//        verify(userService).updateUserAttribute(userId, attribute, value);
//    }

}
