package br.edu.ufop.web.ticket.sales.repositories;

import br.edu.ufop.web.ticket.sales.models.EventsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IEventsRepository extends JpaRepository<EventsModel, UUID> {



}
