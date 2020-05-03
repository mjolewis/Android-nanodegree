package com.projectpico.popularmovies.model;

import java.util.List;

/**********************************************************************************************************************
 * A model used to generate a POJO from the JSON string provided by the GET request in our MovieClient. This POJO
 * represents the model from themoviedb.org
 *
 * @author mlewis
 * @version May 3, 2020
 *********************************************************************************************************************/
public class Trailers {

    private Integer id;
    private List<Object> quicktime = null;
    private List<Youtube> youtube = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getQuicktime() {
        return quicktime;
    }

    public void setQuicktime(List<Object> quicktime) {
        this.quicktime = quicktime;
    }

    public List<Youtube> getYoutube() {
        return youtube;
    }

    public void setYoutube(List<Youtube> youtube) {
        this.youtube = youtube;
    }


    /******************************************************************************************************************
     * A nested JSON object. Contains movie specific metadata that will be used to play movie trailers.
     *
     * @author mlewis
     * @version May 2, 2020
     *****************************************************************************************************************/
    public class Youtube {

        private String name;
        private String size;
        private String source;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}