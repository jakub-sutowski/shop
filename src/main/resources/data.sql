INSERT INTO Category (id, name) VALUES
                                    (UUID_GENERATE_V4(), 'Electronics'),
                                    (UUID_GENERATE_V4(), 'Clothing'),
                                    (UUID_GENERATE_V4(), 'Home and Kitchen'),
                                    (UUID_GENERATE_V4(), 'Books'),
                                    (UUID_GENERATE_V4(), 'Sports Gear'),
                                    (UUID_GENERATE_V4(), 'Toys'),
                                    (UUID_GENERATE_V4(), 'Beauty and Personal Care'),
                                    (UUID_GENERATE_V4(), 'Furniture'),
                                    (UUID_GENERATE_V4(), 'Jewelry'),
                                    (UUID_GENERATE_V4(), 'Automotive');

-- Electronics
INSERT INTO Product (id, product_code, name, price, description, category_id) VALUES
                                                                                 (UUID_GENERATE_V4(), 1234567890, 'Smartphone X', 599.99, 'High-performance smartphone', (SELECT id FROM Category WHERE name = 'Electronics')),
                                                                                 (UUID_GENERATE_V4(), 9876543210, 'Smartwatch Pro', 129.99, 'Advanced smartwatch with health features', (SELECT id FROM Category WHERE name = 'Electronics')),
                                                                                 (UUID_GENERATE_V4(), 3456789012, 'Wireless Earbuds', 79.99, 'Bluetooth earbuds with noise cancellation', (SELECT id FROM Category WHERE name = 'Electronics')),
                                                                                 (UUID_GENERATE_V4(), 5678901234, '4K UHD TV', 899.99, 'Immersive viewing experience with 4K resolution', (SELECT id FROM Category WHERE name = 'Electronics')),
                                                                                 (UUID_GENERATE_V4(), 9012345678, 'Gaming Laptop', 1499.99, 'Powerful laptop for gaming enthusiasts', (SELECT id FROM Category WHERE name = 'Electronics')),

-- Clothing
                                                                                 (UUID_GENERATE_V4(), 1122334455, 'Men Jeans', 49.99, 'Classic denim jeans for men', (SELECT id FROM Category WHERE name = 'Clothing')),
                                                                                 (UUID_GENERATE_V4(), 7890123456, 'Women Dress', 79.99, 'Elegant evening dress for women', (SELECT id FROM Category WHERE name = 'Clothing')),
                                                                                 (UUID_GENERATE_V4(), 4567890123, 'Running Shoes', 89.99, 'Lightweight running shoes for athletes', (SELECT id FROM Category WHERE name = 'Clothing')),
                                                                                 (UUID_GENERATE_V4(), 6789012345, 'T-shirt Pack', 29.99, 'Pack of comfortable cotton t-shirts', (SELECT id FROM Category WHERE name = 'Clothing')),
                                                                                 (UUID_GENERATE_V4(), 8901234567, 'Winter Jacket', 119.99, 'Warm and stylish winter jacket', (SELECT id FROM Category WHERE name = 'Clothing')),

-- Home and Kitchen
                                                                                 (UUID_GENERATE_V4(), 1234567890, 'Coffee Machine', 89.99, 'Automatic coffee machine for home', (SELECT id FROM Category WHERE name = 'Home and Kitchen')),
                                                                                 (UUID_GENERATE_V4(), 9012345678, 'Air Fryer', 69.99, 'Healthy cooking with air frying technology', (SELECT id FROM Category WHERE name = 'Home and Kitchen')),
                                                                                 (UUID_GENERATE_V4(), 6789012345, 'Bedding Set', 149.99, 'Luxurious bedding set for a comfortable sleep', (SELECT id FROM Category WHERE name = 'Home and Kitchen')),
                                                                                 (UUID_GENERATE_V4(), 3456789012, 'Blender', 49.99, 'Versatile blender for smoothies and more', (SELECT id FROM Category WHERE name = 'Home and Kitchen')),
                                                                                 (UUID_GENERATE_V4(), 5678901234, 'Dining Table', 299.99, 'Stylish dining table for your home', (SELECT id FROM Category WHERE name = 'Home and Kitchen')),

