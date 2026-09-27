CREATE TABLE customers (
                           customer_id VARCHAR(50) NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) NOT NULL,
                           tier VARCHAR(255) NOT NULL,
                           country VARCHAR(255) NOT NULL,
                           created_at DATE NOT NULL,
                           verified BOOLEAN NOT NULL,
                           PRIMARY KEY (customer_id),
                           CONSTRAINT uk_customers_email UNIQUE (email)
) ENGINE = InnoDB;

CREATE TABLE customer_tags (
                               customer_id VARCHAR(50) NOT NULL,
                               tag VARCHAR(255) NOT NULL,
                               PRIMARY KEY (customer_id, tag),
                               CONSTRAINT fk_customer_tags_customer
                                   FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
) ENGINE = InnoDB;

CREATE TABLE orders (
                        order_id VARCHAR(50) NOT NULL,
                        customer_id VARCHAR(50) NOT NULL,
                        status VARCHAR(255) NOT NULL,
                        placed_at DATE NOT NULL,
                        delivered_at DATE NULL,
                        eligible_return_until DATE NULL,
                        total DECIMAL(12, 2) NOT NULL,
                        currency VARCHAR(255) NOT NULL,
                        payment_status VARCHAR(255) NOT NULL,
                        tracking_number VARCHAR(255) NULL,
                        PRIMARY KEY (order_id),
                        CONSTRAINT fk_orders_customer
                            FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
) ENGINE = InnoDB;

CREATE TABLE order_items (
                             order_item_id BIGINT NOT NULL AUTO_INCREMENT,
                             order_id VARCHAR(50) NOT NULL,
                             sku VARCHAR(255) NOT NULL,
                             name VARCHAR(255) NOT NULL,
                             quantity INT NOT NULL,
                             category VARCHAR(255) NOT NULL,
                             final_sale BOOLEAN NOT NULL,
                             PRIMARY KEY (order_item_id),
                             CONSTRAINT fk_order_items_order
                                 FOREIGN KEY (order_id) REFERENCES orders (order_id)
) ENGINE = InnoDB;

CREATE TABLE tickets (
                         ticket_id VARCHAR(50) NOT NULL,
                         customer_id VARCHAR(50) NOT NULL,
                         order_id VARCHAR(50) NOT NULL,
                         channel VARCHAR(255) NOT NULL,
                         subject VARCHAR(255) NOT NULL,
                         body TEXT NOT NULL,
                         created_at DATETIME(6) NOT NULL,
                         status VARCHAR(255) NOT NULL,
                         expected_category VARCHAR(255) NOT NULL,
                         expected_priority VARCHAR(255) NOT NULL,
                         expected_sentiment VARCHAR(255) NOT NULL,
                         expected_escalation BOOLEAN NOT NULL,
                         triaged_at DATETIME(6) NULL,
                         PRIMARY KEY (ticket_id),
                         CONSTRAINT fk_tickets_customer
                             FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
                         CONSTRAINT fk_tickets_order
                             FOREIGN KEY (order_id) REFERENCES orders (order_id)
) ENGINE = InnoDB;

CREATE TABLE ticket_expected_actions (
                                         ticket_id VARCHAR(50) NOT NULL,
                                         expected_action VARCHAR(255) NOT NULL,
                                         PRIMARY KEY (ticket_id, expected_action),
                                         CONSTRAINT fk_ticket_expected_actions_ticket
                                             FOREIGN KEY (ticket_id) REFERENCES tickets (ticket_id)
) ENGINE = InnoDB;