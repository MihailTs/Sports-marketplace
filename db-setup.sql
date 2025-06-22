CREATE TABLE "sport" (
  "id" uuid PRIMARY KEY,
  "name" varchar(255)
);

CREATE TABLE "users" (
  "id" uuid PRIMARY KEY,
  "email" varchar(255) UNIQUE NOT NULL,
  "password" varchar(255) NOT NULL,
  "first_name" varchar(255) NOT NULL,
  "last_name" varchar(255) NOT NULL,
  "birth_date" date,
  "phone" varchar(255),
  "gender" varchar(2),
  "role" varchar(5),
  "profile_image_url" text,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "address" (
  "id" uuid PRIMARY KEY,
  "user_id" uuid,
  "street" varchar(255),
  "zip_code" varchar(255),
  "city" varchar(255),
  "country" varchar(255),
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "category" (
  "id" uuid PRIMARY KEY,
  "name" varchar(255) NOT NULL
);

CREATE TABLE "product" (
  "id" uuid PRIMARY KEY,
  "seller_id" uuid,
  "name" varchar(255) NOT NULL,
  "description" text,
  "category_id" uuid,
  "condition" varchar(255),
  "price" decimal(10,2),
  "status" varchar(255),
  "sportId" varchar(255),
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "product_variant" (
  "id" uuid PRIMARY KEY,
  "product_id" uuid,
  "size" varchar(255),
  "color" varchar(255),
  "price" decimal(10,2),
  "stock" int
);

CREATE TABLE "product_image" (
  "id" uuid PRIMARY KEY,
  "product_id" uuid,
  "url" text NOT NULL,
  "is_primary" boolean
);

CREATE TABLE "chat" (
  "id" uuid PRIMARY KEY,
  "created_at" timestamp
);

CREATE TABLE "chat_participant" (
  "id" uuid PRIMARY KEY,
  "chat_id" uuid,
  "user_id" uuid
);

CREATE TABLE "message" (
  "id" uuid PRIMARY KEY,
  "chat_id" uuid,
  "sender_id" uuid,
  "content" text NOT NULL,
  "sent_at" timestamp,
  "read_at" timestamp
);

CREATE TABLE "transaction" (
  "id" uuid PRIMARY KEY,
  "product_id" uuid,
  "buyer_id" uuid,
  "seller_id" uuid,
  "status" varchar(255),
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "review" (
  "id" uuid PRIMARY KEY,
  "reviewer_id" uuid,
  "reviewee_id" uuid,
  "product_id" uuid,
  "rating" int,
  "comment" text,
  "created_at" timestamp
);

CREATE TABLE "pinned_listing" (
  "id" uuid PRIMARY KEY,
  "user_id" uuid,
  "product_id" uuid
);

CREATE TABLE "dispute" (
  "id" uuid PRIMARY KEY,
  "transaction_id" uuid,
  "reported_by_id" uuid,
  "reason" text,
  "status" varchar(255),
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "dispute_message" (
  "id" uuid PRIMARY KEY,
  "dispute_id" uuid,
  "user_id" uuid,
  "content" text,
  "created_at" timestamp
);

CREATE TABLE "forum" (
  "id" uuid PRIMARY KEY,
  "title" varchar(255) NOT NULL,
  "description" text,
  "sport_id" uuid,
  "created_at" timestamp
);

CREATE TABLE "forum_post" (
  "id" uuid PRIMARY KEY,
  "forum_id" uuid,
  "user_id" uuid,
  "title" varchar(255),
  "content" text NOT NULL,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "forum_comment" (
  "id" uuid PRIMARY KEY,
  "post_id" uuid,
  "user_id" uuid,
  "content" text NOT NULL,
  "created_at" timestamp
);

CREATE TABLE "event" (
  "id" uuid PRIMARY KEY,
  "title" varchar(255) NOT NULL,
  "description" text NOT NULL,
  "location" varchar(255),
  "start_datetime" timestamp,
  "end_datetime" timestamp,
  "sport_id" uuid,
  "capacity" int,
  "created_by_id" uuid,
  "created_at" timestamp
);

CREATE TABLE "event_participant" (
  "id" uuid PRIMARY KEY,
  "event_id" uuid,
  "user_id" uuid,
  "status" varchar(255),
  "joined_at" timestamp
);

ALTER TABLE "address" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "product" ADD FOREIGN KEY ("seller_id") REFERENCES "users" ("id");

ALTER TABLE "product" ADD FOREIGN KEY ("category_id") REFERENCES "category" ("id");

ALTER TABLE "product" ADD FOREIGN KEY ("sportId") REFERENCES "sport" ("id");

ALTER TABLE "product_variant" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "product_image" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "chat_participant" ADD FOREIGN KEY ("chat_id") REFERENCES "chat" ("id");

ALTER TABLE "chat_participant" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "message" ADD FOREIGN KEY ("chat_id") REFERENCES "chat" ("id");

ALTER TABLE "message" ADD FOREIGN KEY ("sender_id") REFERENCES "users" ("id");

ALTER TABLE "transaction" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "transaction" ADD FOREIGN KEY ("buyer_id") REFERENCES "users" ("id");

ALTER TABLE "transaction" ADD FOREIGN KEY ("seller_id") REFERENCES "users" ("id");

ALTER TABLE "review" ADD FOREIGN KEY ("reviewer_id") REFERENCES "users" ("id");

ALTER TABLE "review" ADD FOREIGN KEY ("reviewee_id") REFERENCES "users" ("id");

ALTER TABLE "review" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "pinned_listing" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "pinned_listing" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "dispute" ADD FOREIGN KEY ("transaction_id") REFERENCES "transaction" ("id");

ALTER TABLE "dispute" ADD FOREIGN KEY ("reported_by_id") REFERENCES "users" ("id");

ALTER TABLE "dispute_message" ADD FOREIGN KEY ("dispute_id") REFERENCES "dispute" ("id");

ALTER TABLE "dispute_message" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "forum" ADD FOREIGN KEY ("sport_id") REFERENCES "sport" ("id");

ALTER TABLE "forum_post" ADD FOREIGN KEY ("forum_id") REFERENCES "forum" ("id");

ALTER TABLE "forum_post" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "forum_comment" ADD FOREIGN KEY ("post_id") REFERENCES "forum_post" ("id");

ALTER TABLE "forum_comment" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "event" ADD FOREIGN KEY ("sport_id") REFERENCES "sport" ("id");

ALTER TABLE "event" ADD FOREIGN KEY ("created_by_id") REFERENCES "users" ("id");

ALTER TABLE "event_participant" ADD FOREIGN KEY ("event_id") REFERENCES "event" ("id");

ALTER TABLE "event_participant" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

INSERT INTO category VALUES(gen_random_uuid(), 'Apparel'), (gen_random_uuid(), 'Accessories'), (gen_random_uuid(), 'Sports equipment')

INSERT INTO sport VALUES(gen_random_uuid(), 'football'), (gen_random_uuid(), 'basketball'), (gen_random_uuid(), 'tennis'),
						(gen_random_uuid(), 'trecking'), (gen_random_uuid(), 'swimming'), (gen_random_uuid(), 'running'),
						(gen_random_uuid(), 'volleyball'), (gen_random_uuid(), 'badminton'), (gen_random_uuid(), 'cycling'),
						(gen_random_uuid(), 'martial arts'), (gen_random_uuid(), 'other')
