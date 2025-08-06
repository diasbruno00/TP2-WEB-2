package br.edu.ufop.web.ticket.sales.controllers;

import br.edu.ufop.web.ticket.sales.dtos.events.*;
import br.edu.ufop.web.ticket.sales.dtos.events.externals.UserDTO;
import br.edu.ufop.web.ticket.sales.service.SalesService;
import br.edu.ufop.web.ticket.sales.service.clients.UserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/sales")
public class SalesConverter {

    private final SalesService salesService;
    private final UserServiceClient userServiceClient;


    /**
     * Endpoint para buscar todos os usuários.
     *
     * @return ResponseEntity com a lista de usuários ou um status 204 No Content se não houver usuários.
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            System.out.println("Buscando todos os usuários...");
            List<UserDTO> usersList = userServiceClient.getAllUsers();


            if (usersList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(usersList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Endpoint para salvar uma nova venda.
     * Recebe um objeto CreateSalesDTO no corpo da requisição.
     *
     * @param createSalesDTO Objeto contendo os dados da venda a ser criada.
     * @return ResponseEntity com o objeto CreateSalesDTO criado ou um status 400 Bad Request se houver erro.
     */
    @PostMapping
    public ResponseEntity<CreateSalesDTO> saveSales(@RequestBody CreateSalesDTO createSalesDTO) {

        CreateSalesDTO salesDTO = salesService.saveSales(createSalesDTO);
        if (salesDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(salesDTO);


    }

    /**
     * Endpoint para buscar todas as vendas com seus eventos e usuários associados.
     *
     * @return ResponseEntity com a lista de vendas e usuários ou um status 204 No Content se não houver vendas.
     */
    @GetMapping
    public ResponseEntity<List<VendaComDadosUsuarioDTO>> getAllSales() {
        try {
            // Busca a lista de vendas com seus eventos
            List<SalesDetailDTO> salesList = salesService.getAllSalesWithEvents();

            if (salesList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            // Coleta todos os userIds distintos das vendas
            List<UUID> userIds = salesList.stream()
                    .map(SalesDetailDTO::getUserId)
                    .distinct()
                    .toList();

            // Busca todos os usuários de uma vez
            List<SimplesUserDTO> users = userServiceClient.getAllUsersByListId(userIds);

            // Mapeia userId para usuário para acesso rápido O(1)
            Map<UUID, SimplesUserDTO> userMap = users.stream()
                    .collect(Collectors.toMap(SimplesUserDTO::getId, user -> user));

            // Monta a lista de resposta combinando os dados
            List<VendaComDadosUsuarioDTO> response = salesList.stream()
                    .map(sale -> {
                        SimplesUserDTO user = userMap.get(sale.getUserId());
                        // Aqui você criaria um novo DTO de resposta.
                        // Assumindo que SaleWithUserDTO tem um construtor que aceita a venda e o usuário.
                        return new VendaComDadosUsuarioDTO(user, sale);
                    })
                    .toList();

            // Agora a verificação faz sentido
            if (response.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // É uma boa prática logar o erro em vez de apenas imprimir no console
            // log.error("Erro ao buscar vendas: {}", e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    /**
     * Endpoint para buscar uma venda específica pelo ID.
     *
     * @param salesId ID da venda a ser buscada.
     * @return ResponseEntity com os detalhes da venda ou um status 404 Not Found se a venda não existir.
     */
    @GetMapping("/{salesId}")
    public ResponseEntity<SalesDetailDTO> getSalesById(@PathVariable UUID salesId) {

        if (salesId == null || salesId.toString().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        SalesDetailDTO salesDetailDTO = salesService.getAllSalesWithEventsById(salesId);

        if (salesDetailDTO == null) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(salesDetailDTO);
    }

    /**
     * Endpoint para buscar uma venda específica pelo ID.
     *
     * @param //salesId ID da venda a ser buscada.
     * @return ResponseEntity com os detalhes da venda ou um status 404 Not Found se a venda não existir.
     */
    @DeleteMapping
    public ResponseEntity<Object> deleteSalesById(@RequestBody DeleteSalesDTO deleteSalesDTO) {

        if (deleteSalesDTO == null || deleteSalesDTO.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        salesService.deleteSalesById(deleteSalesDTO);

        return ResponseEntity.ok("Sales deleted successfully");
    }

    /**
     * Endpoint para atualizar uma venda.
     * Recebe um objeto UpdateSalesDTO no corpo da requisição.
     *
     * @param updateSalesDTO Objeto contendo os dados da venda a ser atualizada.
     * @return ResponseEntity com o objeto UpdateSalesDTO atualizado ou um status 404 Not Found se a venda não existir.
     */
    @PutMapping
    public ResponseEntity<UpdateSalesDTO> updateSales(@RequestBody UpdateSalesDTO updateSalesDTO) {

        try {

            System.out.println("Atualizando venda com os dados: " + updateSalesDTO);
            if (updateSalesDTO == null || updateSalesDTO.getUuid() == null) {
                return ResponseEntity.badRequest().build();
            }
            UpdateSalesDTO updatedSales = salesService.updateSales(updateSalesDTO);

            if (updatedSales == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(updatedSales);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();

        }

    }

}
