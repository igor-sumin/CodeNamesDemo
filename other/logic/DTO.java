public class UserDTO {
	private int USER_ID;
	private boolean captain;
	private String name;
};

public class RoomDTO {
	private int ROOM_ID;
	private List<Team> teams;
};

public class TeamDTO {
	private List<User> users;
	private String color;
};

loginRequestDTO { login, password }
loginResponseDTO { token }
RoomDTO { usersDTO, teamsDTO }

