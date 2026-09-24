INSERT INTO events (name, event_date) VALUES ('Koncert Rockowy - Warsaw Arena', '2026-10-15 20:00:00');
INSERT INTO events (name, event_date) VALUES ('Koncert rockowy', '2026-11-20 19:00:00');

INSERT INTO ticket_pools (event_id, ticket_type, price, available_tickets, version) VALUES (1, 'STANDARD', 150.00, 500, 0);
INSERT INTO ticket_pools (event_id, ticket_type, price, available_tickets, version) VALUES (1, 'VIP', 300.00, 50, 0);
INSERT INTO ticket_pools (event_id, ticket_type, price, available_tickets, version) VALUES (1, 'PREMIUM', 200.00, 150, 0);

INSERT INTO ticket_pools (event_id, ticket_type, price, available_tickets, version) VALUES (2, 'STANDARD', 150.00, 500, 0);
INSERT INTO ticket_pools (event_id, ticket_type, price, available_tickets, version) VALUES (2, 'VIP', 300.00, 50, 0);
INSERT INTO ticket_pools (event_id, ticket_type, price, available_tickets, version) VALUES (2, 'PREMIUM', 200.00, 150, 0);