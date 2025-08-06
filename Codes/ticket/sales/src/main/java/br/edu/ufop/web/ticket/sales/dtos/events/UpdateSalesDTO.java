package br.edu.ufop.web.ticket.sales.dtos.events;

import br.edu.ufop.web.ticket.sales.enums.EnumSalesTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateSalesDTO {

    private UUID uuid;
    private UUID user_id;
    private UUID event_id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date purchaseDate;
    private EnumSalesTipo purchaseStatus;

}
