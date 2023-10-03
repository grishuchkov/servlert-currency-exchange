create table Currencies
(
    Id        bigserial primary key,
    Code      varchar(10) not null unique,
    Full_Name varchar(50) not null,
    Sign      varchar(10) not null
);

create table Exchange_Rates
(
    Id                 bigserial primary key,
    Base_Currency_Id   int        not null references Currencies (Id),
    Target_Currency_Id int        not null references Currencies (Id),
    Rate               DECIMAL(6) not null,
    unique (Base_Currency_Id, Target_Currency_Id)
);