package com.example.course.controllers;

import com.example.course.AppData;
import com.example.course.Movies;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import java.time.Year;

@Controller
public class MoviesController
{
    private Movies movies;

    private List<Movies> getList(Boolean isNew)
    {
        List<Movies> list = new ArrayList<>();
        int year = Year.now().getValue();
        for (Movies movie : AppData.moviesList) {
            if ((isNew && year - movie.getOutYear() < 3) || (!isNew && year - movie.getOutYear() > 3))
                list.add(movie);
        }
        return list;
    }

    private void openMoviePage(Model model, List<Movies> list, String text)
    {
        model.addAttribute("moviesList", list);
        model.addAttribute("text", text);
    }

    @GetMapping("/sortMovies")
    public String sortMovies(Model model)
    {
        AppData.moviesList.sort(Comparator.comparingInt(Movies::getOutYear));
        return getMovies(model);
    }

    @GetMapping("/lastMovies")
    public String lastMovies(Model model)
    {
        openMoviePage(model, getList(true), "Нові фільми");
        return "movies";
    }

    @GetMapping("/newMovies")
    public String newMovies(Model model)
    {
        openMoviePage(model, getList(false), "Фільми яким більше ніж три роки");
        return "movies";
    }

    @GetMapping("/moviesOutput")
    public String getMovies(Model model)
    {
        openMoviePage(model, AppData.moviesList, "Список фільмів");
        return "movies";
    }

    @PostMapping("/deleteMovie")
    public String deleteMovie(String id)
    {
        Optional<Movies> optionalObject = AppData.moviesList.stream()
                .filter(obj -> obj.getId().equals(id))
                .findFirst();
        optionalObject.ifPresent(movies -> AppData.moviesList.remove(movies));
        return "redirect:/";
    }

    @GetMapping("/editMovie")
    public String editMovie(String movie, Model model)
    {
        int id = AppData.findMovieIndexById(movie);
        if(id != -1) {
            currentWindowForEditOpen(model, AppData.moviesList.get(id), "Редагування", "Змінити");
            return "movies";
        }
        else
            throw new IllegalArgumentException("Element with such index not found");
    }

    @PostMapping("/saveMovie")
    public String saveMovie(
            String movieName, float rate, int outYear,
            String actor, String producer, long money,
            int watchers, String id, Model model)
    {
        int index = AppData.findMovieIndexById(id);
        movies = new Movies(id, movieName, producer, actor, outYear, watchers, rate, money);
        if(AppData.checkData(movies)) {
            if (index != -1)
                AppData.moviesList.set(index, movies);
            else
                AppData.moviesList.add(movies);
            return getMovies(model);
        }
        return "error";
    }

    @PostMapping("/addMovie")
    public String addMovie(Model model)
    {
        movies = new Movies(AppData.generateRandomId());
        currentWindowForEditOpen(model, movies, "Новий фільм", "Додати");
        return "movies";
    }

    @PostMapping("/editRate")
    public String editRate(Model model, int watchers, float rate)
    {
        if(AppData.checkRateAndWatchersQuantity(rate, watchers)) {
            for (int i = 0; i < AppData.moviesList.size(); i++) {
                if (watchers > AppData.moviesList.get(i).getWatchersQuantity())
                    AppData.moviesList.get(i).setRate(rate);
            }
            return getMovies(model);
        }
        return "error";
    }

    private void currentWindowForEditOpen(Model model, Movies objectValue, String textValue, String btnTextValue)
    {
        model.addAttribute("movie", objectValue);
        model.addAttribute("text", textValue);
        model.addAttribute("modalBtnText", btnTextValue);
    }

    @GetMapping("/findMovie")
    public String searchMovie(Model model, String checked, String searchMovie)
    {
        List<Movies> results = new ArrayList<>();
        String search = searchMovie.toLowerCase().replaceAll("\\s+", ".*");
        String fieldValue;
        for (Movies movie : AppData.moviesList) {
            if (checked.equals("Actor"))
                fieldValue = movie.getActor().toLowerCase();
            else if (checked.equals("Producer"))
                fieldValue = movie.getProducer().toLowerCase();
            else
                fieldValue = movie.getMovieName().toLowerCase();
            if (fieldValue.matches(".*" + search + ".*"))
                results.add(movie);
        }
        openMoviePage(model, results, "Результати пошуку");
        return "movies";
    }

    @PostMapping("/saveData")
    public String saveMovie(Model model) throws IOException {
        AppData.writeMoviesToFile(AppData.moviesList);
        return "home";
    }

    @PostMapping("/loadData")
    public String loadMovie(Model model) throws IOException, ClassNotFoundException {
        AppData.readMoviesFromFile();
        return "home";
    }
}