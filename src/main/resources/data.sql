//jdbc:h2:mem:testdb

insert into car
values(10001,'Toyota', '4');

insert into car
values(10002,'Lexus', '4');

insert into truck
values(20001,'Mazda', '8');

insert into truck
values(20002,'GMC', '16');

insert into airplane
values(30001,'Delta', '40');

insert into airplane
values(30002,'Qatar', '50');

insert into drone
values(40001,'Karma', '45');

insert into drone
values(40002,'GoPro', '55');

insert into amphibian
values(50001,'Frog', '4');

insert into amphibian
values(50002,'lizard', '6');

insert into boat
values(60001,'KonTiki', '3');

insert into boat
values(60002,'Freestyle', '4');

insert into vehicle
values(10001, current_timestamp(), 'Car');

insert into vehicle
values(10002, current_timestamp(), 'Car');

insert into vehicle
values(20001, current_timestamp(), 'Truck');

insert into vehicle
values(20002, current_timestamp(), 'Truck');

insert into vehicle
values(30001, current_timestamp(), 'Airplane');

insert into vehicle
values(30002, current_timestamp(), 'Airplane');

insert into vehicle
values(40001, current_timestamp(), 'Drone');

insert into vehicle
values(40002, current_timestamp(), 'Drone');

insert into vehicle
values(50001, current_timestamp(), 'Amphibian');

insert into vehicle
values(50002, current_timestamp(), 'Amphibian');

insert into vehicle
values(60001, current_timestamp(), 'Boat');

insert into vehicle
values(60002, current_timestamp(), 'Boat');
