package pl.wsb.fitnesstracker.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.startDate > :now ORDER BY e.startDate")
    List<Event> findUpcoming(@Param("now") LocalDate now);

    @Query("SELECT new pl.wsb.fitnesstracker.event.EventParticipantsDto(e.name, COUNT(ue.id)) " +
            "FROM Event e LEFT JOIN UserEvent ue ON ue.event = e " +
            "GROUP BY e.id, e.name " +
            "ORDER BY e.name")
    List<EventParticipantsDto> findEventNamesWithParticipantCount();
}
