-- Migrate 1.3.0-beta.1 to 1.3.0-beta.2
-- Migrate posts Table
update posts set slug=url;
alter table posts alter column slug TYPE varchar(255);
alter table posts alter column url TYPE varchar(255);
alter table posts alter column summary TYPE text;

-- Migrate categories Table
update categories set slug=slug_name;
alter table categories alter column slug TYPE varchar(255);
alter table categories alter column slug SET not null;

alter table categories alter column name TYPE varchar(255);
alter table categories alter column name SET not null;

alter table categories alter column slug_name TYPE varchar(50);

-- Migrate tags Table
update tags set slug=slug_name;
alter table tags alter column slug TYPE varchar(50);
alter table tags alter column slug SET not null;

alter table tags alter column slug_name TYPE varchar(255);