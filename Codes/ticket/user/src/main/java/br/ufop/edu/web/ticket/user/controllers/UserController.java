package br.ufop.edu.web.ticket.user.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufop.edu.web.ticket.user.dtos.CreateUserDTO;
import br.ufop.edu.web.ticket.user.dtos.DeleteUserDTO;
import br.ufop.edu.web.ticket.user.dtos.SimpleUserRecordDTO;
import br.ufop.edu.web.ticket.user.dtos.UpdateUserCreditCardDTO;
import br.ufop.edu.web.ticket.user.dtos.UpdateUserDTO;
import br.ufop.edu.web.ticket.user.dtos.UpdateUserPasswordDTO;
import br.ufop.edu.web.ticket.user.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/bylistid")
    public ResponseEntity<List<SimpleUserRecordDTO>> getAllUsersByListId(@RequestParam List<UUID> listId) {
        List<SimpleUserRecordDTO> list = userService.getAllUsersByListId(listId);
        return ResponseEntity.ok(list);
    }
    @GetMapping
    public ResponseEntity<List<SimpleUserRecordDTO>> getAllUsers() {

        List<SimpleUserRecordDTO> list =
            userService.getAllUsers();
        return ResponseEntity.ok(list);

    }

    @PostMapping
    public ResponseEntity<SimpleUserRecordDTO> 
        createUser(@RequestBody 
            CreateUserDTO createUserDTO) {
        
        SimpleUserRecordDTO simpleUserRecordDTO = userService.createUser(createUserDTO);
        return ResponseEntity.ok(simpleUserRecordDTO);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<SimpleUserRecordDTO>
        getUserById(@PathVariable(value = "userId") String id) {

            SimpleUserRecordDTO simpleUserRecordDTO =
            userService.getUserById(id);

            if (simpleUserRecordDTO == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(simpleUserRecordDTO);

        }

    @GetMapping("/byname/{name}")
    public ResponseEntity<List<SimpleUserRecordDTO>> getByName(@PathVariable(value = "name") String userName) {

        List<SimpleUserRecordDTO> list =
            userService.getUsersByName(userName);
        return ResponseEntity.ok(list);

    }

    @PutMapping
    public ResponseEntity<SimpleUserRecordDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {

        SimpleUserRecordDTO simpleUserRecordDTO = userService.updateUser(updateUserDTO);

        if (simpleUserRecordDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(simpleUserRecordDTO);

    }

    @PutMapping("/password")
    public ResponseEntity<SimpleUserRecordDTO> updateUserPassword(@RequestBody  UpdateUserPasswordDTO updateUserPasswordDTO) {

        SimpleUserRecordDTO simpleUserRecordDTO = userService.updateUserPassword(updateUserPasswordDTO);

        if (simpleUserRecordDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(simpleUserRecordDTO);

    }

    @DeleteMapping("/remove")
    public ResponseEntity<Object> deleteUser(@RequestBody DeleteUserDTO deleteUserDTO) {
        
        userService.deleteUser(deleteUserDTO);
        return ResponseEntity.ok("User has been deleted.");

    }

    @PutMapping("/creditcard")
    public ResponseEntity<SimpleUserRecordDTO> updateCreditCard(
        @RequestBody UpdateUserCreditCardDTO updateUserCreditCardDTO
    ) {

        SimpleUserRecordDTO simpleUserRecordDTO = 
            userService.updateCreditCard(updateUserCreditCardDTO);

        if (simpleUserRecordDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(simpleUserRecordDTO);

    }



}
