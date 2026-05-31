package pl.wsb.fitnesstracker.user.internal;

/**
 * Lightweight data transfer object exposing only the identifier and the name of a user.
 *
 * @param id        the user identifier
 * @param firstName the user first name
 * @param lastName  the user last name
 */
record UserSimpleDto(Long id, String firstName, String lastName) {}
