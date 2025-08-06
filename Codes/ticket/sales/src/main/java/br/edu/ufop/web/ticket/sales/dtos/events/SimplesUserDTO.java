package br.edu.ufop.web.ticket.sales.dtos.events;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SimplesUserDTO {
    private UUID id;
    private String name;
    private String email;


}

