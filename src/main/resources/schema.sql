CREATE TABLE IF NOT EXISTS events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    event_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS ticket_pools (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id BIGINT NOT NULL,
    ticket_type VARCHAR(50) NOT NULL,
    available_tickets INT NOT NULL,
    version INT NOT NULL DEFAULT 0,
    FOREIGN KEY (event_id) REFERENCES events(id)
);

CREATE TABLE IF NOT EXISTS reservations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id BIGINT NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    ticket_type VARCHAR(50) NOT NULL,
    reservation_time TIMESTAMP NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events(id)
);