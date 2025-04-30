CREATE TABLE [User] (
  [id] uuid PRIMARY KEY,
  [email] nvarchar(255),
  [password] nvarchar(255),
  [first_name] nvarchar(255),
  [last_name] nvarchar(255),
  [birth_date] date,
  [phone] nvarchar(255),
  [gender_id] uuid,
  [role_id] uuid,
  [sport_id] uuid,
  [profile_image_url] text,
  [created_at] timestamp,
  [updated_at] timestamp
)
GO

CREATE TABLE [Role] (
  [id] uuid PRIMARY KEY,
  [code] nvarchar(255),
  [name] nvarchar(255)
)
GO

CREATE TABLE [Gender] (
  [id] uuid PRIMARY KEY,
  [code] nvarchar(255),
  [name] nvarchar(255)
)
GO

CREATE TABLE [Sport] (
  [id] uuid PRIMARY KEY,
  [name] nvarchar(255)
)
GO

CREATE TABLE [Address] (
  [id] uuid PRIMARY KEY,
  [user_id] uuid,
  [street] nvarchar(255),
  [zip_code] nvarchar(255),
  [city] nvarchar(255),
  [country] nvarchar(255),
  [created_at] timestamp,
  [updated_at] timestamp
)
GO

CREATE TABLE [Category] (
  [id] uuid PRIMARY KEY,
  [name] nvarchar(255),
  [sport_id] uuid
)
GO

CREATE TABLE [Product] (
  [id] uuid PRIMARY KEY,
  [seller_id] uuid,
  [title] nvarchar(255),
  [description] text,
  [category_id] uuid,
  [condition] nvarchar(255),
  [price] decimal,
  [status] nvarchar(255),
  [created_at] timestamp,
  [updated_at] timestamp
)
GO

CREATE TABLE [ProductVariant] (
  [id] uuid PRIMARY KEY,
  [product_id] uuid,
  [size] nvarchar(255),
  [color] nvarchar(255),
  [price] decimal,
  [stock] int
)
GO

CREATE TABLE [ProductImage] (
  [id] uuid PRIMARY KEY,
  [product_id] uuid,
  [url] text,
  [is_primary] boolean
)
GO

CREATE TABLE [Tag] (
  [id] uuid PRIMARY KEY,
  [name] nvarchar(255)
)
GO

CREATE TABLE [ProductTag] (
  [id] uuid PRIMARY KEY,
  [product_id] uuid,
  [tag_id] uuid
)
GO

CREATE TABLE [Chat] (
  [id] uuid PRIMARY KEY,
  [created_at] timestamp
)
GO

CREATE TABLE [ChatParticipant] (
  [id] uuid PRIMARY KEY,
  [chat_id] uuid,
  [user_id] uuid
)
GO

CREATE TABLE [Message] (
  [id] uuid PRIMARY KEY,
  [chat_id] uuid,
  [sender_id] uuid,
  [content] text,
  [sent_at] timestamp,
  [read_at] timestamp
)
GO

CREATE TABLE [Transaction] (
  [id] uuid PRIMARY KEY,
  [product_id] uuid,
  [buyer_id] uuid,
  [seller_id] uuid,
  [status] nvarchar(255),
  [payment_method_id] uuid,
  [shipping_method_id] uuid,
  [created_at] timestamp,
  [updated_at] timestamp
)
GO

CREATE TABLE [Review] (
  [id] uuid PRIMARY KEY,
  [transaction_id] uuid,
  [reviewer_id] uuid,
  [reviewee_id] uuid,
  [product_id] uuid,
  [rating] int,
  [comment] text,
  [created_at] timestamp
)
GO

CREATE TABLE [PaymentMethod] (
  [id] uuid PRIMARY KEY,
  [name] nvarchar(255)
)
GO

CREATE TABLE [ShippingMethod] (
  [id] uuid PRIMARY KEY,
  [name] nvarchar(255),
  [estimated_days] int,
  [cost] decimal
)
GO

CREATE TABLE [Cart] (
  [id] uuid PRIMARY KEY,
  [user_id] uuid,
  [created_at] timestamp
)
GO

CREATE TABLE [CartItem] (
  [id] uuid PRIMARY KEY,
  [cart_id] uuid,
  [product_variant_id] uuid,
  [quantity] int
)
GO

CREATE TABLE [Wishlist] (
  [id] uuid PRIMARY KEY,
  [user_id] uuid,
  [product_id] uuid
)
GO

CREATE TABLE [Dispute] (
  [id] uuid PRIMARY KEY,
  [transaction_id] uuid,
  [reported_by_id] uuid,
  [reason] text,
  [status] nvarchar(255),
  [created_at] timestamp,
  [updated_at] timestamp
)
GO

CREATE TABLE [DisputeMessage] (
  [id] uuid PRIMARY KEY,
  [dispute_id] uuid,
  [user_id] uuid,
  [content] text,
  [created_at] timestamp
)
GO

CREATE TABLE [Forum] (
  [id] uuid PRIMARY KEY,
  [title] nvarchar(255),
  [description] text,
  [sport_id] uuid,
  [created_at] timestamp
)
GO

CREATE TABLE [ForumPost] (
  [id] uuid PRIMARY KEY,
  [forum_id] uuid,
  [user_id] uuid,
  [title] nvarchar(255),
  [content] text,
  [created_at] timestamp,
  [updated_at] timestamp
)
GO

CREATE TABLE [ForumComment] (
  [id] uuid PRIMARY KEY,
  [post_id] uuid,
  [user_id] uuid,
  [content] text,
  [created_at] timestamp
)
GO

