
alter table public.UNIT
  drop constraint FKb6sq7ai8owny604kixc77hen3;

alter table public.UNIT_POINTS
  drop constraint FKj2o3jdubh5d32cbuke7jom1k5;

drop table if exists public.LINKS cascade;

drop table if exists public.NODE cascade;

drop table if exists public.UNIT cascade;

drop table if exists public.UNIT_POINTS cascade;

drop sequence if exists public.links_id_seq;

drop sequence if exists public.node_id_seq;

drop sequence if exists public.unit_UNIT_ID_seq;
drop SEQUENCE unit_id_seq CASCADE ;
