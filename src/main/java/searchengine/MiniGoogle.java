package searchengine;

import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

import java.io.IOException;
import java.util.*;

import static spark.Spark.*;

public class MiniGoogle {
    private static ArrayList<String> comics;
    private static ArrayList<Comic> searchResults = new ArrayList<Comic>();

    public static void main(String[] args) throws IOException {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            return "MiniGoogle by To Uyen Nguyen, Krist Baliev";
        });

        get("/search", ( req , res ) -> {
            searchResults.clear();
            comics = Search.parseCSV("comics.csv");
            Map<String, Object> model = new HashMap<>();
            model.put ("searchResults", searchResults);
            ModelAndView modelAndView = new ModelAndView(model, "miniGoogle") ;
            return modelAndView;
        }, new JadeTemplateEngine());

        post("/search", (request, response) -> {
            searchResults.clear();
            boolean caseSensitiveChecked = false;
            boolean wholeWordChecked = false;
            if(request.queryParams("caseSensitive")!=null) caseSensitiveChecked = true;
            if(request.queryParams("wholeWord")!=null) wholeWordChecked = true;
            String input = request.queryParams("search");
            searchResults = Search.search(comics, input, caseSensitiveChecked, wholeWordChecked);

            Map<String, Object> model = new HashMap<>();
            model.put ("searchResults", searchResults);
            ModelAndView modelAndView = new ModelAndView(model, "miniGoogle") ;
            return modelAndView;
        }, new JadeTemplateEngine());
    }
}
