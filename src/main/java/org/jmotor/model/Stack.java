package org.jmotor.model;

/**
 * Component:
 * Description:
 * Date: 2015/6/18
 *
 * @author Andy Ai
 */
public class Stack {
    private Integer id;
    private String groupId;
    private String artifactId;
    private String version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class Builder {
        private Integer id;
        private String groupId;
        private String artifactId;
        private String version;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder groupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder artifactId(String artifactId) {
            this.artifactId = artifactId;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Stack build() {
            Stack stack = new Stack();
            stack.setId(id);
            stack.setArtifactId(artifactId);
            stack.setGroupId(groupId);
            stack.setVersion(version);
            return stack;
        }

        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
