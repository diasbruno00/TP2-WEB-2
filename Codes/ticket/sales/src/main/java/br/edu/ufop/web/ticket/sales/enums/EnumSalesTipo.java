package br.edu.ufop.web.ticket.sales.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumSalesTipo {

    EMABERTO(1, "Em Aberto"),
    PAGO(2,  "Pago"),
    CANCELADO(3,  "Cancelado"),
    ESTORNADO(4,  "Estornado");

    private Integer id ;
    private String descricao;


}
