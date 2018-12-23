create table db_example.User(
  ID INT not null auto_increment,
  Login varchar(200),
  Password varchar(200),
  Name varchar(200),
  LastName varchar(200),
  Permission bool,
  Email varchar(200),
  Avatar longblob,
  primary key(id)
);

create table db_example.Beer(
  ID INT not null auto_increment,
  Name varchar(200),
  Factory varchar(200),
  IBU varchar(200),
  NumberScore int,
  Rating double,
  Abv double,
  Calorie double,
  Style varchar(200),
  Price double,
  Details varchar(200),
  Photo longblob not null,
  primary key(id)
);

create table db_example.Reviews(
  ID INT not null auto_increment,
  Commensts longtext,
  Rating double,
  DataAdd date,
  TimeAdd time,
  ID_User int,
  ID_Beer int,
  Photos longblob not null,
  primary key(id)
);

create table db_example.MyBeers(
  ID INT not null auto_increment,
  ID_Beer int,
  ID_User int,
  DataAdd date,
  primary key (id)
);