CREATE TABLE [Event] (
  [id] uuid PRIMARY KEY,
  [title] nvarchar(255),
  [description] text,
  [location] nvarchar(255),
  [start_datetime] timestamp,
  [end_datetime] timestamp,
  [sport_id] uuid,
  [created_by_id] uuid,
  [created_at] timestamp
)
GO

CREATE TABLE [EventParticipant] (
  [id] uuid PRIMARY KEY,
  [event_id] uuid,
  [user_id] uuid,
  [status] nvarchar(255)
)
GO

CREATE TABLE [EquipmentGuide] (
  [id] uuid PRIMARY KEY,
  [title] nvarchar(255),
  [content] text,
  [sport_id] uuid,
  [user_id] uuid,
  [created_at] timestamp
)
GO

ALTER TABLE [User] ADD FOREIGN KEY ([gender_id]) REFERENCES [Gender] ([id])
GO

ALTER TABLE [User] ADD FOREIGN KEY ([role_id]) REFERENCES [Role] ([id])
GO

ALTER TABLE [User] ADD FOREIGN KEY ([sport_id]) REFERENCES [Sport] ([id])
GO

ALTER TABLE [Address] ADD FOREIGN KEY ([user_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [Category] ADD FOREIGN KEY ([sport_id]) REFERENCES [Sport] ([id])
GO

ALTER TABLE [Product] ADD FOREIGN KEY ([seller_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [Product] ADD FOREIGN KEY ([category_id]) REFERENCES [Category] ([id])
GO

ALTER TABLE [ProductVariant] ADD FOREIGN KEY ([product_id]) REFERENCES [Product] ([id])
GO

ALTER TABLE [ProductImage] ADD FOREIGN KEY ([product_id]) REFERENCES [Product] ([id])
GO

ALTER TABLE [ProductTag] ADD FOREIGN KEY ([product_id]) REFERENCES [Product] ([id])
GO

ALTER TABLE [ProductTag] ADD FOREIGN KEY ([tag_id]) REFERENCES [Tag] ([id])
GO

ALTER TABLE [ChatParticipant] ADD FOREIGN KEY ([chat_id]) REFERENCES [Chat] ([id])
GO

ALTER TABLE [ChatParticipant] ADD FOREIGN KEY ([user_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [Message] ADD FOREIGN KEY ([chat_id]) REFERENCES [Chat] ([id])
GO

ALTER TABLE [Message] ADD FOREIGN KEY ([sender_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [Transaction] ADD FOREIGN KEY ([product_id]) REFERENCES [Product] ([id])
GO

ALTER TABLE [Transaction] ADD FOREIGN KEY ([buyer_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [Transaction] ADD FOREIGN KEY ([seller_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [Transaction] ADD FOREIGN KEY ([payment_method_id]) REFERENCES [PaymentMethod] ([id])
GO

ALTER TABLE [Transaction] ADD FOREIGN KEY ([shipping_method_id]) REFERENCES [ShippingMethod] ([id])
GO

ALTER TABLE [Review] ADD FOREIGN KEY ([transaction_id]) REFERENCES [Transaction] ([id])
GO

ALTER TABLE [Review] ADD FOREIGN KEY ([reviewer_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [Review] ADD FOREIGN KEY ([reviewee_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [Review] ADD FOREIGN KEY ([product_id]) REFERENCES [Product] ([id])
GO

ALTER TABLE [Cart] ADD FOREIGN KEY ([user_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [CartItem] ADD FOREIGN KEY ([cart_id]) REFERENCES [Cart] ([id])
GO

ALTER TABLE [CartItem] ADD FOREIGN KEY ([product_variant_id]) REFERENCES [ProductVariant] ([id])
GO

ALTER TABLE [Wishlist] ADD FOREIGN KEY ([user_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [Wishlist] ADD FOREIGN KEY ([product_id]) REFERENCES [Product] ([id])
GO

ALTER TABLE [Dispute] ADD FOREIGN KEY ([transaction_id]) REFERENCES [Transaction] ([id])
GO

ALTER TABLE [Dispute] ADD FOREIGN KEY ([reported_by_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [DisputeMessage] ADD FOREIGN KEY ([dispute_id]) REFERENCES [Dispute] ([id])
GO

ALTER TABLE [DisputeMessage] ADD FOREIGN KEY ([user_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [Forum] ADD FOREIGN KEY ([sport_id]) REFERENCES [Sport] ([id])
GO

ALTER TABLE [ForumPost] ADD FOREIGN KEY ([forum_id]) REFERENCES [Forum] ([id])
GO

ALTER TABLE [ForumPost] ADD FOREIGN KEY ([user_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [ForumComment] ADD FOREIGN KEY ([post_id]) REFERENCES [ForumPost] ([id])
GO

ALTER TABLE [ForumComment] ADD FOREIGN KEY ([user_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [Event] ADD FOREIGN KEY ([sport_id]) REFERENCES [Sport] ([id])
GO

ALTER TABLE [Event] ADD FOREIGN KEY ([created_by_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [EventParticipant] ADD FOREIGN KEY ([event_id]) REFERENCES [Event] ([id])
GO

ALTER TABLE [EventParticipant] ADD FOREIGN KEY ([user_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [EquipmentGuide] ADD FOREIGN KEY ([sport_id]) REFERENCES [Sport] ([id])
GO

ALTER TABLE [EquipmentGuide] ADD FOREIGN KEY ([user_id]) REFERENCES [User] ([id])
GO
