/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     24/12/2018 12:10:22                          */
/*==============================================================*/


drop index AGENCIA_VIAGENS_PK;

drop table AGENCIA_VIAGENS;

drop index AGREGA2_FK;

drop index AGREGA_FK;

drop index AGREGA_PK;

drop table AGREGA;

drop index CONTEM_FK;

drop index AVIAO_PK;

drop table AVIAO;

drop index TERA_FK;

drop index POSSUI_FK;

drop index BAGAGENS_PK;

drop table BAGAGENS;

drop index COMPRA_FK;

drop index DISPOE_FK;

drop index BILHETE_PK;

drop table BILHETE;

drop index CLIENTE_PK;

drop table CLIENTE;

drop index TRABALHA_FK;

drop index COMPANHIA_PK;

drop table COMPANHIA;

drop index DESTINOS_PK;

drop table DESTINOS;

drop index GERE_FK;

drop index OPERADOR_PK;

drop table OPERADOR;

drop index PARTIDAS_PK;

drop table PARTIDAS;

drop index ATRIBUI_FK;

drop index PONTUACAO_PK;

drop table PONTUACAO;

drop index RELATIONSHIP_11_FK;

drop index RELATIONSHIP_10_FK;

drop index RELATIONSHIP_10_PK;

drop table RELATIONSHIP_10;

drop index TEM2_FK;

drop index TEM_FK;

drop index TEM_PK;

drop table TEM;

drop index FAZ_FK;

drop index RELATIONSHIP_2_FK;

drop index RELATIONSHIP_1_FK;

drop index VIAGENS_PK;

drop table VIAGENS;

/*==============================================================*/
/* Table: AGENCIA_VIAGENS                                       */
/*==============================================================*/
create table AGENCIA_VIAGENS (
   ID_AGENCIA           INT4                 not null,
   NOME_AGENCIA         VARCHAR(30)          not null,
   constraint PK_AGENCIA_VIAGENS primary key (ID_AGENCIA)
);

/*==============================================================*/
/* Index: AGENCIA_VIAGENS_PK                                    */
/*==============================================================*/
create unique index AGENCIA_VIAGENS_PK on AGENCIA_VIAGENS (
ID_AGENCIA
);

/*==============================================================*/
/* Table: AGREGA                                                */
/*==============================================================*/
create table AGREGA (
   ID_DESTINO           INT4                 not null,
   ID_PONTUACAO         INT4                 not null,
   constraint PK_AGREGA primary key (ID_DESTINO, ID_PONTUACAO)
);

/*==============================================================*/
/* Index: AGREGA_PK                                             */
/*==============================================================*/
create unique index AGREGA_PK on AGREGA (
ID_DESTINO,
ID_PONTUACAO
);

/*==============================================================*/
/* Index: AGREGA_FK                                             */
/*==============================================================*/
create  index AGREGA_FK on AGREGA (
ID_DESTINO
);

/*==============================================================*/
/* Index: AGREGA2_FK                                            */
/*==============================================================*/
create  index AGREGA2_FK on AGREGA (
ID_PONTUACAO
);

