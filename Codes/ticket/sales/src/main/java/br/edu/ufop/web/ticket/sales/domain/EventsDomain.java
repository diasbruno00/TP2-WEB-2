package br.edu.ufop.web.ticket.sales.domain;

import br.edu.ufop.web.ticket.sales.enums.EnumEventoTipo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventsDomain {

    private UUID id ;
    private String description;
    private EnumEventoTipo type;
    private Date date;
    private Date startSales;
    private Date endSales;
    private Float price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
