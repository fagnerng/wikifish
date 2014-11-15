# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  id                        bigint not null,
  fish_id                   bigint not null,
  user_name                 varchar(255),
  comment                   varchar(255),
  comment_likes             integer,
  constraint pk_comment primary key (id))
;

create table fish (
  id                        bigint not null,
  usual_name                varchar(255),
  cientific_name            varchar(255),
  ph                        float,
  dh                        float,
  temperature               float,
  maximum_length            float,
  aquarium_liters           float,
  dry_package_food          boolean,
  live_worms                boolean,
  live_fish                 boolean,
  vegetarian                boolean,
  peaceful                  boolean,
  agressive                 boolean,
  eggylayer                 boolean,
  livebearer                boolean,
  mothbrooder               boolean,
  bottom                    boolean,
  top                       boolean,
  middle                    boolean,
  no_special                boolean,
  densidly_plated           boolean,
  only_rocks                boolean,
  only_substract            boolean,
  complete_set              boolean,
  bright                    boolean,
  medium                    boolean,
  dark                      boolean,
  region_id                 bigint,
  constraint pk_fish primary key (id))
;

create table region (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_region primary key (id))
;

create sequence comment_seq;

create sequence fish_seq;

create sequence region_seq;

alter table comment add constraint fk_comment_fish_1 foreign key (fish_id) references fish (id) on delete restrict on update restrict;
create index ix_comment_fish_1 on comment (fish_id);
alter table fish add constraint fk_fish_region_2 foreign key (region_id) references region (id) on delete restrict on update restrict;
create index ix_fish_region_2 on fish (region_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists comment;

drop table if exists fish;

drop table if exists region;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists comment_seq;

drop sequence if exists fish_seq;

drop sequence if exists region_seq;

