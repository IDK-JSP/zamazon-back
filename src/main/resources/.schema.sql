CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(15) NOT NULL
);

CREATE TABLE product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,
    poster_path VARCHAR(255),
    description VARCHAR(255),
    price DOUBLE,
    quantity int
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255),
    FOREIGN KEY (email) REFERENCES user(email)
);
CREATE TABLe orders_content(
    order_id int,
    product_id int,
    primary key(order_id,product_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
)
