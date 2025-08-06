package br.edu.ufop.web.ticket.sales.service;

import br.edu.ufop.web.ticket.sales.converter.SalesConverter;
import br.edu.ufop.web.ticket.sales.domain.SalesDomain;
import br.edu.ufop.web.ticket.sales.dtos.events.CreateSalesDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.DeleteSalesDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.SalesDetailDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.UpdateSalesDTO;
import br.edu.ufop.web.ticket.sales.enums.EnumSalesTipo;
import br.edu.ufop.web.ticket.sales.models.SalesModel;
import br.edu.ufop.web.ticket.sales.repositories.ISalesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SalesService {

    private final ISalesRepository salesRepository;

    public CreateSalesDTO saveSales(CreateSalesDTO createSalesDTO) {

        SalesDomain salesDomain = SalesConverter.toSalesDomain(createSalesDTO);

        //salesDomain.setPurchaseStatus(EnumSalesTipo.EMABERTO);

        SalesModel salesModel = SalesConverter.toSalesModel(salesDomain);

        return SalesConverter.toCreateSalesDTO(salesRepository.save(salesModel));


    }

    public List<SalesDetailDTO> getAllSalesWithEvents() {
        try {
            System.out.println("Buscando todas as vendas com eventos...");
            List<SalesModel> salesModelsList = salesRepository.findAllSalesWithEvents();

            return salesModelsList
                    .stream()
                    .map(SalesConverter::convertToSaleDetailDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar vendas: " + e.getMessage());
        }

    }

    public SalesDetailDTO getAllSalesWithEventsById(UUID userId) {
        SalesModel salesModelsList = salesRepository.findSalesById(userId);

        if (salesModelsList == null) {
            return null;
        }
        return SalesConverter.convertToSaleDetailDto(salesModelsList);
    }


    public CreateSalesDTO getSalesById(String id) {

        UUID uuid = UUID.fromString(id);
        Optional<SalesModel> optionalSalesModel = salesRepository.findById(uuid);
        if (optionalSalesModel.isEmpty()) {
            return null;
        }
        SalesModel salesModel = optionalSalesModel.get();
        return SalesConverter.toCreateSalesDTO(salesModel);
    }

    public void deleteSalesById(DeleteSalesDTO deleteSalesDTO) {

        Optional<SalesModel> salesModelOptional = salesRepository.findById(deleteSalesDTO.getId());

        if (salesModelOptional.isEmpty()) {
            throw new RuntimeException("Venda não encontrada com o ID: " + deleteSalesDTO.getId());
        }

        salesRepository.delete(salesModelOptional.get());
    }

    public UpdateSalesDTO updateSales(UpdateSalesDTO updateSalesDTO) {

        Optional<SalesModel> salesModelOptional = salesRepository.findById(updateSalesDTO.getUuid());

        if (salesModelOptional.isEmpty()) {
            throw new RuntimeException("Venda não encontrada com o ID: " + updateSalesDTO.getUuid());
        }

        SalesModel salesModel = salesModelOptional.get();
        SalesDomain salesDomain = SalesConverter.toSalesDomain(updateSalesDTO);

        SalesModel updatedSalesModel = SalesConverter.toSalesModel(salesDomain);
        updatedSalesModel.setUuid(salesModel.getUuid()); // Manter o UUID original

        return SalesConverter.toUpdateSalesDTO(salesRepository.save(updatedSalesModel));


    }
}
