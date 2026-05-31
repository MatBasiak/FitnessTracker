package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);

    /**
     * Deletes the user identified by the given id.
     *
     * @param userId the id of the user to be deleted
     */
    void deleteUser(Long userId);

    /**
     * Updates the user identified by the given id with the provided data.
     *
     * @param userId the id of the user to be updated
     * @param userData the user carrying the new attribute values
     * @return the updated user
     */
    User updateUser(Long userId, User userData);

}
