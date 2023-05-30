package com.example.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class CourseApplication
{
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		createMoviesData();
		SpringApplication.run(CourseApplication.class, args);
	}
	private static void createMoviesData() throws IOException, ClassNotFoundException {
		AppData.readMoviesFromFile();
		if(AppData.moviesList == null || AppData.moviesList.isEmpty()) {
			AppData.moviesList = new ArrayList<>();
			AppData.moviesList.add(new Movies(AppData.generateRandomId(), "Холодна гонка", "Саід Туляганов", "Володимір Петренко", 2022, 14000000, 7.1F, 12200000));
			AppData.moviesList.add(new Movies(AppData.generateRandomId(), "Ренфілд", "Кріс МакКей", "Ніколас Кейдж", 2023, 220000000, 6.6F, 1100000000));
			AppData.moviesList.add(new Movies(AppData.generateRandomId(), "Перекладач", "Гай Річі", "Шон Сагар", 2023, 170000000, 8.2F, 20020000));
			AppData.moviesList.add(new Movies(AppData.generateRandomId(), "Я тебе кохаю", "Ернар Нургалієв", "Сара Амангельди", 2019, 4000000, 5.8F, 100000000));
			AppData.moviesList.add(new Movies(AppData.generateRandomId(), "Останній  хлопчик", "Перрі Бандал", "Люк Госс", 2019, 320000000, 7.4F, 120000000));
			AppData.moviesList.add(new Movies(AppData.generateRandomId(), "Добрий доктор", "Майк Листо", "Стівен ДеПол", 2017, 440000000, 5.8F, 700000000));
			AppData.moviesList.add(new Movies(AppData.generateRandomId(), "Снігопад", "Деніел Еттіес", "Майкл Леман", 2017, 290000000, 8.1F, 140000000));
			AppData.moviesList.add(new Movies(AppData.generateRandomId(), "Серце дракона 4", "Патрік Сіверсен", "Бліс Бел", 2017, 620000000, 5.4F, 150000000));
			AppData.moviesList.add(new Movies(AppData.generateRandomId(), "Асстронавти", "Дін Ізраєлайт", "Джонатан Фрейкс", 2020, 940000000, 7.1F, 190000000));
			AppData.moviesList.add(new Movies(AppData.generateRandomId(), "Стилістка", "Джилл Геваргізян", "Неджаппа Таусенд", 2020, 1140000000, 8.2F, 520000000));
		}
	}
}
