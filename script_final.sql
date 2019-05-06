


drop index CONTEM_FK;

drop index AVIAO_PK;

drop table AVIAO;

drop index TEM_FK;

drop index POSSUI_FK;

drop index BAGAGENS_PK;

drop table BAGAGENS;

drop index COMPRA_FK;

drop index DISPOE_FK;

drop index BILHETE_PK;

drop table BILHETE;

drop index CLIENTE_PK;

drop table CLIENTE;

drop index COMPANHIA_PK;

drop table COMPANHIA;

drop index DESTINOS_PK;

drop table DESTINOS;

drop index PARTIDAS_PK;

drop table PARTIDAS;

drop index ATRIBUI_FK;

drop index PONTUACAO_PK;

drop table PONTUACAO;

drop index PONT_COMPANHIA2_FK;

drop index PONT_COMPANHIA_FK;

drop index PONT_COMPANHIA_PK;

drop table PONT_COMPANHIA;

drop index PONT_DESTINO2_FK;

drop index PONT_DESTINO_FK;

drop index PONT_DESTINO_PK;

drop table PONT_DESTINO;

drop index PONT_PARTIDA2_FK;

drop index PONT_PARTIDA_FK;

drop index PONT_PARTIDA_PK;

drop table PONT_PARTIDA;

drop index FAZ_FK;

drop index CHEGA_FK;

drop index PARTE_FK;

drop index VIAGENS_PK;

drop table VIAGENS;

/*==============================================================*/
/* Table: AVIAO                                                 */
/*==============================================================*/
create table AVIAO (
   ID_AVIAO             INT4                 not null,
   ID_COMPANHIA         INT4                 not null,
   NOME_AVIAO           VARCHAR(50)          not null,
   LUGAR                INT4                 not null,
   NUM_LUGARES          INT4                 not null,
   constraint PK_AVIAO primary key (ID_AVIAO)
);

/*==============================================================*/
/* Index: AVIAO_PK                                              */
/*==============================================================*/
create unique index AVIAO_PK on AVIAO (
ID_AVIAO
);

/*==============================================================*/
/* Index: CONTEM_FK                                             */
/*==============================================================*/
create  index CONTEM_FK on AVIAO (
ID_COMPANHIA
);

/*==============================================================*/
/* Table: BAGAGENS                                              */
/*==============================================================*/
create table BAGAGENS (
   ID_BAGAGENS          INT4                 not null,
   ID_CLIENTE           INT4                 not null,
   ID_VIAGENS           INT4                 null,
   PESO_BAGAGENS        INT4                 not null,
   constraint PK_BAGAGENS primary key (ID_BAGAGENS)
);

/*==============================================================*/
/* Index: BAGAGENS_PK                                           */
/*==============================================================*/
create unique index BAGAGENS_PK on BAGAGENS (
ID_BAGAGENS
);

/*==============================================================*/
/* Index: POSSUI_FK                                             */
/*==============================================================*/
create  index POSSUI_FK on BAGAGENS (
ID_CLIENTE
);

/*==============================================================*/
/* Index: TEM_FK                                                */
/*==============================================================*/
create  index TEM_FK on BAGAGENS (
ID_VIAGENS
);

/*==============================================================*/
/* Table: BILHETE                                               */
/*==============================================================*/
create table BILHETE (
   ID_BILHETE           INT4                 not null,
   ID_VIAGENS           INT4                 not null,
   ID_CLIENTE           INT4                 not null,
   PRECO_BILHETE        INT4                 not null,
   LUGAR                INT4                 not null,
   constraint PK_BILHETE primary key (ID_BILHETE)
);

/*==============================================================*/
/* Index: BILHETE_PK                                            */
/*==============================================================*/
create unique index BILHETE_PK on BILHETE (
ID_BILHETE
);

/*==============================================================*/
/* Index: DISPOE_FK                                             */
/*==============================================================*/
create  index DISPOE_FK on BILHETE (
ID_VIAGENS
);

