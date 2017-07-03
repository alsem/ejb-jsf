--Импорт данных. Осуществляется движком хибернейта
create sequence links_id_seq start 1 increment 1;
create sequence node_id_seq start 1 increment 1;
create sequence unit_UNIT_ID_seq start 1 increment 1;

create table LINKS ( link_id int4 not null, SOURCE_NODE_ID int4, SOURCE_POINT_INDEX int4, SOURCE_UNIT_INDEX int4, DEST_NODE_ID int4, DEST_POINT_INDEX int4, DEST_UNIT_INDEX int4, PRIMARY key (link_id) );
create table NODE ( NODE_ID int4 not null, name varchar(255) not null, houseNumber varchar(255), region varchar(255), street varchar(255), PRIMARY key (NODE_ID) );
create table UNIT ( UNIT_ID int4 not null, capacity int4 not null, name varchar(255) not null, UNIT_INDEX int4 not null, NODE_ID int4 not null, PRIMARY key (UNIT_ID) );
create table UNIT_POINTS ( id  serial not null, POINT_INDEX int4 not null, UNIT_ID int4 not null, PRIMARY key (id) );
alter table UNIT ADD constraint FKb6sq7ai8owny604kixc77hen3 foreign key (NODE_ID) references NODE;
alter table UNIT_POINTS ADD constraint FKj2o3jdubh5d32cbuke7jom1k5 foreign key (UNIT_ID) references UNIT;

--nodes
INSERT INTO node (node_id, name, region, street, housenumber) VALUES (nextval('node_id_seq'), 'Baldur', 'Kopenhagen', 'Sankt Peders Straade', '56');
INSERT INTO node (node_id, name, region, street, housenumber) VALUES (nextval('node_id_seq'), 'Bragi', 'Kopenhagen', 'Fensmarkgade', '34');
INSERT INTO node (node_id, name, region, street, housenumber) VALUES (nextval('node_id_seq'), 'Kvasir', 'Kopenhagen', 'Adelgade', '34');
INSERT INTO node (node_id, name, region, street, housenumber) VALUES (nextval('node_id_seq'), 'Loki', 'Kopenhagen', 'Borgergade', '38');
INSERT INTO node (node_id, name, region, street, housenumber) VALUES (nextval('node_id_seq'), 'Freya', 'Kopenhagen', 'Nygårdsvej', '56');

--units
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 10, 'cisco10', 0, 4);
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 8, 'cisco8', 1, 4);
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 5, 'cisco5', 2, 4);
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 10, 'd-link010', 0, 3);
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 10, 'd-link110', 1, 3);
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 10, 'd-link210', 2, 3);
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 10, 'juniper010', 0, 2);
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 10, 'juniper110', 1, 2);
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 10, 'juniper210', 2, 2);
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 10, 'siemens10', 0, 1);
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 8, 'siemens8', 1, 1);
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 15, 'siemens15', 2, 1);
INSERT INTO unit (unit_id, capacity, name, unit_index, node_id) VALUES (nextval('unit_UNIT_ID_seq'), 20, 'cisco20', 1, 5);

--links
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 1, 1, 0, 2, 7, 2);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 1, 0, 2, 2, 0, 2);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 1, 3, 0, 2, 9, 2);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 1, 2, 0, 2, 8, 2);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 2, 6, 2, 3, 5, 1);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 2, 5, 2, 3, 4, 1);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 2, 7, 2, 1, 1, 0);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 2, 8, 2, 1, 2, 0);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 2, 9, 2, 1, 3, 0);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 2, 0, 2, 1, 0, 2);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 3, 1, 1, 4, 0, 0);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 3, 4, 1, 2, 5, 2);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 3, 2, 1, 4, 1, 0);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 3, 5, 1, 2, 6, 2);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 3, 3, 1, 4, 2, 0);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 4, 2, 0, 3, 3, 1);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 4, 1, 0, 3, 2, 1);
INSERT INTO links (link_id, source_node_id, source_point_index, source_unit_index, dest_node_id, dest_point_index, dest_unit_index) VALUES (nextval('links_id_seq'), 4, 0, 0, 3, 1, 1);