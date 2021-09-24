drop database if exists malatang;

create database malatang;

use malatang;

set names utf8mb4;

drop table if exists Ingredient;

create table if not exists Ingredient (
    id bigint not null auto_increment comment '配料表主键',
    name varchar(24) not null comment '配料的名称',
    primary key(id)
)engine=InnoDB auto_increment=1 default charset=utf8mb4 comment='配料表';

insert into Ingredient (name) values ('青菜');
insert into Ingredient (name) values ('香菇');
insert into Ingredient (name) values ('茼蒿');
insert into Ingredient (name) values ('山药');
insert into Ingredient (name) values ('海带丝');
insert into Ingredient (name) values ('娃娃菜');
insert into Ingredient (name) values ('菠菜');
insert into Ingredient (name) values ('生菜');
insert into Ingredient (name) values ('龙虾球');
insert into Ingredient (name) values ('海胆包');


drop table if exists Morder;

create table if not exists Morder (
    id bigint not null auto_increment comment '订单表主键',
    name varchar(15) not null comment '收货人姓名',
    phone varchar(11) not null comment '收货人号码',
    address varchar(90) not null comment '收货人地址',
    create_at datetime not null comment '订单创建时间',
    primary key (id)
)engine=InnoDB auto_increment=1 default charset=utf8mb4 comment='麻辣烫的订单表';

drop table if exists Morder_Ingredients;

create table if not exists Morder_Ingredients(
    orderId bigint not null comment '订单表的id',
    ingredientId bigint not null comment '配料表的id'
)engine=InnoDB default charset=utf8mb4 comment='订单-配料表';

-- 添加外键
-- alter table Morder_Ingredients add foreign key (orderId) references Morder(id);
-- alter table Morder_Ingredients add foreign key (ingredientId) references Ingredient(id);

-- 删除外键
-- show create table morder_ingredients;
-- alter table morder_ingredients drop foreign key morder_ingredients_ibfk_2;


-- 用于定时任务的数据库表
DROP TABLE IF EXISTS scheduled;

CREATE TABLE scheduled (
	cron_id INT NOT NULL AUTO_INCREMENT,
	cron_name VARCHAR(30) NOT NULL,
	cron VARCHAR(30) NOT NULL,
	PRIMARY KEY (cron_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='基于接口的定时任务表';

INSERT INTO scheduled VALUES (NULL, '定时器任务一', '0/6 * * * * ?');

-- 用于spring security安全的user等表的sql语句

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
	id INT NOT NULL AUTO_INCREMENT COMMENT '用户角色表主键',
	NAME VARCHAR(32) NOT NULL COMMENT '用户角色名称',
	PRIMARY KEY (id)
)ENGINE=INNODB, AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

INSERT INTO roles VALUES (NULL, 'ROLE_ADMIN');
INSERT INTO roles VALUES (NULL, 'ROLE_USER');
INSERT INTO roles VALUES (NULL, 'ROLE_TEST');


DROP TABLE IF EXISTS user;

CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
	username VARCHAR(64) NOT NULL COMMENT '用户名',
	PASSWORD VARCHAR(255) NOT NULL COMMENT '用户密码',
	enabled TINYINT NOT NULL COMMENT '用户是否可用',
	email VARCHAR(64) NOT NULL COMMENT '用户邮箱',
	regtime DATETIME NOT NULL COMMENT '用户注册时间',
	PRIMARY KEY (id)
)ENGINE=INNODB, AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


DROP TABLE IF EXISTS user_roles;

CREATE TABLE user_roles(
	uid INT NOT NULL COMMENT '用户id',
	rid INT NOT NULL COMMENT '用户角色id'
)ENGINE=INNODB, AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户id_角色id表';