/*==============================================================*/
/* Index: COMPRA_FK                                             */
/*==============================================================*/
create  index COMPRA_FK on BILHETE (
ID_CLIENTE
);

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE (
   ID_CLIENTE           INT4                 not null,
   NOME_CLIENTE         VARCHAR(50)          not null,
   PASS_CLIENTE         VARCHAR(50)          not null,
   EMAIL_CLIENTE        VARCHAR(50)          not null,
   CONTA                INT4                 null,
   constraint PK_CLIENTE primary key (ID_CLIENTE)
);

/*==============================================================*/
/* Index: CLIENTE_PK                                            */
/*==============================================================*/
create unique index CLIENTE_PK on CLIENTE (
ID_CLIENTE
);

/*==============================================================*/
/* Table: COMPANHIA                                             */
/*==============================================================*/
create table COMPANHIA (
   ID_COMPANHIA         INT4                 not null,
   NOME_COMPANHIA       VARCHAR(50)          not null,
   PONTUACAO_MEDIA      FLOAT8               not null,
   constraint PK_COMPANHIA primary key (ID_COMPANHIA)
);

/*==============================================================*/
/* Index: COMPANHIA_PK                                          */
/*==============================================================*/
create unique index COMPANHIA_PK on COMPANHIA (
ID_COMPANHIA
);

/*==============================================================*/
/* Table: DESTINOS                                              */
/*==============================================================*/
create table DESTINOS (
   ID_DESTINO           INT4                 not null,
   CIDADE_DESTINO       VARCHAR(50)          not null,
   PONTUACAO_MEDIA      FLOAT8               not null,
   constraint PK_DESTINOS primary key (ID_DESTINO)
);

/*==============================================================*/
/* Index: DESTINOS_PK                                           */
/*==============================================================*/
create unique index DESTINOS_PK on DESTINOS (
ID_DESTINO
);

/*==============================================================*/
/* Table: PARTIDAS                                              */
/*==============================================================*/
create table PARTIDAS (
   ID_PARTIDA           INT4                 not null,
   CIDADE_PARTIDA       VARCHAR(50)          not null,
   PONTUACAO_MEDIA      FLOAT8               not null,
   constraint PK_PARTIDAS primary key (ID_PARTIDA)
);

/*==============================================================*/
/* Index: PARTIDAS_PK                                           */
/*==============================================================*/
create unique index PARTIDAS_PK on PARTIDAS (
ID_PARTIDA
);

/*==============================================================*/
/* Table: PONTUACAO                                             */
/*==============================================================*/
create table PONTUACAO (
   ID_PONTUACAO         INT4                 not null,
   ID_CLIENTE           INT4                 not null,
   VALOR                INT4                 not null,
   constraint PK_PONTUACAO primary key (ID_PONTUACAO)
);

/*==============================================================*/
/* Index: PONTUACAO_PK                                          */
/*==============================================================*/
create unique index PONTUACAO_PK on PONTUACAO (
ID_PONTUACAO
);

/*==============================================================*/
/* Index: ATRIBUI_FK                                            */
/*==============================================================*/
create  index ATRIBUI_FK on PONTUACAO (
ID_CLIENTE
);

/*==============================================================*/
/* Table: PONT_COMPANHIA                                        */
/*==============================================================*/
create table PONT_COMPANHIA (
   ID_COMPANHIA         INT4                 not null,
   ID_PONTUACAO         INT4                 not null,
   constraint PK_PONT_COMPANHIA primary key (ID_COMPANHIA, ID_PONTUACAO)
);

/*==============================================================*/
/* Index: PONT_COMPANHIA_PK                                     */
/*==============================================================*/
create unique index PONT_COMPANHIA_PK on PONT_COMPANHIA (
ID_COMPANHIA,
ID_PONTUACAO
);

/*==============================================================*/
/* Index: PONT_COMPANHIA_FK                                     */
/*==============================================================*/
create  index PONT_COMPANHIA_FK on PONT_COMPANHIA (
ID_COMPANHIA
);

/*==============================================================*/
/* Index: PONT_COMPANHIA2_FK                                    */
/*==============================================================*/
create  index PONT_COMPANHIA2_FK on PONT_COMPANHIA (
ID_PONTUACAO
);

