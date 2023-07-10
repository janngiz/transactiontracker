CREATE TABLE parent (
  parent_id BINARY(16) PRIMARY KEY,
  sender VARCHAR(255),
  receiver VARCHAR(255),
  total_amount DECIMAL(10, 2),
  total_paid_amount DECIMAL(10, 2)
);
