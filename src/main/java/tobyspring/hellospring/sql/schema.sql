create table orders
(
    id          bigint       not null,
    orderNumber varchar(255) not null,
    totalPrice  numeric(38, 2),
    primary key (id)
)
alter table if exists orders drop constraint if exists UKt5ee3vjmonruwsp9g423dhrek
alter table if exists orders add constraint UKt5ee3vjmonruwsp9g423dhrek unique (orderNumber)
create sequence orders_SEQ start with 1 increment by 50