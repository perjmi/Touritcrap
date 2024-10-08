use sakila;

select count(film.film_id), category.name
from category join film_category join film
on category.category_id=film_category.category_id
and film_category.film_id = film.film_id
group by category.name;


select film.title, count(*)
from category join film_category join film
on category.category_id=film_category.category_id
and film.film_id = film_category.film_id
group by film.title;
