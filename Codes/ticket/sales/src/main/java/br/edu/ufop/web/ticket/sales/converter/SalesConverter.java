package br.edu.ufop.web.ticket.sales.converter;

import br.edu.ufop.web.ticket.sales.domain.SalesDomain;
import br.edu.ufop.web.ticket.sales.dtos.events.CreateSalesDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.SalesDetailDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.UpdateSalesDTO;
import br.edu.ufop.web.ticket.sales.models.EventsModel;
import br.edu.ufop.web.ticket.sales.models.SalesModel;

public class SalesConverter {

    public static SalesDetailDTO convertToSaleDetailDto(SalesModel sale) {

        EventsModel event = sale.getEvent();

        return new SalesDetailDTO(
                sale.getUuid(),
                sale.getUser_id(),
                sale.getPurchaseDate(),
                sale.getPurchaseStatus(),
                sale.getCreatedAt(),
                event.getId(), // id do evento
                event.getDescription(),
                event.getDate(),
                event.getPrice(),
                event.getStartSales(),
                event.getEndSales()
        );
    }


    public static SalesDomain toSalesDomain(CreateSalesDTO createSalesDTO){

        EventsModel eventsModel = new EventsModel();
        eventsModel.setId(createSalesDTO.getEvent_id());

        return  SalesDomain.builder()
                .user_id(createSalesDTO.getUser_id())
                .event(eventsModel)
                .purchaseDate(createSalesDTO.getPurchaseDate())
                .purchaseStatus(createSalesDTO.getPurchaseStatus())
                .build();
    }

    public static SalesModel toSalesModel(SalesDomain salesDomain) {
        return SalesModel.builder()
                .user_id(salesDomain.getUser_id())
                .event(salesDomain.getEvent())
                .purchaseDate(salesDomain.getPurchaseDate())
                .purchaseStatus(salesDomain.getPurchaseStatus())
                .build();
    }

    public static CreateSalesDTO toCreateSalesDTO(SalesModel salesModel) {
        return new CreateSalesDTO(
                salesModel.getUuid(),
                salesModel.getUser_id(),
                salesModel.getEvent().getId(),
                salesModel.getPurchaseDate(),
                salesModel.getPurchaseStatus()
        );
    }

    public static SalesDomain toSalesDomain(UpdateSalesDTO updateSalesDTO) {
        EventsModel eventsModel = new EventsModel();
        eventsModel.setId(updateSalesDTO.getEvent_id());

        return SalesDomain.builder()
                .uuid(updateSalesDTO.getUuid())
                .user_id(updateSalesDTO.getUser_id())
                .event(eventsModel)
                .purchaseDate(updateSalesDTO.getPurchaseDate())
                .purchaseStatus(updateSalesDTO.getPurchaseStatus())
                .build();
    }

    public static UpdateSalesDTO toUpdateSalesDTO(SalesModel salesModel) {
        return new UpdateSalesDTO(
                salesModel.getUuid(),
                salesModel.getUser_id(),
                salesModel.getEvent().getId(),
                salesModel.getPurchaseDate(),
                salesModel.getPurchaseStatus()
        );
    }
}
