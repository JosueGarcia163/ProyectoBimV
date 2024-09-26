-- drop database hotelerias;
use hotelerias;
-- Insertar user
INSERT INTO user (name, surname, username, email, password, nit)
VALUES ('Juan', 'Pérez', 'juanperez', 'juan.perez@example.com', 'password123', 123456789);

INSERT INTO user (name, surname, username, email, password, nit)
VALUES ('María', 'González', 'mariagonz', 'maria.gonzalez@example.com', 'password456', 987654321);

INSERT INTO user (name, surname, username, email, password, nit)
VALUES ('Carlos', 'López', 'carlopez', 'carlos.lopez@example.com', 'password789', 456123789);

INSERT INTO user (name, surname, username, email, password, nit)
VALUES ('Ana', 'Martínez', 'anamartinez', 'ana.martinez@example.com', 'password321', 789123456);

INSERT INTO user (name, surname, username, email, password, nit)
VALUES ('Pedro', 'Ramírez', 'pedroram', 'pedro.ramirez@example.com', 'password654', 321654987);

INSERT INTO user (name, surname, username, email, password, nit)
VALUES ('Pedro', 'Ramírez', 'emilis', ' emily.ramirez@example.com', 'password654', 321654987);


-- Insertar hotel.
INSERT INTO hotel (name, address, num_stars, comfort, number_rent) 
VALUES ('Hotel Sol y Mar', 'Calle del Sol 123, Barcelona', 4, 'Confortable',1);
 
-- Inserción 2
INSERT INTO hotel (name, address, num_stars, comfort, number_rent) 
VALUES ('Gran Hotel Plaza', 'Avenida Central 456, Madrid', 5, 'Lujo',1);
 
-- Inserción 3
INSERT INTO hotel (name, address, num_stars, comfort, number_rent) 
VALUES ('Hotel Costa Azul', 'Playa del Mar 789, Valencia', 3, 'Sencillo',1);
 
-- Inserción 4
INSERT INTO hotel (name, address, num_stars, comfort, number_rent) 
VALUES ('Hotel Montaña', 'Calle Alta 101, Granada', 4, 'Confortable',1);
 
-- Inserción 5
INSERT INTO hotel (name, address, num_stars, comfort, number_rent)
VALUES ('Hotel Luna', 'Avenida del Lago 202, Sevilla', 3, 'Sencillo',1);
 

-- Insertar evento 1
INSERT INTO event (event_type, name, cost, date_start, date_finish, hotel_id_hotel)
VALUES 
('Conference', 'Tech Conference 2024', 150.00, '2024-11-01 09:00:00', '2024-11-01 17:00:00', 1);

-- Insertar evento 2
INSERT INTO event (event_type, name, cost, date_start, date_finish, hotel_id_hotel)
VALUES 
('Wedding', 'John and Jane Wedding', 500.00, '2024-12-15 12:00:00', '2024-12-15 23:59:00', 2);

-- Insertar evento 3
INSERT INTO event (event_type, name, cost, date_start, date_finish, hotel_id_hotel)
VALUES 
('Concert', 'Rock Fest 2024', 200.00, '2024-11-20 18:00:00', '2024-11-20 22:00:00', 3);

-- Insertar evento 4
INSERT INTO event (event_type, name, cost, date_start, date_finish, hotel_id_hotel)
VALUES 
('Seminar', 'Business Growth Seminar', 100.00, '2024-11-05 10:00:00', '2024-11-05 16:00:00', 5);

-- Insertar evento 5
INSERT INTO event (event_type, name, cost, date_start, date_finish, hotel_id_hotel)
VALUES 
('Party', 'New Year Eve Party', 250.00, '2024-12-31 20:00:00', '2025-01-01 01:00:00', 4);

INSERT INTO event (event_type, name, cost, date_start, date_finish, hotel_id_hotel)
VALUES 
('Party', 'party', 250.00, '2024-12-31 20:00:00', '2025-01-01 01:00:00', 4);

-- Insertar room 1.
INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id_hotel, event_id_event) 
VALUES ('Suite', '2 personas', 'Disponible', '2024-09-15', 2,5);
 
-- Insertar room 2.
INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id_hotel, event_id_event) 
VALUES ('Doble', '2 personas', 'No Disponible', '2024-09-16', 1,4);
 
-- Insertar room 3.
INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id_hotel, event_id_event) 
VALUES ('Individual', '1 persona', 'Disponible', '2024-09-17', 3,3);
 
-- Insertar room 4.
INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id_hotel, event_id_event) 
VALUES ('Familiar', '4 personas', 'Disponible', '2024-09-18', 4,2);
 
-- Insertar room 5.
INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id_hotel, event_id_event) 
VALUES ('Suite', '2 personas', 'No Disponible', '2024-09-19', 5,1);

INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id_hotel, event_id_event) 
VALUES ('Suite', '2 personas', 'No Disponible', '2024-09-19', 5,1);

INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id_hotel, event_id_event) 
VALUES ('Individual', '1 persona', 'Disponible', '2024-09-17', 3,3);

INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id_hotel, event_id_event) 
VALUES ('Individual', '1 persona', 'Disponible', '2024-09-17', 3,3);

INSERT INTO room (room_Type, capacity, availability, availability_date, hotel_id_hotel, event_id_event) 
VALUES ('Individual', '1 persona', 'Disponible', '2024-09-17', 3,3);


-- Insertar reservacion.
INSERT INTO reservation (start, end, cost, status, user_id_user, room_id_room)
VALUES ('2024-10-01 14:00:00', '2024-10-01 16:00:00', '100.00', 'PROCESS', 5,1);

INSERT INTO reservation (start, end, cost, status, user_id_user, room_id_room)
VALUES ('2024-11-05 10:00:00', '2024-11-05 12:00:00', '150.00', 'PROCESS', 4,2);

INSERT INTO reservation (start, end, cost, status, user_id_user, room_id_room)
VALUES ('2024-12-12 18:00:00', '2024-12-12 20:00:00', '200.00', 'RESERVED', 3,3);

INSERT INTO reservation (start, end, cost, status, user_id_user, room_id_room)
VALUES ('2024-09-30 09:00:00', '2024-09-30 11:00:00', '120.00', 'RESERVED', 2,4);

INSERT INTO reservation (start, end, cost, status, user_id_user, room_id_room)
VALUES ('2024-10-15 15:00:00', '2024-10-15 17:00:00', '180.00', 'CANCELED', 1,7);

INSERT INTO reservation (start, end, cost, status, user_id_user, room_id_room)
VALUES ('2024-10-15 15:00:00', '2024-10-15 17:00:00', '200.00', 'RESERVED', 3,5);

INSERT INTO reservation (start, end, cost, status, user_id_user, room_id_room)
VALUES ('2024-10-15 15:00:00', '2024-10-15 17:00:00', '280.00', 'RESERVED', 4,6);



-- Insertar services
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








select * from user;
select * from reservation;
select * from hotel;
select * from room;	
select * from event;	
select * from services;