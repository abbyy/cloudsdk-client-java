// Copyright © 2019 ABBYY Production LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.ocrsdk.abbyy.v2.client.models;

import com.ocrsdk.abbyy.v2.client.models.enums.ApplicationType;

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
     * Displayed name of the application
     */
    private String displayName;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