-- Books
                                                                                 (UUID_GENERATE_V4(), 9012345678, 'Sci-Fi Novel', 19.99, 'Best-selling science fiction novel', (SELECT id FROM Category WHERE name = 'Books')),
                                                                                 (UUID_GENERATE_V4(), 6789012345, 'Mystery Thriller', 24.99, 'Intriguing mystery thriller book', (SELECT id FROM Category WHERE name = 'Books')),
                                                                                 (UUID_GENERATE_V4(), 5678901234, 'Self-Help Guide', 14.99, 'Motivational self-help guide for personal growth', (SELECT id FROM Category WHERE name = 'Books')),
                                                                                 (UUID_GENERATE_V4(), 9012345678, 'History Book', 29.99, 'Insightful exploration of historical events', (SELECT id FROM Category WHERE name = 'Books')),
                                                                                 (UUID_GENERATE_V4(), 6789012345, 'Cookbook', 29.99, 'Delicious recipes for every occasion', (SELECT id FROM Category WHERE name = 'Books')),

-- Sports Gear
                                                                                 (UUID_GENERATE_V4(), 5678901234, 'Basketball', 19.99, 'Durable basketball for indoor and outdoor play', (SELECT id FROM Category WHERE name = 'Sports Gear')),
                                                                                 (UUID_GENERATE_V4(), 9012345678, 'Yoga Mat', 14.99, 'Non-slip yoga mat for comfortable workouts', (SELECT id FROM Category WHERE name = 'Sports Gear')),
                                                                                 (UUID_GENERATE_V4(), 6789012345, 'Dumbbell Set', 49.99, 'Adjustable dumbbell set for strength training', (SELECT id FROM Category WHERE name = 'Sports Gear')),
                                                                                 (UUID_GENERATE_V4(), 3456789012, 'Cycling Helmet', 34.99, 'Safety first with a quality cycling helmet', (SELECT id FROM Category WHERE name = 'Sports Gear')),
                                                                                 (UUID_GENERATE_V4(), 5678901234, 'Running Shoes', 79.99, 'Lightweight running shoes for athletes', (SELECT id FROM Category WHERE name = 'Sports Gear')),

-- Toys
                                                                                 (UUID_GENERATE_V4(), 1234567890, 'Building Blocks Set', 34.99, 'Colorful building blocks for creative play', (SELECT id FROM Category WHERE name = 'Toys')),
                                                                                 (UUID_GENERATE_V4(), 5678901234, 'Remote Control Car', 24.99, 'Fun remote control car for kids', (SELECT id FROM Category WHERE name = 'Toys')),
                                                                                 (UUID_GENERATE_V4(), 9012345678, 'Dollhouse Kit', 69.99, 'Miniature dollhouse kit for kids', (SELECT id FROM Category WHERE name = 'Toys')),
                                                                                 (UUID_GENERATE_V4(), 6789012345, 'Stuffed Animal Set', 19.99, 'Adorable stuffed animals for playtime', (SELECT id FROM Category WHERE name = 'Toys')),
                                                                                 (UUID_GENERATE_V4(), 3456789012, 'Board Game', 39.99, 'Classic board game for family fun', (SELECT id FROM Category WHERE name = 'Toys')),

