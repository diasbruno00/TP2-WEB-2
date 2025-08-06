package br.edu.ufop.web.ticket.sales.dtos.events;


import br.edu.ufop.web.ticket.sales.enums.EnumEventoTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventsDTO {
    private UUID id;
    private String description;
    private EnumEventoTipo type;
    private Date date;
    private Date startSales;
    private Date endSales;
    private Float price;
}
