package br.edu.ufop.web.ticket.sales.models;

import br.edu.ufop.web.ticket.sales.enums.EnumSalesTipo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_sales")

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private UUID user_id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventsModel event;
    private Date purchaseDate;
    private EnumSalesTipo purchaseStatus;
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
