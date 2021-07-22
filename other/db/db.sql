create table rooms (
                       room_id serial primary key,
                       room_ref varchar unique not null,
                       room_name varchar not null
);

create table teams (
                       team_id serial primary key,
                       room_id bigint not null,
                       team_name varchar not null,
                       qnt_points integer not null,
                       foreign key (room_id) references rooms (room_id)
);

create table users (
                       user_id serial primary key,
                       user_login varchar(50) not null,
                       user_name varchar(50) not null,
                       user_password varchar(50) not null,
                       user_email varchar(50) not null
);

create table user_tokens (
                         user_id serial primary key,
                         user_token varchar not null,
                         foreign key (user_id) references users (user_id)
);

create table messages (
                          messages_id serial primary key,
                          user_id bigint not null,
                          room_id bigint not null,
                          user_text varchar not null,
                          wired integer,
                          created_on timestamp not null,
                          foreign key (user_id) references users (user_id),
                          foreign key (room_id) references rooms (room_id)
);

create table user_team_rels (
                           id serial primary key,
                           user_id bigint not null,
                           team_id bigint not null,
                           is_captain boolean not null,
                           foreign key (user_id) references users (user_id),
                           foreign key (team_id) references teams (team_id)
);

-- drop table users, messages, rooms, teams, user_team_rels, user_tokens cascade;
-- truncate table users, messages, rooms, teams, user_team_rels, user_tokens cascade;

-- select * from user_tokens;
-- select * from users
-- select * from teams;
-- select * from rooms;
-- select * from user_team_rels;

-- delete from teams where team_id = 508

-- {
--     "roomRef": "99ECFACF",
--     "isCaptain": false,
--     "teamName": "Red"
-- }