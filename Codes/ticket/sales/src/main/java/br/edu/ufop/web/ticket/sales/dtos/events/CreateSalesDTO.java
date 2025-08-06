package br.edu.ufop.web.ticket.sales.dtos.events;

import br.edu.ufop.web.ticket.sales.enums.EnumSalesTipo;
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
public class CreateSalesDTO {
    private UUID id;
    private UUID user_id;
    private UUID event_id;
    private Date purchaseDate;
    private EnumSalesTipo purchaseStatus;
}
