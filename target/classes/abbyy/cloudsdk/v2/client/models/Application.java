package abbyy.cloudsdk.v2.client.models;

import abbyy.cloudsdk.v2.client.models.enums.ApplicationType;

import java.util.Date;

/**
 * Describes the Application
 */
public final class Application {
    /**
     * Application Id
     */
    private String id;

    /**
     * The number of pages remained for processing
     */
    private Integer pages;

    /**
     * The number of fields remained for processing
     */
    private Integer fields;

    /**
     * Pages expiration date
     */
    private Date expires;

    /**
     * Application type
     */
    private ApplicationType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getFields() {
        return fields;
    }

    public void setFields(Integer fields) {
        this.fields = fields;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public ApplicationType getType() {
        return type;
    }

    public void setType(ApplicationType type) {
        this.type = type;
    }
}
