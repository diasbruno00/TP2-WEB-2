package br.edu.ufop.web.ticket.sales.service;

import br.edu.ufop.web.ticket.sales.converter.EventsConverter;
import br.edu.ufop.web.ticket.sales.domain.EventsDomain;
import br.edu.ufop.web.ticket.sales.dtos.events.CreateEventsDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.DeleteEventsDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.UpdateEventsDTO;
import br.edu.ufop.web.ticket.sales.models.EventsModel;
import br.edu.ufop.web.ticket.sales.repositories.IEventsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EventsService {

    private final IEventsRepository eventsRepository;


    public CreateEventsDTO saveEvent(CreateEventsDTO createEventsDTO) {

        try {
            System.out.println("dados" + createEventsDTO);

            EventsDomain eventsDomain = EventsConverter.toEventsDomain(createEventsDTO);

            EventsModel eventsModel = EventsConverter.toEventsModel(eventsDomain);

            return EventsConverter.toCreateEventsDTO(eventsRepository.save(eventsModel));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar o evento: " + e.getMessage());
        }


    }

    public List<CreateEventsDTO> getAllEvents() {

        List<EventsModel> eventsModelsList = eventsRepository.findAll();

        return eventsModelsList
                .stream()
                .map(EventsConverter::toCreateEventsDTO)
                .toList();
    }

    public void deteleEvents(DeleteEventsDTO deleteEventsDTO) {

        Optional<EventsModel> eventsModelOptional = eventsRepository.findById(deleteEventsDTO.getId());

        if (eventsModelOptional.isEmpty()) {
            throw new RuntimeException("Evento não encontrado com o ID: " + deleteEventsDTO.getId());
        }

        eventsRepository.delete(eventsModelOptional.get());
    }

    public CreateEventsDTO getEventById(String id) {

        UUID idConverter = UUID.fromString(id);

        Optional<EventsModel> eventsModelOptional = eventsRepository.findById(idConverter);

        if (eventsModelOptional.isEmpty()) {
            throw new RuntimeException("Evento não encontrado com o ID: " + id);
        }

        return EventsConverter.toCreateEventsDTO(eventsModelOptional.get());


    }

    public UpdateEventsDTO updateEvent(UpdateEventsDTO updateEventsDTO) {

        EventsDomain eventsDomain = EventsConverter.toEventsDomain(updateEventsDTO);

        Optional<EventsModel> optionalEventsModel = eventsRepository.findById(updateEventsDTO.getId());

        if (optionalEventsModel.isEmpty()) {
            throw new RuntimeException("Evento não encontrado com o ID: " + updateEventsDTO.getId());
        }

        EventsModel updatedEventsModel = EventsConverter.toEventsModel(eventsDomain);

        return EventsConverter.ToupdateEvents(eventsRepository.save(updatedEventsModel));



    }
}

