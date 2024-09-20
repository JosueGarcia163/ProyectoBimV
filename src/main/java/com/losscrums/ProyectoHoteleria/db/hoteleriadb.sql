-- drop database hotelerias;
use hotelerias;
 
-- Inserción 1
INSERT INTO hotel (name, address, num_stars, comfort ) 
VALUES ('Hotel Sol y Mar', 'Calle del Sol 123, Barcelona', 4, 'Confortable');
 
-- Inserción 2
INSERT INTO hotel (name, address, num_stars, comfort) 
VALUES ('Gran Hotel Plaza', 'Avenida Central 456, Madrid', 5, 'Lujo');
 
-- Inserción 3
INSERT INTO hotel (name, address, num_stars, comfort) 
VALUES ('Hotel Costa Azul', 'Playa del Mar 789, Valencia', 3, 'Sencillo');
 
-- Inserción 4
INSERT INTO hotel (name, address, num_stars, comfort) 
VALUES ('Hotel Montaña', 'Calle Alta 101, Granada', 4, 'Confortable');
 
-- Inserción 5
INSERT INTO hotel (name, address, num_stars, comfort) 
VALUES ('Hotel Luna', 'Avenida del Lago 202, Sevilla', 3, 'Sencillo');
 

-- Insertar evento 1
INSERT INTO event (event_type, name, cost, date_start, date_finish, hotel_id)
VALUES 
('Conference', 'Tech Conference 2024', 150.00, '2024-11-01 09:00:00', '2024-11-01 17:00:00', 1);

-- Insertar evento 2
INSERT INTO event (event_type, name, cost, date_start, date_finish, hotel_id)
VALUES 
('Wedding', 'John and Jane Wedding', 500.00, '2024-12-15 12:00:00', '2024-12-15 23:59:00', 2);

-- Insertar evento 3
INSERT INTO event (event_type, name, cost, date_start, date_finish, hotel_id)
VALUES 
('Concert', 'Rock Fest 2024', 200.00, '2024-11-20 18:00:00', '2024-11-20 22:00:00', 3);

-- Insertar evento 4
INSERT INTO event (event_type, name, cost, date_start, date_finish, hotel_id)
VALUES 
('Seminar', 'Business Growth Seminar', 100.00, '2024-11-05 10:00:00', '2024-11-05 16:00:00', 4);

-- Insertar evento 5
INSERT INTO event (event_type, name, cost, date_start, date_finish, hotel_id)
VALUES 
('Party', 'New Year Eve Party', 250.00, '2024-12-31 20:00:00', '2025-01-01 01:00:00', 5);

-- Inserción 1
INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id, event_id_event) 
VALUES ('Suite', '2 personas', 'Disponible', '2024-09-15', 1,1);
 
-- Inserción 2
INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id) 
VALUES ('Doble', '2 personas', 'No Disponible', '2024-09-16', 2,2);
 
-- Inserción 3
INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id) 
VALUES ('Individual', '1 persona', 'Disponible', '2024-09-17', 3,3);
 
-- Inserción 4
INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id) 
VALUES ('Familiar', '4 personas', 'Disponible', '2024-09-18', 4,4);
 
-- Inserción 5
INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id) 
VALUES ('Suite', '2 personas', 'No Disponible', '2024-09-19', 5,5);
 
INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id) 
VALUES ('Suite', '2 personas', 'No Disponible', '2024-09-19', 1,5);

INSERT INTO services (type_service, capacity, cost, description, event_id_event)
VALUES ('Spa', 10, 50.00, 'Servicio de spa completo con masajes', 1);

INSERT INTO services (type_service, capacity, cost, description, event_id_event)
VALUES ('Gimnasio', 20, 30.00, 'Acceso al gimnasio del hotel', 2);

INSERT INTO services (type_service, capacity, cost, description, event_id_event)
VALUES ('Piscina', 15, 25.00, 'Acceso a la piscina con servicio de toallas', 3);

INSERT INTO services (type_service, capacity, cost, description, event_id_event)
VALUES ('Sala de conferencias', 50, 100.00, 'Alquiler de sala de conferencias', 4);

INSERT INTO services (type_service, capacity, cost, description, event_id_event)
VALUES ('Servicio de catering', 100, 200.00, 'Catering para eventos especiales', 5);



 
select * from hotel;
select * from room;
select * from event;
select * from services;