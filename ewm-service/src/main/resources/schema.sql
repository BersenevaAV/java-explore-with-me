drop table if exists ratings,compilations_of_events, compilations, requests, events, users, categories;

create table if not exists users (
  id bigint primary key generated by default as identity,
  name varchar(255) not null,
  email varchar(255) unique not null
);

create table if not exists categories (
  id bigint primary key generated by default as identity,
  name varchar(255) unique
);

create table if not exists events (
  id bigint primary key generated by default as identity,
  annotation varchar(2000) not null,
  category bigint references categories(id) not null,
  created_on timestamp without time zone,
  confirmed_requests int default 0,
  description varchar(7000) not null,
  event_date timestamp without time zone not null,
  initiator bigint references users(id) on delete cascade,
  lat real not null,
  lon real not null,
  paid bool,
  participant_limit int default 0,
  published_on timestamp without time zone,
  request_moderation bool,
  title varchar(120) not null,
  state varchar(50)
);

create table if not exists requests (
  id bigint primary key generated by default as identity,
  created timestamp without time zone,
  id_user bigint references users(id) on delete cascade,
  id_event bigint references events(id) on delete cascade,
  status varchar(50)
);

create table if not exists compilations (
  id bigint primary key generated by default as identity,
  pinned bool default false,
  title varchar(50)
);

create table if not exists compilations_of_events (
  id bigint primary key generated by default as identity,
  id_compilation bigint not null references compilations(id) on delete cascade ,
  id_event bigint references events(id) on delete cascade
);

create table if not exists ratings (
  id bigint primary key generated by default as identity,
  id_user bigint references users(id) on delete cascade,
  id_event bigint references events(id) on delete cascade,
  rating int
);