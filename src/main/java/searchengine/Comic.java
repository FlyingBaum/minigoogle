package searchengine;

public class Comic {
    private String link;
    private String alt;
    private String title;

    public Comic(String link, String alt, String title){
        this.link = link;
        this.alt = alt;
        this.title = title;
    }

    public String getLink() { return link; }
    public String getAlt() { return alt; }
    public String getTitle() { return title; }
}
