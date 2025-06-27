INSERT INTO users (id, email, password, first_name, last_name)
VALUES ('00000000-0000-0000-0000-000000000000', 'deleted@user.com', '', 'Deleted', 'User')
ON CONFLICT (id) DO NOTHING;

INSERT INTO category VALUES(gen_random_uuid(), 'Apparel'), (gen_random_uuid(), 'Accessories'), (gen_random_uuid(), 'Sports equipment');

INSERT INTO sport VALUES(gen_random_uuid(), 'football'), (gen_random_uuid(), 'basketball'), (gen_random_uuid(), 'tennis'),
                        (gen_random_uuid(), 'trecking'), (gen_random_uuid(), 'swimming'), (gen_random_uuid(), 'running'),
                        (gen_random_uuid(), 'volleyball'), (gen_random_uuid(), 'badminton'), (gen_random_uuid(), 'cycling'),
                        (gen_random_uuid(), 'martial arts'), (gen_random_uuid(), 'other')
