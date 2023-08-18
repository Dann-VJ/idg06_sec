/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  DannVJ
 * Created: 7 jul. 2023
 */

insert into alumno (matricula, nombre) values('2020371104', 'Daniel Vazques Joaquin');
insert into alumno (matricula, nombre) values('2020371104', 'Daniel Vazques Joaquin');
insert into alumno (matricula, nombre) values('2020371104', 'Daniel Vazques Joaquin');

/**/
insert into users (nombre, apellidos, correo, password, enabled) values('Admin', 'Emi', 'emil@uteq.edu.mx', '$2a$10$u52dzlzA6apDFeJTQ7JnAO27f.sMdYkZvqbYyEZ4aqHtXUCWq5Hl6', 1);
insert into users (nombre, apellidos, correo, password, enabled) values('Daniel', 'Vazquez Joaquin', '2020371064@uteq.edu.mx', '$2a$10$PbQNYwaixF/7pMtJMzATauMzc37ewOvAI44uwWzvu7.AF0U1gNPUC', 1);
insert into users (nombre, apellidos, correo, password, enabled) values('Enrique', 'Martinez', 'enrique@uteq.edu.mx', '$2a$10$PbQNYwaixF/7pMtJMzATauMzc37ewOvAI44uwWzvu7.AF0U1gNPUC', 1);

insert into authorities (authority, id_user) values('ROLE_ADMIN', '1');
insert into authorities (authority, id_user) values('ROLE_USER', '1');
insert into authorities (authority, id_user) values('ROLE_USER', '2');
insert into authorities (authority, id_user) values('ROLE_USER', '3');

insert into post (texto, imagen, id_user, permiso, enabled) values('Publicacion del amin publica', 'uploads/post/39e58817-1e74-4a6c-aad2-02491d5e3547_1366_2000.jpeg', '1', 0, 1);
insert into post (texto, imagen, id_user, permiso, enabled) values('Publicacion privada del amin', 'uploads/post/fc43a8dc-509f-4bfb-be29-6ec975fbea0e_tree-736885_1280.jpg', '1', 1, 1);
insert into post (texto, imagen, id_user, permiso, enabled) values('Segunda publicacion publica del amin', 'uploads/post/fc43a8dc-509f-4bfb-be29-6ec975fbea0e_tree-736885_1280.jpg', '1', 0, 1);

insert into amigos (id_friend, id_myuser) values('2', '1');
insert into amigos (id_friend, id_myuser) values('1', '2');