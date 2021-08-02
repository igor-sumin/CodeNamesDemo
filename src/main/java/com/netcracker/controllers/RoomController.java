package com.netcracker.controllers;

import com.netcracker.dto.RequestContext;
import com.netcracker.dto.RoomDTO;
import com.netcracker.dto.entry.EntryResponseDTO;
import com.netcracker.services.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.netcracker.filters.EntryFilter.REQUEST_CONTEXT;

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

    @GetMapping("/random")
    public ResponseEntity<RoomDTO> getRandRoom(@RequestAttribute(REQUEST_CONTEXT) RequestContext requestContext) {
        return ResponseEntity.ok(roomService.findRandRoom(requestContext));
    }

    @GetMapping("/{ref}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable String ref) {
        return ResponseEntity.ok(roomService.findRoom(ref));
    }

    @PatchMapping("/{ref}")
    public ResponseEntity<EntryResponseDTO> assignRoomName(@PathVariable String ref, @RequestParam String name) {
        roomService.overrideRoomName(ref, name);
        return ResponseEntity.ok(EntryResponseDTO.builder()
                                                 .message("The name of the room has been rewritten.")
                                                 .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.findAllRooms());
    }

    @GetMapping("/list/user")
    public ResponseEntity<List<RoomDTO>> getAllRoomsForUser(@RequestAttribute(REQUEST_CONTEXT) RequestContext requestContext) {
        return ResponseEntity.ok(roomService.findAllRoomsForUser(requestContext));
    }

    @GetMapping("/amount")
    public ResponseEntity<Integer> getAmountRooms() {
        return ResponseEntity.ok(roomService.defAmountRooms());
    }

    @GetMapping("/amount/user")
    public ResponseEntity<Integer> getAmountRoomsForUser(@RequestAttribute(REQUEST_CONTEXT) RequestContext requestContext) {
        return ResponseEntity.ok(roomService.defAmountRoomsForUser(requestContext));
    }
}
