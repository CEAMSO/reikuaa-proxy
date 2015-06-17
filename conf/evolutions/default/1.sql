# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table notificacion.dispositivo (
  id                        bigserial not null not null,
  registration_id           varchar(255),
  tipo_dispositivo          varchar(255),
  usuario_id                bigserial not null not null,
  constraint pk_dispositivo primary key (id))
;

create table notificacion.evento (
  id                        bigserial not null not null,
  licitacion_planificacion_id character varying(255) not null,
  tipo_evento_id            bigserial not null not null,
  descripcion               varchar(255),
  notificado_android        boolean,
  notificacion_ios          boolean,
  constraint pk_evento primary key (id))
;

create table notificacion.evento_usuario (
  id                        bigserial not null not null,
  usuario_id                bigserial not null not null,
  grupo_evento_id           bigserial not null not null,
  constraint pk_evento_usuario primary key (id))
;

create table notificacion.grupo_evento (
  id                        bigserial not null not null,
  descripcion               varchar(255),
  constraint pk_grupo_evento primary key (id))
;

create table notificacion.notificacion_log (
  id                        bigserial not null not null,
  fecha_registro_id         timestamp without time zone not null,
  evento_id                 bigserial not null not null,
  error                     varchar(255),
  message_id                varchar(255),
  registration_id           varchar(255),
  constraint pk_notificacion_log primary key (id))
;

create table notificacion.suscripcion (
  id                        bigserial not null not null,
  usuario_id                bigserial not null not null,
  licitacion_planificacion_id character varying(255) not null,
  constraint pk_suscripcion primary key (id))
;

create table notificacion.tipo_evento (
  id                        bigserial not null not null,
  descripcion               varchar(255),
  nombre                    varchar(255),
  grupo_evento_id           bigserial not null,
  constraint pk_tipo_evento primary key (id))
;

create table notificacion.usuario (
  id                        bigserial not null not null,
  identificador             character varying(255) not null,
  constraint pk_usuario primary key (id))
;

create sequence notificacion.dispositivo_seq;

create sequence notificacion.evento_seq;

create sequence notificacion.evento_usuario_seq;

create sequence notificacion.grupo_evento_seq;

create sequence notificacion.notificacion_log_seq;

create sequence notificacion.suscripcion_seq;

create sequence notificacion.tipo_evento_seq;

create sequence notificacion.usuario_seq;

alter table notificacion.dispositivo add constraint fk_notificacion.dispositivo_us_1 foreign key (usuario_id) references notificacion.usuario (id);
create index ix_notificacion.dispositivo_us_1 on notificacion.dispositivo (usuario_id);
alter table notificacion.evento add constraint fk_notificacion.evento_tipoEve_2 foreign key (tipo_evento_id) references notificacion.tipo_evento (id);
create index ix_notificacion.evento_tipoEve_2 on notificacion.evento (tipo_evento_id);
alter table notificacion.evento_usuario add constraint fk_notificacion.evento_usuario_3 foreign key (usuario_id) references notificacion.usuario (id);
create index ix_notificacion.evento_usuario_3 on notificacion.evento_usuario (usuario_id);
alter table notificacion.evento_usuario add constraint fk_notificacion.evento_usuario_4 foreign key (grupo_evento_id) references notificacion.grupo_evento (id);
create index ix_notificacion.evento_usuario_4 on notificacion.evento_usuario (grupo_evento_id);
alter table notificacion.notificacion_log add constraint fk_notificacion.notificacion_l_5 foreign key (evento_id) references notificacion.evento (id);
create index ix_notificacion.notificacion_l_5 on notificacion.notificacion_log (evento_id);
alter table notificacion.suscripcion add constraint fk_notificacion.suscripcion_us_6 foreign key (usuario_id) references notificacion.usuario (id);
create index ix_notificacion.suscripcion_us_6 on notificacion.suscripcion (usuario_id);
alter table notificacion.tipo_evento add constraint fk_notificacion.tipo_evento_gr_7 foreign key (grupo_evento_id) references notificacion.grupo_evento (id);
create index ix_notificacion.tipo_evento_gr_7 on notificacion.tipo_evento (grupo_evento_id);



# --- !Downs

drop table if exists notificacion.dispositivo cascade;

drop table if exists notificacion.evento cascade;

drop table if exists notificacion.evento_usuario cascade;

drop table if exists notificacion.grupo_evento cascade;

drop table if exists notificacion.notificacion_log cascade;

drop table if exists notificacion.suscripcion cascade;

drop table if exists notificacion.tipo_evento cascade;

drop table if exists notificacion.usuario cascade;

drop sequence if exists notificacion.dispositivo_seq;

drop sequence if exists notificacion.evento_seq;

drop sequence if exists notificacion.evento_usuario_seq;

drop sequence if exists notificacion.grupo_evento_seq;

drop sequence if exists notificacion.notificacion_log_seq;

drop sequence if exists notificacion.suscripcion_seq;

drop sequence if exists notificacion.tipo_evento_seq;

drop sequence if exists notificacion.usuario_seq;

