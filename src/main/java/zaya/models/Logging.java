
package zaya.models;

import java.sql.Timestamp;

public class Logging {
    private Integer id;
    private String code;
    private String description;
    private String entityRequest;
    private String entityResponse;
    private String entityType;
    private Integer entityId;
    private Timestamp updatedAt;

    public Logging() {
    }

    private Logging(Builder builder) {
        this.id = builder.id;
        this.code = builder.code;
        this.description = builder.description;
        this.entityRequest = builder.entityRequest;
        this.entityResponse = builder.entityResponse;
        this.entityType = builder.entityType;
        this.entityId = builder.entityId;
        this.updatedAt = builder.updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntityRequest() {
        return this.entityRequest;
    }

    public void setEntityRequest(String entityRequest) {
        this.entityRequest = entityRequest;
    }

    public String getEntityResponse() {
        return this.entityResponse;
    }

    public void setEntityResponse(String entityResponse) {
        this.entityResponse = entityResponse;
    }

    public String getEntityType() {
        return this.entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Integer getEntityId() {
        return this.entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class Builder {
        private Integer id;
        private String code;
        private String description;
        private String entityRequest;
        private String entityResponse;
        private String entityType;
        private Integer entityId;
        private Timestamp updatedAt;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setEntityRequest(String entityRequest) {
            this.entityRequest = entityRequest;
            return this;
        }

        public Builder setEntityResponse(String entityResponse) {
            this.entityResponse = entityResponse;
            return this;
        }

        public Builder setEntityType(String entityType) {
            this.entityType = entityType;
            return this;
        }

        public Builder setEntityId(Integer entityId) {
            this.entityId = entityId;
            return this;
        }

        public Builder setUpdatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Logging build() {
            return new Logging(this);
        }
    }
}
