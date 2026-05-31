package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<User> findAllUsers();

    /**
     * Retrieves all users whose email address contains the given fragment, case-insensitive.
     *
     * @param emailPart the fragment of the email address to search for
     * @return a list of matching users (may be empty)
     */
    List<User> findUsersByEmailContaining(String emailPart);

    /**
     * Retrieves all users older than the given date, i.e. whose birthdate is before it.
     *
     * @param time the threshold date
     * @return a list of matching users (may be empty)
     */
    List<User> findUsersOlderThan(LocalDate time);

}
