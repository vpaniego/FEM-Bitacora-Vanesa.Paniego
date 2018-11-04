
package es.upm.miw.bitacora.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BestSeller {

    @SerializedName("age_group")
    @Expose
    private String ageGroup;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("contributor")
    @Expose
    private String contributor;
    @SerializedName("contributor_note")
    @Expose
    private String contributorNote;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("isbns")
    @Expose
    private List<Isbn> isbns = null;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("ranks_history")
    @Expose
    private List<RanksHistory> ranksHistory = null;
    @SerializedName("reviews")
    @Expose
    private List<Review> reviews = null;
    @SerializedName("title")
    @Expose
    private String title;

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getContributorNote() {
        return contributorNote;
    }

    public void setContributorNote(String contributorNote) {
        this.contributorNote = contributorNote;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Isbn> getIsbns() {
        return isbns;
    }

    public void setIsbns(List<Isbn> isbns) {
        this.isbns = isbns;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<RanksHistory> getRanksHistory() {
        return ranksHistory;
    }

    public void setRanksHistory(List<RanksHistory> ranksHistory) {
        this.ranksHistory = ranksHistory;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
