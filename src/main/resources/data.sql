create sequence links_id_seq start 1 increment 1;
create sequence node_id_seq start 1 increment 1;
create sequence unit_UNIT_ID_seq start 1 increment 1;

create table LINKS ( link_id int4 not null, SOURCE_NODE_ID int4, SOURCE_POINT_INDEX int4, SOURCE_UNIT_INDEX int4, DEST_NODE_ID int4, DEST_POINT_INDEX int4, DEST_UNIT_INDEX int4, PRIMARY key (link_id) );
create table NODE ( NODE_ID int4 not null, name varchar(255) not null, houseNumber varchar(255), region varchar(255), street varchar(255), PRIMARY key (NODE_ID) );
create table UNIT ( UNIT_ID int4 not null, capacity int4 not null, name varchar(255) not null, UNIT_INDEX int4 not null, NODE_ID int4 not null, PRIMARY key (UNIT_ID) );
create table UNIT_POINTS ( id  serial not null, POINT_INDEX int4 not null, UNIT_ID int4 not null, PRIMARY key (id) );
alter table UNIT ADD constraint FKb6sq7ai8owny604kixc77hen3 foreign key (NODE_ID) references NODE;
alter table UNIT_POINTS ADD constraint FKj2o3jdubh5d32cbuke7jom1k5 foreign key (UNIT_ID) references UNIT;

INSERT INTO node (node_id, name, region, street, housenumber) VALUES (nextval('node_id_seq'), 'Baldur', 'Kopenhagen', 'Sankt Peders Straade', '56');
INSERT INTO node (node_id, name, region, street, housenumber) VALUES (nextval('node_id_seq'), 'Bragi', 'Kopenhagen', 'Fensmarkgade', '34');
INSERT INTO node (node_id, name, region, street, housenumber) VALUES (nextval('node_id_seq'), 'Kvasir', 'Kopenhagen', 'Adelgade', '34');
INSERT INTO node (node_id, name, region, street, housenumber) VALUES (nextval('node_id_seq'), 'Loki', 'Kopenhagen', 'Borgergade', '38');
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_unit_id_seq'), 5, 'cisco', 1, 1);