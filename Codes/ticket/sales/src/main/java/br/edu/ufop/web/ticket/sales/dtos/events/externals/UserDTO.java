package br.edu.ufop.web.ticket.sales.dtos.events.externals;


import java.util.UUID;

public record UserDTO(
        UUID uuid,
        String name,
        String email
) {
}
