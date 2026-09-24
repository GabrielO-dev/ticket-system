DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS ticket_pools;
DROP TABLE IF EXISTS events;

CREATE TABLE IF NOT EXISTS events
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    event_date TIMESTAMP NOT NULL
);

CREATE TABLE ticket_pools
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id          BIGINT,
    ticket_type       VARCHAR(50),
    price             DECIMAL(10, 2),
    available_tickets INT,
    version           INT
);

CREATE TABLE IF NOT EXISTS reservations
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id BIGINT NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    ticket_type VARCHAR(50) NOT NULL,
    reservation_time TIMESTAMP NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events(id)
);