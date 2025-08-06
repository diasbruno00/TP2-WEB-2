package br.edu.ufop.web.ticket.sales.repositories;


import br.edu.ufop.web.ticket.sales.models.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ISalesRepository extends JpaRepository<SalesModel, UUID> {

    // No seu repositório (SalesRepository)

    @Query("SELECT s FROM SalesModel s JOIN FETCH s.event")
    List<SalesModel> findAllSalesWithEvents();

    @Query("SELECT s FROM SalesModel s JOIN FETCH s.event WHERE s.id = :id")
    SalesModel findSalesById(UUID id);


}
