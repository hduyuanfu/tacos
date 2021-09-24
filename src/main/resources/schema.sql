
-- 一个order可能包含多个taco,一个taco可能包含多个Ingredient
create table if not exists Ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null,
  primary key(id)
);

create table if not exists Taco (
  id bigint(11) not null auto_increment,
  name varchar(50) not null,
  createdAt timestamp not null,
  primary key(id)
);

create table if not exists Taco_Ingredients (
  malatang bigint(11) not null,
  ingredient varchar(4) not null
);

-- 引用的那个字段必须为另一个表的主键，否则创建外键时会失败
alter table Taco_Ingredients
    add foreign key (malatang) references Taco(id);
alter table Taco_Ingredients
    add foreign key (ingredient) references Ingredient(id);
--     add foreign key Taco_Ingredients(ingredient) references Ingredient(id);像Taco_Ingredients(ingredient)这种给某个字段
-- 加外键时，前面还带有表名的，表名只能出现一次，同一个文件中出现两次就会报错，所以最好用上面那种不带表名的

create table if not exists Taco_Order (
	id bigint(11) not null auto_increment,
	deliveryName varchar(50) not null,
	deliveryStreet varchar(50) not null,
	deliveryCity varchar(50) not null,
	deliveryState varchar(2) not null,
	deliveryZip varchar(10) not null,
	ccNumber varchar(16) not null,
	ccExpiration varchar(5) not null,
	ccCVV varchar(3) not null,
    placedAt timestamp not null,
    primary key(id)
);

create table if not exists Taco_Order_Tacos (
	tacoOrder bigint(11) not null,
	malatang bigint(11) not null
);

alter table Taco_Order_Tacos
    add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos
    add foreign key (malatang) references Taco(id);

