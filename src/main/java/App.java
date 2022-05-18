import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public/");

        ProcessBuilder process = new ProcessBuilder();
        int port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String heroName = request.queryParams("heroName");
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/newhero/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String heroName = request.queryParams("heroName");
            return new ModelAndView(model, "newhero.hbs");
        }, new HandlebarsTemplateEngine());

        post("/heroesdetails/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String heroName = request.queryParams("heroName");
            Integer heroAge = Integer.parseInt(request.queryParams("heroAge"));
            String heroStrength = request.queryParams("heroStrength");
            String heroWeakness = request.queryParams("heroWeakness");
            Hero hero = new Hero(heroName, heroAge, heroStrength, heroWeakness);
            HeroTask heroTask = new HeroTask();
            heroTask.creatHero(request.session().attribute("myHeroList"), hero);
            List<Hero> heroList = heroTask.getList();
            request.session().attribute("myHeroList", heroList);
            response.redirect("/heroesdetails");
            return null;
        }, new HandlebarsTemplateEngine());

//        heroesdetails
        get("/heroesdetails", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("Heroes", request.session().attribute("myHeroList"));
            return new ModelAndView(model, "heroesdetails.hbs");
        }, new HandlebarsTemplateEngine());

//        newsquad
        get("/newsquad", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newsquad.hbs");
        }, new HandlebarsTemplateEngine());

        post("/squaddetails/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Integer squadSize = Integer.parseInt(request.queryParams("squadSize"));
            String squadName = request.queryParams("squadName");
            String squadCause = request.queryParams("squadCause");
            Squad squad = new Squad(squadName, squadCause);
            SquadTask squadTask = new SquadTask();
            squadTask.createSquad(request.session().attribute("mySquadList"), squad);
            List<Squad>squadList = squadTask.getSquad();
            request.session().attribute("mySquadList", squadList);
            response.redirect("/squaddetails");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/squaddetails", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("Squads", request.session().attribute("mySquadList"));
            return new ModelAndView(model, "squaddetails.hbs");
        }, new HandlebarsTemplateEngine());


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


    }

}