package calendar.esports;
import java.io.Serializable;
import java.util.Date;

class Match implements Serializable{
    private Integer id;
    private String name;
    private Date begin_at;
    private Opponents[] opponents;
    private Result[] results;

    public Match(Integer id, String name, Date begin_at, Opponents[] opponents, Result[] results) {
        this.id = id;
        this.name = name;
        this.begin_at = begin_at;
        this.opponents = opponents;
        this.results = results;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBegin_at() {
        return begin_at;
    }

    public void setBegin_at(Date begin_at) {
        this.begin_at = begin_at;
    }

    public Opponents[] getOpponents() {
        return opponents;
    }

    public void setOpponents(Opponents[] opponents) {
        this.opponents = opponents;
    }

    public Result[] getResults() {
        return results;
    }

    public void setResults(Result[] results) {
        this.results = results;
    }
}

class Opponents implements Serializable{
    private String type;
    private Team opponent;

    public Opponents(String type, Team opponent) {
        this.type = type;
        this.opponent = opponent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Team getOpponent() {
        return opponent;
    }

    public void setOpponent(Team opponent) {
        this.opponent = opponent;
    }
}

class Team implements Serializable{
    private Integer id;
    private String image_url;
    private String slug;
    private String name;

    public Team(Integer id, String image_url, String slug, String name) {
        this.id = id;
        this.image_url = image_url;
        this.slug = slug;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Result implements Serializable{
    private Integer team_id;
    private Integer score;

    public Result(Integer team_id, Integer score) {
        this.team_id = team_id;
        this.score = score;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer id) {
        this.team_id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
