# rackspace-rest-test

Task:

Design a simple Restful application (Spring/SpringBoot, JPA)to handle vehicles inventory & search.

Basic requirements: 

1)     Vehicle could be of different types with specific properties and behaviors. Types: Car, Truck, Airplane, Drone, Amphibian, Boat.

2)     CRUD operations to manage vehicles

3)     Save to a local db like h2 or sqlite

4)     Ability to search for vehicles

5)     Delete recent API - should delete last added vehicle

Solution:

1) To make the vehicles design flexible, I decided to build up 6 models for 6 vehicles, and each vehicles has the different
properties and behaviors. 

2) I did all the CRUD operations to manage the vehicles.

3) I save to H2.

4) We can search based on one or two parameters on each type of vehicle.

5) For the last requirement, I've built up the Generic Model, Vehicle, to find the last item. The algorithm is that I will
try to find the one with highest ID and with latest timestamp. Then I remove the item from the specific Vehicle table and
the appropriate vehicle table.
