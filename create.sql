create table tb_postagem (id int8 generated by default as identity, data timestamp, titulo varchar(100), txt varchar(400), tema_id int8, usuario_id int8, primary key (id));
create table tb_tema (id_tema int8 generated by default as identity, tema varchar(255), primary key (id_tema));
create table tb_usuario (id_usuario int8 generated by default as identity, email varchar(255), nome varchar(255), senha varchar(255), usuario varchar(255), primary key (id_usuario));
alter table if exists tb_postagem add constraint FKbs3gyvg64n0q2f3ce0trikhi3 foreign key (tema_id) references tb_tema;
alter table if exists tb_postagem add constraint FKr8awqpir4je3agsos18npscw7 foreign key (usuario_id) references tb_usuario;
