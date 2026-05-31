package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

/**
 * Maps between {@link User} entities and their data transfer objects (DTO).
 */
@Component
class UserMapper {

    /**
     * Maps a user entity to a full {@link UserDto}.
     *
     * @param user the user entity to map
     * @return the mapped {@link UserDto}
     */
    UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthdate(), user.getEmail());
    }

    /**
     * Maps a user entity to a {@link UserSimpleDto} containing only the ID and the name.
     *
     * @param user the user entity to map
     * @return the mapped {@link UserSimpleDto}
     */
    UserSimpleDto toUserSimpleDto(User user) {
        return new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    /**
     * Maps a user entity to a {@link UserEmailDto} containing only the ID and the email address.
     *
     * @param user the user entity to map
     * @return the mapped {@link UserEmailDto}
     */
    UserEmailDto toUserEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }

    /**
     * Maps a {@link UserDto} to a new {@link User} entity. The ID carried by the DTO is ignored.
     *
     * @param userDto the data transfer object to map
     * @return a new {@link User} entity built from the DTO
     */
    User toEntity(UserDto userDto) {
        return new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
    }
}
