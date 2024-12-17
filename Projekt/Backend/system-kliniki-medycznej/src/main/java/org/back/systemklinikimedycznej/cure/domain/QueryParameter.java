package org.back.systemklinikimedycznej.cure.domain;

public class QueryParameterBuilder {
    private final String name;

    QueryParameterBuilder(String name) {
        this.name = name;
    }

    public static QueryParameterBuilderBuilder builder() {
        return new QueryParameterBuilderBuilder();
    }

    public static class QueryParameterBuilderBuilder {
        private String name;

        QueryParameterBuilderBuilder() {
        }

        public QueryParameterBuilderBuilder append(String name) {
            this.name = name;
            return this;
        }

        public QueryParameterBuilder build() {
            return new QueryParameterBuilder(this.name);
        }

        public String toString() {
            return "QueryParameterBuilder.QueryParameterBuilderBuilder(name=" + this.name + ")";
        }
    }
}
