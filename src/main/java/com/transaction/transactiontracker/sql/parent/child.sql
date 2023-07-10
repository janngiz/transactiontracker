

CREATE TABLE child (
  child_id BINARY(16) PRIMARY KEY,
  parent_id BINARY(16),
  sender VARCHAR(255),
  receiver VARCHAR(255),
  total_amount DECIMAL(10, 2),
  paid_amount DECIMAL(10, 2),
  FOREIGN KEY (parent_id) REFERENCES parent(parent_id)
);
