package br.edu.ufop.web.ticket.sales.dtos.events;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DeleteSalesDTO {
    private UUID id;
}
