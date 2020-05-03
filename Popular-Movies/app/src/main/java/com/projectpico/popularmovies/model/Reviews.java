package com.projectpico.popularmovies.model;

import java.util.List;

/**********************************************************************************************************************
 * A model used to generate a POJO from the JSON string provided by the GET request in our MovieClient. This POJO
 * represents the model from themoviedb.org
 *
 * @author mlewis
 * @version May 2, 2020
 *********************************************************************************************************************/
public class Reviews {
    private Integer id;
    private Integer page;
    private List<Result> results = null;
    private Integer totalPages;
    private Integer totalResults;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    /******************************************************************************************************************
     * A nested JSON object under the movies Result element. Contains movie specific metadata that will be used in our
     * Movie model.
     *
     * @author mlewis
     * @version May 2, 2020
     *****************************************************************************************************************/
    public class Result {

        private String author;
        private String content;
        private String id;
        private String url;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