/*==============================================================*/
/* Table: PONT_DESTINO                                          */
/*==============================================================*/
create table PONT_DESTINO (
   ID_DESTINO           INT4                 not null,
   ID_PONTUACAO         INT4                 not null,
   constraint PK_PONT_DESTINO primary key (ID_DESTINO, ID_PONTUACAO)
);

/*==============================================================*/
/* Index: PONT_DESTINO_PK                                       */
/*==============================================================*/
create unique index PONT_DESTINO_PK on PONT_DESTINO (
ID_DESTINO,
ID_PONTUACAO
);

/*==============================================================*/
/* Index: PONT_DESTINO_FK                                       */
/*==============================================================*/
create  index PONT_DESTINO_FK on PONT_DESTINO (
ID_DESTINO
);

/*==============================================================*/
/* Index: PONT_DESTINO2_FK                                      */
/*==============================================================*/
create  index PONT_DESTINO2_FK on PONT_DESTINO (
ID_PONTUACAO
);

/*==============================================================*/
/* Table: PONT_PARTIDA                                          */
/*==============================================================*/
create table PONT_PARTIDA (
   ID_PARTIDA           INT4                 not null,
   ID_PONTUACAO         INT4                 not null,
   constraint PK_PONT_PARTIDA primary key (ID_PARTIDA, ID_PONTUACAO)
);

/*==============================================================*/
/* Index: PONT_PARTIDA_PK                                       */
/*==============================================================*/
create unique index PONT_PARTIDA_PK on PONT_PARTIDA (
ID_PARTIDA,
ID_PONTUACAO
);

/*==============================================================*/
/* Index: PONT_PARTIDA_FK                                       */
/*==============================================================*/
create  index PONT_PARTIDA_FK on PONT_PARTIDA (
ID_PARTIDA
);

/*==============================================================*/
/* Index: PONT_PARTIDA2_FK                                      */
/*==============================================================*/
create  index PONT_PARTIDA2_FK on PONT_PARTIDA (
ID_PONTUACAO
);

/*==============================================================*/
/* Table: VIAGENS                                               */
/*==============================================================*/
create table VIAGENS (
   ID_VIAGENS           INT4                 not null,
   ID_PARTIDA           INT4                 not null,
   ID_DESTINO           INT4                 not null,
   ID_AVIAO             INT4                 not null,
   HORA_PARTIDA         INT4                 not null,
   HORA_CHEGADA         INT4                 not null,
   PRECO                INT4                 not null,
   ESTADO_VIAGEM        VARCHAR(50)          null,
   constraint PK_VIAGENS primary key (ID_VIAGENS)
);

/*==============================================================*/
/* Index: VIAGENS_PK                                            */
/*==============================================================*/
create unique index VIAGENS_PK on VIAGENS (
ID_VIAGENS
);

/*==============================================================*/
/* Index: PARTE_FK                                     */
/*==============================================================*/
create  index PARTE_FK on VIAGENS (
ID_PARTIDA
);

/*==============================================================*/
/* Index: CHEGA_FK                                     */
/*==============================================================*/
create  index CHEGA_FK on VIAGENS (
ID_DESTINO
);

/*==============================================================*/
/* Index: FAZ_FK                                                */
/*==============================================================*/
create  index FAZ_FK on VIAGENS (
ID_AVIAO
);

alter table AVIAO
   add constraint FK_AVIAO_CONTEM_COMPANHI foreign key (ID_COMPANHIA)
      references COMPANHIA (ID_COMPANHIA)
      on delete restrict on update restrict;

alter table BAGAGENS
   add constraint FK_BAGAGENS_POSSUI_CLIENTE foreign key (ID_CLIENTE)
      references CLIENTE (ID_CLIENTE)
      on delete restrict on update restrict;

alter table BAGAGENS
   add constraint FK_BAGAGENS_TEM_VIAGENS foreign key (ID_VIAGENS)
      references VIAGENS (ID_VIAGENS)
      on delete restrict on update restrict;

alter table BILHETE
   add constraint FK_BILHETE_COMPRA_CLIENTE foreign key (ID_CLIENTE)
      references CLIENTE (ID_CLIENTE)
      on delete restrict on update restrict;