-- Beauty and Personal Care
                                                                                 (UUID_GENERATE_V4(), 9012345678, 'Skincare Gift Set', 54.99, 'Luxurious skincare products in a gift set', (SELECT id FROM Category WHERE name = 'Beauty and Personal Care')),
                                                                                 (UUID_GENERATE_V4(), 6789012345, 'Perfume Set', 39.99, 'Fragrance gift set for a delightful experience', (SELECT id FROM Category WHERE name = 'Beauty and Personal Care')),
                                                                                 (UUID_GENERATE_V4(), 5678901234, 'Hair Dryer', 29.99, 'Powerful hair dryer for quick styling', (SELECT id FROM Category WHERE name = 'Beauty and Personal Care')),
                                                                                 (UUID_GENERATE_V4(), 9012345678, 'Men Grooming Kit', 39.99, 'Essential grooming products for men', (SELECT id FROM Category WHERE name = 'Beauty and Personal Care')),
                                                                                 (UUID_GENERATE_V4(), 6789012345, 'Makeup Set', 49.99, 'Complete makeup set for a glamorous look', (SELECT id FROM Category WHERE name = 'Beauty and Personal Care')),

-- Furniture
                                                                                 (UUID_GENERATE_V4(), 5678901234, 'Modern Coffee Table', 119.99, 'Sleek and stylish coffee table for the living room', (SELECT id FROM Category WHERE name = 'Furniture')),
                                                                                 (UUID_GENERATE_V4(), 9012345678, 'Office Chair', 79.99, 'Comfortable office chair for long hours of work', (SELECT id FROM Category WHERE name = 'Furniture')),
                                                                                 (UUID_GENERATE_V4(), 6789012345, 'Bed Frame', 249.99, 'Sturdy bed frame for a restful sleep', (SELECT id FROM Category WHERE name = 'Furniture')),
                                                                                 (UUID_GENERATE_V4(), 3456789012, 'Wardrobe', 199.99, 'Spacious wardrobe for organized storage', (SELECT id FROM Category WHERE name = 'Furniture')),
                                                                                 (UUID_GENERATE_V4(), 5678901234, 'Bookshelf', 99.99, 'Functional bookshelf for displaying books and decor', (SELECT id FROM Category WHERE name = 'Furniture')),

-- Jewelry
                                                                                 (UUID_GENERATE_V4(), 9012345678, 'Diamond Stud Earrings', 499.99, 'Classic diamond stud earrings', (SELECT id FROM Category WHERE name = 'Jewelry')),
                                                                                 (UUID_GENERATE_V4(), 6789012345, 'Silver Necklace', 89.99, 'Elegant silver necklace for a timeless look', (SELECT id FROM Category WHERE name = 'Jewelry')),
                                                                                 (UUID_GENERATE_V4(), 5678901234, 'Gold Bracelet', 249.99, 'Elegant gold bracelet for special occasions', (SELECT id FROM Category WHERE name = 'Jewelry')),
                                                                                 (UUID_GENERATE_V4(), 9012345678, 'Gemstone Ring', 179.99, 'Sparkling gemstone ring for a touch of glamour', (SELECT id FROM Category WHERE name = 'Jewelry')),
                                                                                 (UUID_GENERATE_V4(), 6789012345, 'Pearl Earrings', 119.99, 'Timeless pearl earrings for a classic style', (SELECT id FROM Category WHERE name = 'Jewelry')),

-- Automotive
                                                                                 (UUID_GENERATE_V4(), 5678901234, 'Car Seat Covers', 29.99, 'Durable and stylish car seat covers', (SELECT id FROM Category WHERE name = 'Automotive')),
                                                                                 (UUID_GENERATE_V4(), 9012345678, 'Car Phone Mount', 14.99, 'Secure phone mount for your car', (SELECT id FROM Category WHERE name = 'Automotive')),
                                                                                 (UUID_GENERATE_V4(), 6789012345, 'Car Cleaning Kit', 39.99, 'Complete kit for keeping your car clean', (SELECT id FROM Category WHERE name = 'Automotive')),
                                                                                 (UUID_GENERATE_V4(), 3456789012, 'Jump Starter', 49.99, 'Portable jump starter for your car', (SELECT id FROM Category WHERE name = 'Automotive')),
                                                                                 (UUID_GENERATE_V4(), 5678901234, 'Car Air Freshener', 9.99, 'Refreshing air freshener for your car', (SELECT id FROM Category WHERE name = 'Automotive'));
