use swabhav;

create table if not exists Organizer (
	orgId int primary key auto_increment not null,
    orgName varchar(200) not null,
);

create table if not exists Exhibitions (
	exbId int primary key auto_increment not null,
    exbName varchar(200) not null,
    exbLocation varchar(200) not null,
    orgId int not null,
    foreign key (orgId) references Organizer(orgId)
);

create table if not exists Visitors (
	visId int primary key auto_increment not null,
    visName varchar(100) not null,
    exbId int not null,
    foreign key (exbId) references Exhibitions(exbId)
);