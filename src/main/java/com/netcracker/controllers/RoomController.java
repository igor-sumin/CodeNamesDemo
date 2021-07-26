package com.netcracker.controllers;

import com.netcracker.dto.RoomDTO;
import com.netcracker.services.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("")
    public ResponseEntity<RoomDTO> createRoom() {
        return ResponseEntity.ok(roomService.createRoom());
    }

    // TODO: доделать
    @GetMapping("/random")
    public ResponseEntity<RoomDTO> getRandRoom() {
        RoomDTO roomDTO = roomService.findRandRoom();

        if (roomDTO == null) {
            throw new CodeNamesExceptions("no rooms have been created");
        }

        return ResponseEntity.ok(roomDTO);
    }

    @GetMapping("/{ref}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable String ref) {
        RoomDTO roomDTO = roomService.findRoom(ref);

        if (roomDTO == null) {
            throw new CodeNamesExceptions("not found room");
        }

        return ResponseEntity.ok(roomDTO);
    }

    @GetMapping("/qnt")
    public ResponseEntity<Integer> getAmountUsersRoom() {
        return ResponseEntity.ok(roomService.defAmountUsersRoom());
    }
}
