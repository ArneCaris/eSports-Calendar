package trung.nguyen.javaicongame;

//[
//{
//    winner_id: null,
//    winner: null,
//    videogame_version: {},
//    videogame: {},
//    tournament_id: 2088,
//    tournament: {},
//    status: "running",
//    slug: "apk-prince-vs-bbq-olivers-2019-03-18",
//    serie_id: 1713,
//    serie: {},
//    results: [],
//    opponents: [],
//    number_of_games: 3,
//    name: "APK vs BBQ",
//    modified_at: "2019-03-18T11:30:49Z",
//    match_type: "best_of",
//    live: {},
//    league_id: 4142,
//    league: {},
//    id: 541085,
//    games: [],
//    draw: false,
//    begin_at: "2019-03-18T11:30:49Z",
//},
//]

import java.util.Date;

class Match{
    private Integer id;
    private String name;
    private Date begin_at;

    public Match(Integer id, String name, Date begin_at) {
        this.id = id;
        this.name = name;
        this.begin_at = begin_at;
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
}
