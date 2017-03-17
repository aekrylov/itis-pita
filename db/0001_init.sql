create table users (
  id SERIAL PRIMARY KEY,
  name VARCHAR(64) NOT NULL ,
  email VARCHAR(45) UNIQUE NOT NULL ,
  phone VARCHAR(32) NOT NULL ,
  password_hash VARCHAR(64) NOT NULL ,
  secret_question VARCHAR(128),
  secret_answer VARCHAR(128),
  role VARCHAR(16) NOT NULL ,
  active BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO users (name, email, phone, password_hash, role) VALUES ('Admin', 'admin@adm.in', 'foo', 'bar', 'superuser');