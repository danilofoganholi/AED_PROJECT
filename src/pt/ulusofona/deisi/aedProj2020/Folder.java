package pt.ulusofona.deisi.aedProj2020;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Folder {
    LinkedHashMap<Integer,Movie> dicionarioMovies = new LinkedHashMap<>();
    ArrayList<Actor> listActors = new ArrayList<>();
    ArrayList<Realizador> listDirectors = new ArrayList<>();
    HashMap<Integer,String> dicionarioGenres = new HashMap<>();
    HashMap<Integer, Integer> dicionarioGenresMovie = new HashMap<>();
    HashMap<Integer, Vote> dicionarioMovieVotes = new HashMap<>();

    public Folder(LinkedHashMap<Integer, Movie> dicionarioMovies, ArrayList<Actor> listActors,
                  ArrayList<Realizador> listDirectors, HashMap<Integer, String> dicionarioGenres,
                  HashMap<Integer, Integer> dicionarioGenresMovie, HashMap<Integer, Vote> dicionarioMovieVotes){
        this.dicionarioMovies = dicionarioMovies;
        this.listActors = listActors;
        this.listDirectors = listDirectors;
        this.dicionarioGenres = dicionarioGenres;
        this.dicionarioGenresMovie = dicionarioGenresMovie;
        this.dicionarioMovieVotes = dicionarioMovieVotes;
    }
}
