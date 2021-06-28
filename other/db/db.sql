create table room (
	room_id serial primary key,
	uniq_ref varchar unique not null,
	amount integer not null
);

create table users (
	users_id serial primary key,
	username varchar(50) not null,
	userpassword varchar(50) not null,
	captain boolean not null,
	teams integer not null,
	created_on timestamp not null,
	room integer not null
	foreign key (room)
		references room (room_id),
	foreign key (teams)
		references team (team_id)
);

create table team (
	team_id serial primary key,
	teamname varchar(50) not null,
	qnt_points integer not null,
	created_on timestamp not null
);


create table messages (
	messages_id serial primary key,
	usertext varchar,
	user_id integer not null,
	wired integer not null,
	created_on timestamp not null,
	
	foreign key (user_id)
		references users (users_id)
);

select * from messages;
select * from room;
select * from users;
select * from team;
