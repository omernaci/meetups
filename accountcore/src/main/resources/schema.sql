CREATE TABLE customer (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255),
      customer_type VARCHAR(50),
      email VARCHAR(255),
      phone_number VARCHAR(50)
);

CREATE TABLE account (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     customer_id BIGINT,
     currency VARCHAR(255),
     account_name VARCHAR(255),
     balance DECIMAL(19, 2),
     account_type VARCHAR(50),
     account_status VARCHAR(50),
     CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);