/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.index.query;

import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

/**
 * Constructs a filter that only match on documents that the field has a value in them.
 */
public class MissingQueryBuilder extends QueryBuilder {

    private String name;

    private String queryName;

    private Boolean nullValue;

    private Boolean existence;

    public MissingQueryBuilder(String name) {
        this.name = name;
    }

    /**
     * Should the missing filter automatically include fields with null value configured in the
     * mappings. Defaults to <tt>false</tt>.
     */
    public MissingQueryBuilder nullValue(boolean nullValue) {
        this.nullValue = nullValue;
        return this;
    }

    /**
     * Should the missing filter include documents where the field doesn't exists in the docs.
     * Defaults to <tt>true</tt>.
     */
    public MissingQueryBuilder existence(boolean existence) {
        this.existence = existence;
        return this;
    }

    /**
     * Sets the filter name for the filter that can be used when searching for matched_filters per hit.
     */
    public MissingQueryBuilder queryName(String queryName) {
        this.queryName = queryName;
        return this;
    }

    @Override
    protected void doXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject(MissingQueryParser.NAME);
        builder.field("field", name);
        if (nullValue != null) {
            builder.field("null_value", nullValue);
        }
        if (existence != null) {
            builder.field("existence", existence);
        }
        if (queryName != null) {
            builder.field("_name", queryName);
        }
        builder.endObject();
    }

    @Override
    protected String parserName() {
        return MissingQueryParser.NAME;
    }
}
