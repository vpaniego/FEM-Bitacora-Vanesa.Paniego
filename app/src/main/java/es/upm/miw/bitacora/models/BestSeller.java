
package es.upm.miw.bitacora.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BestSeller {

    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("status")
    @Expose
    private String status;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BestSeller{" +
                "copyright='" + copyright + '\'' +
                ", numResults=" + numResults +
                ", results=" + results +
                ", status='" + status + '\'' +
                '}';
    }
}
