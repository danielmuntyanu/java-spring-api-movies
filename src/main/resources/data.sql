-- years table
INSERT INTO years (id) VALUES (2002);
INSERT INTO years (id) VALUES (2004);
INSERT INTO years (id) VALUES (2007);
INSERT INTO years (id) VALUES (2012);
INSERT INTO years (id) VALUES (2014);
INSERT INTO years (id) VALUES (2017);
INSERT INTO years (id) VALUES (2019);
INSERT INTO years (id) VALUES (2021);
INSERT INTO years (id) VALUES (2026);

-- genres table
INSERT INTO genres (id, name) VALUES (default, 'Action');
INSERT INTO genres (id, name) VALUES (default, 'Adventure');
INSERT INTO genres (id, name) VALUES (default, 'Sci-Fi');
INSERT INTO genres (id, name) VALUES (default, 'Fantasy');

-- actors table
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Tobey', 'Maguire'); -- 1
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Kirsten', 'Dunst'); -- 2
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'James', 'Franco'); -- 3
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Willem', 'Dafoe'); -- 4
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Alfred', 'Molina'); -- 5
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Topher', 'Grace'); -- 6

INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Andrew', 'Garfield'); -- 7
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Emma', 'Stone'); -- 8
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Rhys', 'Ifans'); -- 9
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Jamie', 'Foxx'); -- 10

INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Robert', 'Downey Jr.'); -- 11
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Tom', 'Holland'); -- 12
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Zendaya', ''); -- 13
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Jake', 'Gyllenhaal'); -- 14
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Michael', 'Keaton'); -- 15
INSERT INTO actors (id, first_name, last_name) VALUES (default, 'Benedict', 'Cumberbatch'); -- 16

-- movies table
INSERT INTO movies (id, title, release_year) VALUES (default, 'Spider-Man', 2002); -- 1
INSERT INTO movies (id, title, release_year) VALUES (default, 'Spider-Man 2', 2004); -- 2
INSERT INTO movies (id, title, release_year) VALUES (default, 'Spider-Man 3', 2007); -- 3
INSERT INTO movies (id, title, release_year) VALUES (default, 'The Amazing Spider-Man', 2012); -- 4
INSERT INTO movies (id, title, release_year) VALUES (default, 'The Amazing Spider-Man 2', 2014); -- 5
INSERT INTO movies (id, title, release_year) VALUES (default, 'Spider-Man: Homecoming', 2017); -- 6
INSERT INTO movies (id, title, release_year) VALUES (default, 'Spider-Man Far from Home', 2019); -- 7
INSERT INTO movies (id, title, release_year) VALUES (default, 'Spider-Man: No Way Home', 2021); -- 8
INSERT INTO movies (id, title, release_year) VALUES (default, 'Spider-Man: Brand New Day', 2026); -- 9

-- movies_genres table
INSERT INTO movies_genres (movie_id, genre_id) VALUES (1, 1);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (1, 2);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (1, 3);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (2, 1);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (2, 2);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (2, 3);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (3, 1);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (3, 2);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (3, 3);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (4, 1);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (4, 2);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (4, 3);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (5, 1);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (5, 2);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (5, 3);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (6, 1);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (6, 2);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (6, 3);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (7, 1);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (7, 2);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (7, 3);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (8, 1);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (8, 2);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (8, 3);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (9, 1);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (9, 2);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (9, 3);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (9, 4);

-- movies_actors table
INSERT INTO movies_actors (movie_id, actor_id) VALUES (1, 1); -- Tobey Maguire
INSERT INTO movies_actors (movie_id, actor_id) VALUES (2, 1);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (3, 1);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (8, 1);

INSERT INTO movies_actors (movie_id, actor_id) VALUES (1, 2); -- Kirsten Dunst
INSERT INTO movies_actors (movie_id, actor_id) VALUES (2, 2); 
INSERT INTO movies_actors (movie_id, actor_id) VALUES (3, 2); 

INSERT INTO movies_actors (movie_id, actor_id) VALUES (1, 3); -- James Franco
INSERT INTO movies_actors (movie_id, actor_id) VALUES (2, 3); 
INSERT INTO movies_actors (movie_id, actor_id) VALUES (3, 3); 

INSERT INTO movies_actors (movie_id, actor_id) VALUES (1, 4); -- Willem Defoe 
INSERT INTO movies_actors (movie_id, actor_id) VALUES (8, 4); 

INSERT INTO movies_actors (movie_id, actor_id) VALUES (2, 5); -- Alfred Molina
INSERT INTO movies_actors (movie_id, actor_id) VALUES (8, 5); 

INSERT INTO movies_actors (movie_id, actor_id) VALUES (3, 6); -- Topher Grace

INSERT INTO movies_actors (movie_id, actor_id) VALUES (4, 7); -- Andrew Garfield
INSERT INTO movies_actors (movie_id, actor_id) VALUES (5, 7);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (8, 7);

INSERT INTO movies_actors (movie_id, actor_id) VALUES (4, 8); -- Emma Stone
INSERT INTO movies_actors (movie_id, actor_id) VALUES (5, 8);

INSERT INTO movies_actors (movie_id, actor_id) VALUES (4, 9); -- Rhys Ifans
INSERT INTO movies_actors (movie_id, actor_id) VALUES (8, 9);

INSERT INTO movies_actors (movie_id, actor_id) VALUES (5, 10); -- Jamie", "Foxx
INSERT INTO movies_actors (movie_id, actor_id) VALUES (8, 10);

INSERT INTO movies_actors (movie_id, actor_id) VALUES (6, 11); -- Robert Downey Jr.

INSERT INTO movies_actors (movie_id, actor_id) VALUES (6, 12); -- Tom Holland
INSERT INTO movies_actors (movie_id, actor_id) VALUES (7, 12);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (8, 12);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (9, 12);

INSERT INTO movies_actors (movie_id, actor_id) VALUES (6, 13); -- Zendaya
INSERT INTO movies_actors (movie_id, actor_id) VALUES (7, 13);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (8, 13);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (9, 13);

INSERT INTO movies_actors (movie_id, actor_id) VALUES (6, 14); -- Jake Gyllenhaal

INSERT INTO movies_actors (movie_id, actor_id) VALUES (7, 15); -- Michael Keaton

INSERT INTO movies_actors (movie_id, actor_id) VALUES (8, 16); -- Benedict Cumberbatch









