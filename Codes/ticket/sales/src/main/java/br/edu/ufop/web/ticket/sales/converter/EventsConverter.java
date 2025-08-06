package br.edu.ufop.web.ticket.sales.converter;

import br.edu.ufop.web.ticket.sales.domain.EventsDomain;
import br.edu.ufop.web.ticket.sales.dtos.events.CreateEventsDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.UpdateEventsDTO;
import br.edu.ufop.web.ticket.sales.enums.EnumEventoTipo;
import br.edu.ufop.web.ticket.sales.models.EventsModel;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

public class EventsConverter {

    public static EventsDomain toEventsDomain(CreateEventsDTO createEventsDTO){


        return EventsDomain.builder()
                .id(createEventsDTO.getId())
                .description(createEventsDTO.getDescription())
                .type(createEventsDTO.getType())
                .date(createEventsDTO.getDate())
                .startSales(createEventsDTO.getStartSales())
                .endSales(createEventsDTO.getEndSales())
                .price(createEventsDTO.getPrice())
                .build();

    }

    public static EventsModel toEventsModel(EventsDomain eventsDomain){

        return EventsModel.builder()
                .id(eventsDomain.getId())
                .description(eventsDomain.getDescription())
                .type(eventsDomain.getType())
                .date(eventsDomain.getDate())
                .startSales(eventsDomain.getStartSales())
                .endSales(eventsDomain.getEndSales())
                .price(eventsDomain.getPrice())
                .build();
    }


    public static CreateEventsDTO toCreateEventsDTO(EventsModel eventsModel) {


        return  new CreateEventsDTO(
                eventsModel.getId(),
                eventsModel.getDescription(),
                eventsModel.getType(),
                eventsModel.getDate(),
                eventsModel.getStartSales(),
                eventsModel.getEndSales(),
                eventsModel.getPrice()
        );
    }
    public static UpdateEventsDTO ToupdateEvents(EventsModel eventsModel) {



        return  new UpdateEventsDTO(
                eventsModel.getId(),
                eventsModel.getDescription(),
                eventsModel.getType(),
                eventsModel.getDate(),
                eventsModel.getStartSales(),
                eventsModel.getEndSales(),
                eventsModel.getPrice()
        );
    }

    public static EventsDomain toEventsDomain(UpdateEventsDTO updateEventsDTO) {


        return EventsDomain.builder()
                .id(updateEventsDTO.getId())
                .description(updateEventsDTO.getDescription())
                .type(updateEventsDTO.getType())
                .date(updateEventsDTO.getDate())
                .startSales(updateEventsDTO.getStartSales())
                .endSales(updateEventsDTO.getEndSales())
                .price(updateEventsDTO.getPrice())
                .build();
    }
}
