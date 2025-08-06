package br.edu.ufop.web.ticket.sales.dtos.events;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VendaComDadosUsuarioDTO {
    private SimplesUserDTO simplesUserDTO;
    private SalesDetailDTO salesDetailDTO;
}
