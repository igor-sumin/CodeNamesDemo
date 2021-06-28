package com.netcracker.controllers;

import com.netcracker.dto.RoomDTO;
import com.netcracker.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;

    // Получить данные о комнате
    @GetMapping("/room")
    public RoomDTO getRoom(@RequestParam int uniqId) {
        return roomService.getRoom(uniqId);
    }
}
