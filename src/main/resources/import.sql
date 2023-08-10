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



insert into authorities (authority, id_user) values('ROLE_ADMIN', '1');
insert into authorities (authority, id_user) values('ROLE_USER', '1');
insert into authorities (authority, id_user) values('ROLE_USER', '2');