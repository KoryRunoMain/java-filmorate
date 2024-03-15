CREATE TABLE IF NOT EXISTS films (
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	mpa_rating_id INTEGER, -- FK (table -> mpa_ratings, field -> id)
	name VARCHAR(100) NOT NULL,
	description VARCHAR(255) DEFAULT '',
	releaseDate DATE NOT NULL,
	duration INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS film_genre (
	film_id INTEGER, -- FK (table -> films, field -> id)
	genre_id INTEGER, -- FK (table -> genres, field -> id)
	CONSTRAINT film_genre_id PRIMARY KEY (film_id, genre_id)
);

CREATE TABLE IF NOT EXISTS genres (
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS mpa_ratings (
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users (
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	email VARCHAR(255) NOT NULL UNIQUE,
	login VARCHAR(100) NOT NULL UNIQUE,
	name VARCHAR(50),
	birthday DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS friends (
	user_id INTEGER, -- FK (table -> users, field -> id)
	friend_id INTEGER, -- FK (table -> users, field -> id)
	status BOOLEAN DEFAULT false,
	CONSTRAINT friends_id PRIMARY KEY (user_id, friend_id)
);

CREATE TABLE IF NOT EXISTS likes (
	film_id INTEGER, -- FK (table -> films, field -> id)
	user_id INTEGER, -- FK (table -> users, field -> id)
	CONSTRAINT likes_id PRIMARY KEY (film_id, user_id)
);

ALTER TABLE films ADD FOREIGN KEY (mpa_rating_id) REFERENCES mpa_ratings (id) ON DELETE SET NULL;
ALTER TABLE film_genre ADD FOREIGN KEY (film_id) REFERENCES films (id) ON DELETE CASCADE;
ALTER TABLE film_genre ADD FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE CASCADE;
ALTER TABLE friends ADD FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;
ALTER TABLE friends ADD FOREIGN KEY (friend_id) REFERENCES users (id) ON DELETE CASCADE;
ALTER TABLE likes ADD FOREIGN KEY (film_id) REFERENCES films (id) ON DELETE CASCADE;
ALTER TABLE likes ADD FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;
