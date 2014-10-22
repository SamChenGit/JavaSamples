-- create table
create table Category(
	Id         integer constraint pk_category primary key autoincrement,
	ParentId   integer not null,
	Name       text not null constraint uq_category_name unique
);

create table CodeInfo(
	Id         integer constraint pk_codeInfo primary key autoincrement,
	Title      text not null,
	CategoryId integer constraint fk_codeInfo_category  references Category(Id) ,
	Keys       text not null,
	Content    text not null,
	Points     integer not null default 0
);

create table Resource(
	Id         integer constraint pk_resource primary key autoincrement,
	CodeInfoId integer constraint fk_resource_codeInfo references CodeInfo(Id),
	Title      text not null,
  	Location   text not null,
	ResourceType text note null
);

-- drop table
drop table CodeInfo;
drop table Category;

-- select 
select * from Category;
select * from CodeInfo;
