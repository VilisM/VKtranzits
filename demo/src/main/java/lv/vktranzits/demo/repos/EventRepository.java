package lv.vktranzits.demo.repos;

import java.time.LocalDateTime;
import java.util.List;

import lv.vktranzits.demo.models.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public interface EventRepository extends CrudRepository<Event, Long> {
	@Query("from Event e where not(e.end < :from or e.start > :to)")
    List<Event> findBetween(@Param("from")
	@DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime start,
                            @Param("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime end);
}