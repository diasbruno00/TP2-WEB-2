package br.edu.ufop.web.ticket.sales.dtos.events;


import br.edu.ufop.web.ticket.sales.enums.EnumSalesTipo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalesDetailDTO {

    private UUID saleId;
    private UUID userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date purchaseDate;
    private EnumSalesTipo purchaseStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")

    private LocalDateTime saleDate;

    // Dados do Evento (Events)
    private UUID eventId;
    private String eventDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")

    private Date eventDate;
    private Float eventPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")

    private Date startSales;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")

    private Date endSales;


}
