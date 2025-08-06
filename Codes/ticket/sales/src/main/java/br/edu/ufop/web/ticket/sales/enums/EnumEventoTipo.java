package br.edu.ufop.web.ticket.sales.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum EnumEventoTipo {

    PALESTRA(1, "Palestra"),
    SHOW(2,  "Show"),
    TEATRO(3,  "Teatro");

    private Integer id ;
    private String descricao;

}
