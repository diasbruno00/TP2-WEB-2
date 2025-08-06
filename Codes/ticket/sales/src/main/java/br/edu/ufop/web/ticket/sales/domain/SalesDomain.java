package br.edu.ufop.web.ticket.sales.domain;

import br.edu.ufop.web.ticket.sales.enums.EnumSalesTipo;
import br.edu.ufop.web.ticket.sales.models.EventsModel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesDomain {
    private UUID uuid;
    private UUID user_id;
    private EventsModel event;
    private Date purchaseDate;
    private EnumSalesTipo purchaseStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