alter table BILHETE
   add constraint FK_BILHETE_DISPOE_VIAGENS foreign key (ID_VIAGENS)
      references VIAGENS (ID_VIAGENS)
      on delete restrict on update restrict;

alter table PONTUACAO
   add constraint FK_PONTUACA_ATRIBUI_CLIENTE foreign key (ID_CLIENTE)
      references CLIENTE (ID_CLIENTE)
      on delete restrict on update restrict;

alter table PONT_COMPANHIA
   add constraint FK_PONT_COM_PONT_COMP_COMPANHI foreign key (ID_COMPANHIA)
      references COMPANHIA (ID_COMPANHIA)
      on delete restrict on update restrict;

alter table PONT_COMPANHIA
   add constraint FK_PONT_COM_PONT_COMP_PONTUACA foreign key (ID_PONTUACAO)
      references PONTUACAO (ID_PONTUACAO)
      on delete restrict on update restrict;

alter table PONT_DESTINO
   add constraint FK_PONT_DES_PONT_DEST_DESTINOS foreign key (ID_DESTINO)
      references DESTINOS (ID_DESTINO)
      on delete restrict on update restrict;

alter table PONT_DESTINO
   add constraint FK_PONT_DES_PONT_DEST_PONTUACA foreign key (ID_PONTUACAO)
      references PONTUACAO (ID_PONTUACAO)
      on delete restrict on update restrict;

alter table PONT_PARTIDA
   add constraint FK_PONT_PAR_PONT_PART_PARTIDAS foreign key (ID_PARTIDA)
      references PARTIDAS (ID_PARTIDA)
      on delete restrict on update restrict;

alter table PONT_PARTIDA
   add constraint FK_PONT_PAR_PONT_PART_PONTUACA foreign key (ID_PONTUACAO)
      references PONTUACAO (ID_PONTUACAO)
      on delete restrict on update restrict;

alter table VIAGENS
   add constraint FK_VIAGENS_CHEGA_DESTINOS foreign key (ID_DESTINO)
      references DESTINOS (ID_DESTINO)
      on delete restrict on update restrict;

alter table VIAGENS
   add constraint FK_VIAGENS_FAZ_AVIAO foreign key (ID_AVIAO)
      references AVIAO (ID_AVIAO)
      on delete restrict on update restrict;

alter table VIAGENS
   add constraint FK_VIAGENS_PARTE_PARTIDAS foreign key (ID_PARTIDA)
      references PARTIDAS (ID_PARTIDA)
      on delete restrict on update restrict;
      
create sequence aviao_id_seq;
alter table aviao alter id_operador set default nextval('aviao_id_seq');

create sequence bagagens_id_seq;
alter table bagagens alter id_bagagens set default nextval('bagagens_id_seq');

create sequence bilhete_id_seq;
alter table bilhete alter id_bilhete set default nextval('bilhete_id_seq');

create sequence cliente_id_seq;
alter table cliente alter id_cliente set default nextval('cliente_id_seq');

create sequence companhia_id_seq;
alter table companhia alter id_companhia set default nextval('companhia_id_seq');

create sequence destinos_id_seq;
alter table destinos alter id_destino set default nextval('destinos_id_seq');

create sequence partidas_id_seq;
alter table partidas alter id_partida set default nextval('partidas_id_seq');

create sequence pontuacao_id_seq;
alter table pontuacao alter id_pontuacao set default nextval('pontuacao_id_seq');

create sequence viagens_id_seq;
alter table viagens alter id_viagens set default nextval('viagens_id_seq');

create sequence if not exists seq_logs;
CREATE TABLE logs (id_log  INTEGER PRIMARY KEY DEFAULT nextval('seq_logs'), info_log varchar(250) NOT NULL, data_log timestamp NOT NULL);
create sequence if not exists operador_id_seq;
CREATE TABLE operador (id_operador  INTEGER  PRIMARY KEY DEFAULT nextval('operador_id_seq'), nome_operador varchar(50) NOT NULL, email_operador varchar(1024) NOT NULL, pass_operador varchar(50) NOT NULL);






