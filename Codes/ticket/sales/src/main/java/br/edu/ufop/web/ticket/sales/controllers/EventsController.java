package br.edu.ufop.web.ticket.sales.controllers;

import br.edu.ufop.web.ticket.sales.dtos.events.CreateEventsDTO;

import br.edu.ufop.web.ticket.sales.dtos.events.DeleteEventsDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.UpdateEventsDTO;
import br.edu.ufop.web.ticket.sales.service.EventsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/event")
public class EventsController {

    private final EventsService eventsService;

    /**
     * Endpoint para salvar um novo evento.
     * Recebe um objeto CreateEventsDTO no corpo da requisição.
     *
     * @param createEventsDTO Objeto contendo os dados do evento a ser criado.
     * @return ResponseEntity com o objeto CreateEventsDTO criado ou um status 400 Bad Request se houver erro.
     */
    @PostMapping
    public ResponseEntity<CreateEventsDTO> saveEvent(@RequestBody CreateEventsDTO createEventsDTO) {

        // Opção 1: Simples e direta (imprime a representação .toString() do objeto)
        System.out.println("====== DADOS RECEBIDOS NO BACKEND ======");
        System.out.println(createEventsDTO);
        System.out.println("========================================");

        CreateEventsDTO eventsDTO = eventsService.saveEvent(createEventsDTO);

        return ResponseEntity.ok(eventsDTO);

    }

    /**
     * Endpoint para buscar todos os eventos.
     *
     * @return ResponseEntity com a lista de eventos ou um status 204 No Content se não houver eventos.
     */
    @GetMapping
    public ResponseEntity<List<CreateEventsDTO>> getAllEvents() {

        List<CreateEventsDTO> eventsList = eventsService.getAllEvents();

        if (eventsList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(eventsList);
    }

    /**
     * Endpoint para deletar eventos.
     * Recebe um objeto DeleteEventsDTO no corpo da requisição.
     *
     * @param deleteEventsDTO Objeto contendo os IDs dos eventos a serem deletados.
     * @return ResponseEntity com uma mensagem de sucesso ou um status 400 Bad Request se houver erro.
     */
    @DeleteMapping
    public ResponseEntity<Object> deleteEvent(@RequestBody DeleteEventsDTO deleteEventsDTO) {

        eventsService.deteleEvents(deleteEventsDTO);

        return ResponseEntity.ok("Event deleted successfully");
    }

    /**
     * Endpoint para buscar um evento específico pelo ID.
     *
     * @param eventId ID do evento a ser buscado.
     * @return ResponseEntity com o objeto CreateEventsDTO correspondente ou um status 404 Not Found se o evento não existir.
     */
    @GetMapping("/{eventId}")
    public ResponseEntity<CreateEventsDTO> getEventById(@PathVariable String eventId) {

        if (Objects.isNull(eventId) || eventId.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        CreateEventsDTO eventsDTO = eventsService.getEventById(eventId);

        if (Objects.isNull(eventsDTO)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(eventsDTO);
    }

    /**
     * Endpoint para atualizar um evento.
     * Recebe um objeto UpdateEventsDTO no corpo da requisição.
     *
     * @param updateEventsDTO Objeto contendo os dados do evento a ser atualizado.
     * @return ResponseEntity com o objeto UpdateEventsDTO atualizado ou um status 404 Not Found se o evento não existir.
     */
    @PutMapping
    public ResponseEntity<UpdateEventsDTO> updateEvent(@RequestBody UpdateEventsDTO updateEventsDTO) {
        if (Objects.isNull(updateEventsDTO) || Objects.isNull(updateEventsDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }

        UpdateEventsDTO updatedEvent = eventsService.updateEvent(updateEventsDTO);

        if (Objects.isNull(updatedEvent)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedEvent);
    }
}
