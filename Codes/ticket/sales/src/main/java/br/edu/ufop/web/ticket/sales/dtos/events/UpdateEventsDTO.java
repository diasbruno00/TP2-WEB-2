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
public class UpdateEventsDTO {
    private UUID id;
    private String description;
    private EnumEventoTipo type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "America/Sao_Paulo")
    private Date date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "America/Sao_Paulo")
    private Date startSales;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "America/Sao_Paulo")
    private Date endSales;
    private Float price;
}
