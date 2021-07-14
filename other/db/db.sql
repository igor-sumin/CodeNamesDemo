create table room (
	room_id serial primary key,
	uniq_ref varchar unique not null,
	amount integer not null
);

create table team (
      team_id serial primary key,
      team_name varchar(50) not null,
      qnt_points integer not null,
      created_on timestamp not null
);

create table users (
	users_id serial primary key,
	user_login varchar(50) not null,
	user_name varchar(50) not null,
	user_password varchar(50) not null,
    user_email varchar(50) not null,
    captain boolean,
	team integer,
	room integer,

	foreign key (room)
		references room (room_id),
	foreign key (team)
		references team (team_id)
);

create table messages (
	messages_id serial primary key,
	user_text varchar,
	user_id integer not null,
	wired integer not null,
	created_on timestamp not null,
	
	foreign key (user_id)
		references users (users_id)
);

create table entry (
    user_id serial primary key,
    user_token varchar not null
);

insert into room(uniq_ref, amount)
    values('localhost:8080/sass1', 10);

insert into team(team_name, qnt_points, created_on)
    values('Red', 0, '2021-06-29 10:17:48.223');

insert into users(user_name, user_password, created_on, user_email)
    values('Igor', 123, '2021-06-29 10:17:48.223', 'igor@mail.ru');
