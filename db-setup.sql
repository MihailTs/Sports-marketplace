-- PostgreSQL-compatible version of your schema

CREATE TABLE gender (
  id UUID PRIMARY KEY,
  code VARCHAR(255),
  name VARCHAR(255)
);

CREATE TABLE role (
  id UUID PRIMARY KEY,
  code VARCHAR(255),
  name VARCHAR(255)
);

CREATE TABLE sport (
  id UUID PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE users (
  id UUID PRIMARY KEY,
  email VARCHAR(255),
  password VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  birth_date DATE,
  phone VARCHAR(255),
  gender_id UUID,
  role_id UUID,
  sport_id UUID,
  profile_image_url TEXT,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY (gender_id) REFERENCES gender(id),
  FOREIGN KEY (role_id) REFERENCES role(id),
  FOREIGN KEY (sport_id) REFERENCES sport(id)
);

CREATE TABLE address (
  id UUID PRIMARY KEY,
  user_id UUID,
  street VARCHAR(255),
  zip_code VARCHAR(255),
  city VARCHAR(255),
  country VARCHAR(255),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE category (
  id UUID PRIMARY KEY,
  name VARCHAR(255),
  sport_id UUID,
  FOREIGN KEY (sport_id) REFERENCES sport(id)
);

CREATE TABLE product (
  id UUID PRIMARY KEY,
  seller_id UUID,
  title VARCHAR(255),
  description TEXT,
  category_id UUID,
  condition VARCHAR(255),
  price DECIMAL(10,2),
  status VARCHAR(255),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY (seller_id) REFERENCES users(id),
  FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE product_variant (
  id UUID PRIMARY KEY,
  product_id UUID,
  size VARCHAR(255),
  color VARCHAR(255),
  price DECIMAL(10,2),
  stock INT,
  FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE product_image (
  id UUID PRIMARY KEY,
  product_id UUID,
  url TEXT,
  is_primary BOOLEAN,
  FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE tag (
  id UUID PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE product_tag (
  id UUID PRIMARY KEY,
  product_id UUID,
  tag_id UUID,
  FOREIGN KEY (product_id) REFERENCES product(id),
  FOREIGN KEY (tag_id) REFERENCES tag(id)
);

CREATE TABLE chat (
  id UUID PRIMARY KEY,
  created_at TIMESTAMP
);

CREATE TABLE chat_participant (
  id UUID PRIMARY KEY,
  chat_id UUID,
  user_id UUID,
  FOREIGN KEY (chat_id) REFERENCES chat(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE message (
  id UUID PRIMARY KEY,
  chat_id UUID,
  sender_id UUID,
  content TEXT,
  sent_at TIMESTAMP,
  read_at TIMESTAMP,
  FOREIGN KEY (chat_id) REFERENCES chat(id),
  FOREIGN KEY (sender_id) REFERENCES users(id)
);

CREATE TABLE payment_method (
  id UUID PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE shipping_method (
  id UUID PRIMARY KEY,
  name VARCHAR(255),
  estimated_days INT,
  cost DECIMAL(10,2)
);

CREATE TABLE transaction (
  id UUID PRIMARY KEY,
  product_id UUID,
  buyer_id UUID,
  seller_id UUID,
  status VARCHAR(255),
  payment_method_id UUID,
  shipping_method_id UUID,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY (product_id) REFERENCES product(id),
  FOREIGN KEY (buyer_id) REFERENCES users(id),
  FOREIGN KEY (seller_id) REFERENCES users(id),
  FOREIGN KEY (payment_method_id) REFERENCES payment_method(id),
  FOREIGN KEY (shipping_method_id) REFERENCES shipping_method(id)
);

CREATE TABLE review (
  id UUID PRIMARY KEY,
  transaction_id UUID,
  reviewer_id UUID,
  reviewee_id UUID,
  product_id UUID,
  rating INT,
  comment TEXT,
  created_at TIMESTAMP,
  FOREIGN KEY (transaction_id) REFERENCES transaction(id),
  FOREIGN KEY (reviewer_id) REFERENCES users(id),
  FOREIGN KEY (reviewee_id) REFERENCES users(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE cart (
  id UUID PRIMARY KEY,
  user_id UUID,
  created_at TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE cart_item (
  id UUID PRIMARY KEY,
  cart_id UUID,
  product_variant_id UUID,
  quantity INT,
  FOREIGN KEY (cart_id) REFERENCES cart(id),
  FOREIGN KEY (product_variant_id) REFERENCES product_variant(id)
);

CREATE TABLE wishlist (
  id UUID PRIMARY KEY,
  user_id UUID,
  product_id UUID,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE dispute (
  id UUID PRIMARY KEY,
  transaction_id UUID,
  reported_by_id UUID,
  reason TEXT,
  status VARCHAR(255),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY (transaction_id) REFERENCES transaction(id),
  FOREIGN KEY (reported_by_id) REFERENCES users(id)
);

CREATE TABLE dispute_message (
  id UUID PRIMARY KEY,
  dispute_id UUID,
  user_id UUID,
  content TEXT,
  created_at TIMESTAMP,
  FOREIGN KEY (dispute_id) REFERENCES dispute(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE forum (
  id UUID PRIMARY KEY,
  title VARCHAR(255),
  description TEXT,
  sport_id UUID,
  created_at TIMESTAMP,
  FOREIGN KEY (sport_id) REFERENCES sport(id)
);

CREATE TABLE forum_post (
  id UUID PRIMARY KEY,
  forum_id UUID,
  user_id UUID,
  title VARCHAR(255),
  content TEXT,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY (forum_id) REFERENCES forum(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE forum_comment (
  id UUID PRIMARY KEY,
  post_id UUID,
  user_id UUID,
  content TEXT,
  created_at TIMESTAMP,
  FOREIGN KEY (post_id) REFERENCES forum_post(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE event (
  id UUID PRIMARY KEY,
  title VARCHAR(255),
  description TEXT,
  location VARCHAR(255),
  start_datetime TIMESTAMP,
  end_datetime TIMESTAMP,
  sport_id UUID,
  created_by_id UUID,
  created_at TIMESTAMP,
  FOREIGN KEY (sport_id) REFERENCES sport(id),
  FOREIGN KEY (created_by_id) REFERENCES users(id)
);

CREATE TABLE event_participant (
  id UUID PRIMARY KEY,
  event_id UUID,
  user_id UUID,
  status VARCHAR(255),
  FOREIGN KEY (event_id) REFERENCES event(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE equipment_guide (
  id UUID PRIMARY KEY,
  title VARCHAR(255),
  content TEXT,
  sport_id UUID,
  user_id UUID,
  created_at TIMESTAMP,
  FOREIGN KEY (sport_id) REFERENCES sport(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);
