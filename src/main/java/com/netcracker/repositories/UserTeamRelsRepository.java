package com.netcracker.repositories;

import com.netcracker.entities.Room;
import com.netcracker.entities.Team;
import com.netcracker.entities.User;
import com.netcracker.entities.UserTeamRels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTeamRelsRepository extends JpaRepository<UserTeamRels, Long> {
    List<UserTeamRels> findAllByTeam(Team team);
    List<UserTeamRels> findAllByUser(User user);
    UserTeamRels findByUserAndTeam(User user, Team team);

    @Query(
            value = "select count(utr)>0 from user_team_rels utr where utr.team_id = :teamId and utr.is_captain = true",
            nativeQuery = true)
    Boolean existsByCaptain(Long teamId);

    @Query(
            value = "select distinct team_id from user_team_rels",
            nativeQuery = true)
    List<Long> findAllTeams();
}
