# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table notificacion.dispositivos (
  id                        integer not null,
  registration_id           varchar(255),
  tipo_dispositivo          varchar(255),
  usuario_id                integer not null,
  constraint pk_dispositivos primary key (id))
;

create table notificacion.eventos (
  id                        integer not null,
  planificacion_id          varchar(255),
  tipo_evento_id            integer not null,
  descripcion               varchar(255),
  constraint pk_eventos primary key (id))
;

create table notificacion.evento_usuario (
  id                        integer not null,
  usuario_id                integer not null,
  tipo_evento_id            integer not null,
  constraint pk_evento_usuario primary key (id))
;

create table notificacion.notificaciones (
  id                        integer not null,
  dispositivo_id            integer not null,
  evento_id                 integer not null,
  notificado                boolean,
  constraint pk_notificaciones primary key (id))
;

create table notificacion.suscripciones (
  id                        bigint not null,
  usuario_id                integer not null,
  planificacion_id          varchar(255) not null,
  constraint pk_suscripciones primary key (id))
;

create table notificacion.tipo_eventos (
  id                        integer not null,
  descripcion               varchar(255),
  template                  varchar(255),
  constraint pk_tipo_eventos primary key (id))
;

create table notificacion.usuarios (
  id                        integer not null,
  mail                      varchar(255),
  constraint pk_usuarios primary key (id))
;

create sequence notificacion.dispositivos_seq;

create sequence evento_id_seq;

create sequence notificacion.evento_usuario_seq;

create sequence notificacion.notificaciones_seq;

create sequence notificacion.suscripciones_seq;

create sequence notificacion.tipo_eventos_seq;

create sequence notificacion.usuarios_seq;

alter table notificacion.dispositivos add constraint fk_notificacion.dispositivos_u_1 foreign key (usuario_id) references notificacion.usuarios (id);
create index ix_notificacion.dispositivos_u_1 on notificacion.dispositivos (usuario_id);
alter table notificacion.eventos add constraint fk_notificacion.eventos_tipoEv_2 foreign key (tipo_evento_id) references notificacion.tipo_eventos (id);
create index ix_notificacion.eventos_tipoEv_2 on notificacion.eventos (tipo_evento_id);
alter table notificacion.evento_usuario add constraint fk_notificacion.evento_usuario_3 foreign key (usuario_id) references notificacion.usuarios (id);
create index ix_notificacion.evento_usuario_3 on notificacion.evento_usuario (usuario_id);
alter table notificacion.evento_usuario add constraint fk_notificacion.evento_usuario_4 foreign key (tipo_evento_id) references notificacion.tipo_eventos (id);
create index ix_notificacion.evento_usuario_4 on notificacion.evento_usuario (tipo_evento_id);
alter table notificacion.notificaciones add constraint fk_notificacion.notificaciones_5 foreign key (dispositivo_id) references notificacion.dispositivos (id);
create index ix_notificacion.notificaciones_5 on notificacion.notificaciones (dispositivo_id);
alter table notificacion.notificaciones add constraint fk_notificacion.notificaciones_6 foreign key (evento_id) references notificacion.eventos (id);
create index ix_notificacion.notificaciones_6 on notificacion.notificaciones (evento_id);
alter table notificacion.suscripciones add constraint fk_notificacion.suscripciones__7 foreign key (usuario_id) references notificacion.usuarios (id);
create index ix_notificacion.suscripciones__7 on notificacion.suscripciones (usuario_id);



# --- !Downs

drop table if exists notificacion.dispositivos cascade;

drop table if exists notificacion.eventos cascade;

drop table if exists notificacion.evento_usuario cascade;

drop table if exists notificacion.notificaciones cascade;

drop table if exists notificacion.suscripciones cascade;

drop table if exists notificacion.tipo_eventos cascade;

drop table if exists notificacion.usuarios cascade;

drop sequence if exists notificacion.dispositivos_seq;

drop sequence if exists evento_id_seq;

drop sequence if exists notificacion.evento_usuario_seq;

drop sequence if exists notificacion.notificaciones_seq;

drop sequence if exists notificacion.suscripciones_seq;

drop sequence if exists notificacion.tipo_eventos_seq;

drop sequence if exists notificacion.usuarios_seq;