/*==============================================================*/
/* Table: AVIAO                                                 */
/*==============================================================*/
create table AVIAO (
   ID_AVIAO             INT4                 not null,
   ID_COMPANHIA         INT4                 not null,
   NOME_AVIAO           VARCHAR(50)          not null,
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
   ID_VIAGENS           INT4                 not null,
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
/* Index: TERA_FK                                               */
/*==============================================================*/
create  index TERA_FK on BAGAGENS (
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
   ID_AGENCIA           INT4                 not null,
   NOME_COMPANHIA       VARCHAR(50)          not null,
   PONTUACAO_MEDIA      INT4                 not null,
   constraint PK_COMPANHIA primary key (ID_COMPANHIA)
);

/*==============================================================*/
/* Index: COMPANHIA_PK                                          */
/*==============================================================*/
create unique index COMPANHIA_PK on COMPANHIA (
ID_COMPANHIA
);

/*==============================================================*/
/* Index: TRABALHA_FK                                           */
/*==============================================================*/
create  index TRABALHA_FK on COMPANHIA (
ID_AGENCIA
);

/*==============================================================*/
/* Table: DESTINOS                                              */
/*==============================================================*/
create table DESTINOS (
   ID_DESTINO           INT4                 not null,
   CIDADE_DESTINO       VARCHAR(50)          not null,
   PONTUACAO_MEDIA      INT4                 not null,
   constraint PK_DESTINOS primary key (ID_DESTINO)
);

/*==============================================================*/
/* Index: DESTINOS_PK                                           */
/*==============================================================*/
create unique index DESTINOS_PK on DESTINOS (
ID_DESTINO
);

/*==============================================================*/
/* Table: OPERADOR                                              */
/*==============================================================*/
create table OPERADOR (
   ID_OPERADOR          INT4                 not null,
   ID_AGENCIA           INT4                 not null,
   NOME_OPERADOR        VARCHAR(50)          not null,
   EMAIL_OPERADOR       VARCHAR(1024)        not null,
   PASS_OPERADOR        VARCHAR(50)          not null,
   constraint PK_OPERADOR primary key (ID_OPERADOR)
);

/*==============================================================*/
/* Index: OPERADOR_PK                                           */
/*==============================================================*/
create unique index OPERADOR_PK on OPERADOR (
ID_OPERADOR
);

/*==============================================================*/
/* Index: GERE_FK                                               */
/*==============================================================*/
create  index GERE_FK on OPERADOR (
ID_AGENCIA
);

/*==============================================================*/
/* Table: PARTIDAS                                              */
/*==============================================================*/
create table PARTIDAS (
   ID_PARTIDA           INT4                 not null,
   CIDADE_PARTIDA       VARCHAR(50)          not null,
   PONTUACAO_MEDIA      INT4                 not null,
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
/* Table: RELATIONSHIP_10                                       */
/*==============================================================*/
create table RELATIONSHIP_10 (
   ID_COMPANHIA         INT4                 not null,
   ID_PONTUACAO         INT4                 not null,
   constraint PK_RELATIONSHIP_10 primary key (ID_COMPANHIA, ID_PONTUACAO)
);

/*==============================================================*/
/* Index: RELATIONSHIP_10_PK                                    */
/*==============================================================*/
create unique index RELATIONSHIP_10_PK on RELATIONSHIP_10 (
ID_COMPANHIA,
ID_PONTUACAO
);

/*==============================================================*/
/* Index: RELATIONSHIP_10_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_10_FK on RELATIONSHIP_10 (
ID_COMPANHIA
);

/*==============================================================*/
/* Index: RELATIONSHIP_11_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_11_FK on RELATIONSHIP_10 (
ID_PONTUACAO
);

/*==============================================================*/
/* Table: TEM                                                   */
/*==============================================================*/
create table TEM (
   ID_PARTIDA           INT4                 not null,
   ID_PONTUACAO         INT4                 not null,
   constraint PK_TEM primary key (ID_PARTIDA, ID_PONTUACAO)
);

/*==============================================================*/
/* Index: TEM_PK                                                */
/*==============================================================*/
create unique index TEM_PK on TEM (
ID_PARTIDA,
ID_PONTUACAO
);

/*==============================================================*/
/* Index: TEM_FK                                                */
/*==============================================================*/
create  index TEM_FK on TEM (
ID_PARTIDA
);

/*==============================================================*/
/* Index: TEM2_FK                                               */
/*==============================================================*/
create  index TEM2_FK on TEM (
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
   NUM_LUGARES          INT4                 not null,
   HORA_PARTIDA         DATE                 not null,
   HORA_CHEGADA         DATE                 not null,
   constraint PK_VIAGENS primary key (ID_VIAGENS)
);

/*==============================================================*/
/* Index: VIAGENS_PK                                            */
/*==============================================================*/
create unique index VIAGENS_PK on VIAGENS (
ID_VIAGENS
);

/*==============================================================*/
/* Index: RELATIONSHIP_1_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_1_FK on VIAGENS (
ID_PARTIDA
);

/*==============================================================*/
/* Index: RELATIONSHIP_2_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_2_FK on VIAGENS (
ID_DESTINO
);

/*==============================================================*/
/* Index: FAZ_FK                                                */
/*==============================================================*/
create  index FAZ_FK on VIAGENS (
ID_AVIAO
);

alter table AGREGA
   add constraint FK_AGREGA_AGREGA_DESTINOS foreign key (ID_DESTINO)
      references DESTINOS (ID_DESTINO)
      on delete restrict on update restrict;

alter table AGREGA
   add constraint FK_AGREGA_AGREGA2_PONTUACA foreign key (ID_PONTUACAO)
      references PONTUACAO (ID_PONTUACAO)
      on delete restrict on update restrict;

alter table AVIAO
   add constraint FK_AVIAO_CONTEM_COMPANHI foreign key (ID_COMPANHIA)
      references COMPANHIA (ID_COMPANHIA)
      on delete restrict on update restrict;

alter table BAGAGENS
   add constraint FK_BAGAGENS_POSSUI_CLIENTE foreign key (ID_CLIENTE)
      references CLIENTE (ID_CLIENTE)
      on delete restrict on update restrict;

alter table BAGAGENS
   add constraint FK_BAGAGENS_TERA_VIAGENS foreign key (ID_VIAGENS)
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

alter table COMPANHIA
   add constraint FK_COMPANHI_TRABALHA_AGENCIA_ foreign key (ID_AGENCIA)
      references AGENCIA_VIAGENS (ID_AGENCIA)
      on delete restrict on update restrict;

alter table OPERADOR
   add constraint FK_OPERADOR_GERE_AGENCIA_ foreign key (ID_AGENCIA)
      references AGENCIA_VIAGENS (ID_AGENCIA)
      on delete restrict on update restrict;

alter table PONTUACAO
   add constraint FK_PONTUACA_ATRIBUI_CLIENTE foreign key (ID_CLIENTE)
      references CLIENTE (ID_CLIENTE)
      on delete restrict on update restrict;

alter table RELATIONSHIP_10
   add constraint FK_RELATION_RELATIONS_COMPANHI foreign key (ID_COMPANHIA)
      references COMPANHIA (ID_COMPANHIA)
      on delete restrict on update restrict;

alter table RELATIONSHIP_10
   add constraint FK_RELATION_RELATIONS_PONTUACA foreign key (ID_PONTUACAO)
      references PONTUACAO (ID_PONTUACAO)
      on delete restrict on update restrict;

alter table TEM
   add constraint FK_TEM_TEM_PARTIDAS foreign key (ID_PARTIDA)
      references PARTIDAS (ID_PARTIDA)
      on delete restrict on update restrict;

alter table TEM
   add constraint FK_TEM_TEM2_PONTUACA foreign key (ID_PONTUACAO)
      references PONTUACAO (ID_PONTUACAO)
      on delete restrict on update restrict;

alter table VIAGENS
   add constraint FK_VIAGENS_FAZ_AVIAO foreign key (ID_AVIAO)
      references AVIAO (ID_AVIAO)
      on delete restrict on update restrict;

alter table VIAGENS
   add constraint FK_VIAGENS_RELATIONS_PARTIDAS foreign key (ID_PARTIDA)
      references PARTIDAS (ID_PARTIDA)
      on delete restrict on update restrict;

alter table VIAGENS
   add constraint FK_VIAGENS_RELATIONS_DESTINOS foreign key (ID_DESTINO)
      references DESTINOS (ID_DESTINO)
      on delete restrict on update restrict;

