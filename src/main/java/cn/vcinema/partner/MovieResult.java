package cn.vcinema.partner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: songlijun
 * Date: 2018/1/29
 * Time: 下午8:48
 * To change this template use File | Settings | File Templates.
 */
public class MovieResult {

    private Integer total_movie_number;

    private String data_generate_time;

    private String data_generate_timestamp;

    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Integer getTotal_movie_number() {
        return total_movie_number;
    }

    public void setTotal_movie_number(Integer total_movie_number) {
        this.total_movie_number = total_movie_number;
    }


    public String getData_generate_time() {
        return data_generate_time;
    }

    public void setData_generate_time(String data_generate_time) {
        this.data_generate_time = data_generate_time;
    }

    public String getData_generate_timestamp() {
        return data_generate_timestamp;
    }

    public void setData_generate_timestamp(String data_generate_timestamp) {
        this.data_generate_timestamp = data_generate_timestamp;
    }
}
