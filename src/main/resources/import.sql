insert into users(id,user_name,password) values (1,'user','$2a$10$rjFU3hAIf1tw/KiVnAOQDeZmWXvmLT2B6TaFtXpFKk4PtU1b0NIeC');
insert into roles select 1,'BASIC';
insert into roles select 2,'ADMIN';
insert into users_roles select 1,1;
insert into users_roles select 1,2;