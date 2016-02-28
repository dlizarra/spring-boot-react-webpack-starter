-- users
INSERT INTO "users"("id","username", "password", "enabled") VALUES (1, 'david', '$2a$10$.ysnHr4PeaEgfljWaHexYO41hvcqmxLFOG69179iOLkHUKXRFpXKu', 1);
INSERT INTO "users"("id","username", "password", "enabled") VALUES (2, 'mark', '$2a$10$QIWJYadawFt4QQut5MRgdeSMQKFPROQELPWphpGgHYQl3VwLsqcgS', 1);
INSERT INTO "users"("id","username", "password", "enabled") VALUES (3, 'john', '$2a$10$LUVfN36xEPS4kqD7NNUuUemaI30J6wzYpkYN6X7UzYhpDun6vaLFC', 1);
INSERT INTO "users"("id","username", "password", "enabled") VALUES (4, 'ryan', '$2a$10$RwAaoqOzsS9J1RSivRozUeOj1Bs/uExeP1TMa6wG21zwll3Yp9DUC', 1);
INSERT INTO "users"("id","username", "password", "enabled") VALUES (5, 'martin', '$2a$10$ACRP9z0Ya//Nbym3oQj9Keq4NNXwoq.oyCnUlx1819RvlzLcqDTJq/uExeP1TMa6wG21zwll3Yp9DUC', 1);

-- role
INSERT INTO "role"("id", "rolename", "user_id") VALUES (1, 'ROLE_ADMIN', 1);
INSERT INTO "role"("id", "rolename", "user_id") VALUES (2, 'ROLE_USER', 1);
INSERT INTO "role"("id", "rolename", "user_id") VALUES (3, 'ROLE_USER', 2);
