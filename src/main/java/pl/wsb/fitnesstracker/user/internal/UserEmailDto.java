package pl.wsb.fitnesstracker.user.internal;

/**
 * Data transfer object exposing only the identifier and the email address of a user.
 * Used as the result of an email lookup.
 *
 * @param id    the user identifier
 * @param email the user email address
 */
record UserEmailDto(Long id, String email) {}
