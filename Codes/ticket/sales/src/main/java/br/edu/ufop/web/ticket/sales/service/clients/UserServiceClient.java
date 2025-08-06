package br.edu.ufop.web.ticket.sales.service.clients;

import br.edu.ufop.web.ticket.sales.dtos.events.SimplesUserDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.externals.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient("users-service")
public interface UserServiceClient  {

    @GetMapping("/users")
    List<UserDTO> getAllUsers();

    @GetMapping("/users/bylistid")
    List<SimplesUserDTO> getAllUsersByListId(@RequestParam("listId") List<UUID> listId);
}
