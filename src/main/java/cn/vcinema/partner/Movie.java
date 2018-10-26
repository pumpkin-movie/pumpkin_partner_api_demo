package cn.vcinema.partner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;
import java.util.Objects;

/**
 * 影片
 */
//@ApiModel(description = "影片")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-09T16:18:44.630+08:00")

public class Movie {
    @JsonProperty("movie_id")
    private String movieId = null;

    @JsonProperty("movie_title")
    private String movieTitle = null;

    @JsonProperty("movie_name")
    private String movieName = null;

    @JsonProperty("movie_name_english")
    private String movieNameEnglish = null;

    @JsonProperty("movie_alias")
    private String movieAlias = null;

    @JsonProperty("movie_desc")
    private String movieDesc = null;

    @JsonProperty("movie_director")
    private String movieDirector = null;

    @JsonProperty("movie_actor")
    private String movieActor = null;

    @JsonProperty("movie_country")
    private String movieCountry = null;

    @JsonProperty("movie_language")
    private String movieLanguage = null;

    @JsonProperty("movie_year")
    private String movieYear = null;

    private String movieCreateTime;

    public String getMovieCreateTime() {
        return movieCreateTime;
    }

    public void setMovieCreateTime(String movieCreateTime) {
        this.movieCreateTime = movieCreateTime;
    }

    /**
     * 影片类型
     */
    public enum MovieTypeEnum {
        MOVIE("MOVIE"),

        SERIES("SERIES");

        private String value;

        MovieTypeEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static MovieTypeEnum fromValue(String text) {
            for (MovieTypeEnum b : MovieTypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("movie_type")
    private String movieType = null;

    @JsonProperty("movie_poster")
    private String moviePoster = null;

    @JsonProperty("movie_duration")
    private Integer movieDuration = null;

    @JsonProperty("episode_num")
    private Integer episodeNum = null;

    @JsonProperty("movie_image_list")
    private List<String> movieImages;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieNameEnglish() {
        return movieNameEnglish;
    }

    public void setMovieNameEnglish(String movieNameEnglish) {
        this.movieNameEnglish = movieNameEnglish;
    }

    public String getMovieAlias() {
        return movieAlias;
    }

    public void setMovieAlias(String movieAlias) {
        this.movieAlias = movieAlias;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(String movieActor) {
        this.movieActor = movieActor;
    }

    public String getMovieCountry() {
        return movieCountry;
    }

    public void setMovieCountry(String movieCountry) {
        this.movieCountry = movieCountry;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public Integer getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(Integer movieDuration) {
        this.movieDuration = movieDuration;
    }

    public Integer getEpisodeNum() {
        return episodeNum;
    }

    public void setEpisodeNum(Integer episodeNum) {
        this.episodeNum = episodeNum;
    }

    public List<String> getMovieImages() {
        return movieImages;
    }

    public void setMovieImages(List<String> movieImages) {
        this.movieImages = movieImages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return Objects.equals(this.movieId, movie.movieId) &&
                Objects.equals(this.movieTitle, movie.movieTitle) &&
                Objects.equals(this.movieName, movie.movieName) &&
                Objects.equals(this.movieNameEnglish, movie.movieNameEnglish) &&
                Objects.equals(this.movieAlias, movie.movieAlias) &&
                Objects.equals(this.movieDesc, movie.movieDesc) &&
                Objects.equals(this.movieDirector, movie.movieDirector) &&
                Objects.equals(this.movieActor, movie.movieActor) &&
                Objects.equals(this.movieCountry, movie.movieCountry) &&
                Objects.equals(this.movieLanguage, movie.movieLanguage) &&
                Objects.equals(this.movieYear, movie.movieYear) &&
                Objects.equals(this.movieType, movie.movieType) &&
                Objects.equals(this.moviePoster, movie.moviePoster) &&
                Objects.equals(this.movieDuration, movie.movieDuration) &&
                Objects.equals(this.episodeNum, movie.episodeNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieTitle, movieName, movieNameEnglish, movieAlias, movieDesc, movieDirector, movieActor, movieCountry, movieLanguage, movieYear, movieType, moviePoster, movieDuration, episodeNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Movie {\n");

        sb.append("    id: ").append(toIndentedString(movieId)).append("\n");
        sb.append("    movieTitle: ").append(toIndentedString(movieTitle)).append("\n");
        sb.append("    movieName: ").append(toIndentedString(movieName)).append("\n");
        sb.append("    movieNameEnglish: ").append(toIndentedString(movieNameEnglish)).append("\n");
        sb.append("    movieAlias: ").append(toIndentedString(movieAlias)).append("\n");
        sb.append("    movieDesc: ").append(toIndentedString(movieDesc)).append("\n");
        sb.append("    movieDirector: ").append(toIndentedString(movieDirector)).append("\n");
        sb.append("    movieActor: ").append(toIndentedString(movieActor)).append("\n");
        sb.append("    movieCountry: ").append(toIndentedString(movieCountry)).append("\n");
        sb.append("    movieLanguage: ").append(toIndentedString(movieLanguage)).append("\n");
        sb.append("    movieYear: ").append(toIndentedString(movieYear)).append("\n");
        sb.append("    movieType: ").append(toIndentedString(movieType)).append("\n");
        sb.append("    moviePoster: ").append(toIndentedString(moviePoster)).append("\n");
        sb.append("    movieDuration: ").append(toIndentedString(movieDuration)).append("\n");
        sb.append("    episodeNum: ").append(toIndentedString(episodeNum)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

