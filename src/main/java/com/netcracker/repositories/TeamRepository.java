package com.netcracker.repositories;

import com.netcracker.entities.Room;
import com.netcracker.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByTeamNameAndRoom(String teamName, Room room);
    Team findByRoom(Room room);
}
