package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for listing, retrieving, searching, creating, updating and deleting users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    private final UserProvider userProvider;

    private final UserMapper userMapper;

    /**
     * Returns the full details of all users.
     *
     * @return a list of {@link UserDto}
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Returns a lightweight list of all users containing only their id and name.
     *
     * @return a list of {@link UserSimpleDto}
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserSimpleDto)
                .toList();
    }

    /**
     * Returns the full details of the user with the given id.
     *
     * @param id the id of the user
     * @return the matching {@link UserDto}
     * @throws UserNotFoundException if no user with the given id exists
     */
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userProvider.getUser(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Searches users whose email address contains the given fragment, case-insensitive.
     * Only the id and email of each matching user are returned.
     *
     * @param email the fragment of the email address to search for
     * @return a list of {@link UserEmailDto}
     */
    @GetMapping("/email")
    public List<UserEmailDto> getUsersByEmail(@RequestParam String email) {
        return userProvider.findUsersByEmailContaining(email).stream()
                .map(userMapper::toUserEmailDto)
                .toList();
    }

    /**
     * Returns the full details of all users older than the given date.
     *
     * @param time the threshold date (ISO format yyyy-MM-dd)
     * @return a list of {@link UserDto}
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {
        return userProvider.findUsersOlderThan(time).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Creates a new user.
     *
     * @param userDto the user to create
     * @return the created {@link UserDto}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        final User created = userService.createUser(userMapper.toEntity(userDto));
        return userMapper.toUserDto(created);
    }

    /**
     * Updates the user with the given id.
     *
     * @param userId  the id of the user to update
     * @param userDto the new user data
     * @return the updated {@link UserDto}
     */
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        final User updated = userService.updateUser(userId, userMapper.toEntity(userDto));
        return userMapper.toUserDto(updated);
    }

    /**
     * Deletes the user with the given id.
     *
     * @param userId the id of the user to delete
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
