package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    @Query(
            value = "SELECT * FROM users WHERE email LIKE CONCAT('%@', :domain)",
            nativeQuery = true
    )
    List<User> findByEmailDomain(@Param("domain") String domain);

    /**
     * Searches users whose email address contains the given fragment, case-insensitive.
     *
     * @param emailPart the fragment of the email address to search for
     * @return a list of matching users (may be empty)
     */
    default List<User> findAllByEmailContainingIgnoreCase(String emailPart) {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(emailPart.toLowerCase()))
                .toList();
    }

    /**
     * Searches users older than the given date, i.e. whose birthdate is before it.
     *
     * @param time the threshold date
     * @return a list of matching users (may be empty)
     */
    default List<User> findAllOlderThan(LocalDate time) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(time))
                .toList();
    }

}
