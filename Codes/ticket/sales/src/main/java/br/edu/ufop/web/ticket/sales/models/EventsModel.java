package br.edu.ufop.web.ticket.sales.models;

import br.edu.ufop.web.ticket.sales.enums.EnumEventoTipo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "tb_events")

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id ;
    private String description;
    private EnumEventoTipo type;
    private Date date;
    private Date startSales;
    private Date endSales;
    private Float price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @PrePersist
    public void antesGravar() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void antesAtualizar(){
        this.updatedAt = LocalDateTime.now();
    }

}
