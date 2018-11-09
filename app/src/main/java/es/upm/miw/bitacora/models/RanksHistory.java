
package es.upm.miw.bitacora.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RanksHistory {

    @SerializedName("asterisk")
    @Expose
    private Integer asterisk;
    @SerializedName("bestsellers_date")
    @Expose
    private String bestsellersDate;
    @SerializedName("dagger")
    @Expose
    private Integer dagger;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("list_name")
    @Expose
    private String listName;
    @SerializedName("primary_isbn10")
    @Expose
    private String primaryIsbn10;
    @SerializedName("primary_isbn13")
    @Expose
    private String primaryIsbn13;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("weeks_on_list")
    @Expose
    private Integer weeksOnList;

    public Integer getAsterisk() {
        return asterisk;
    }

    public void setAsterisk(Integer asterisk) {
        this.asterisk = asterisk;
    }

    public String getBestsellersDate() {
        return bestsellersDate;
    }

    public void setBestsellersDate(String bestsellersDate) {
        this.bestsellersDate = bestsellersDate;
    }

    public Integer getDagger() {
        return dagger;
    }

    public void setDagger(Integer dagger) {
        this.dagger = dagger;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getPrimaryIsbn10() {
        return primaryIsbn10;
    }

    public void setPrimaryIsbn10(String primaryIsbn10) {
        this.primaryIsbn10 = primaryIsbn10;
    }

    public String getPrimaryIsbn13() {
        return primaryIsbn13;
    }

    public void setPrimaryIsbn13(String primaryIsbn13) {
        this.primaryIsbn13 = primaryIsbn13;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getWeeksOnList() {
        return weeksOnList;
    }

    public void setWeeksOnList(Integer weeksOnList) {
        this.weeksOnList = weeksOnList;
    }

}
