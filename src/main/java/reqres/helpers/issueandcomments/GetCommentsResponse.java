package reqres.helpers.issueandcomments;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"url",
"html_url",
"issue_url",
"id",
"node_id",
"user",
"created_at",
"updated_at",
"author_association",
"body"
})
public class GetCommentsResponse {

@JsonProperty("url")
private String url;
@JsonProperty("html_url")
private String htmlUrl;
@JsonProperty("issue_url")
private String issueUrl;
@JsonProperty("id")
private Integer id;
@JsonProperty("node_id")
private String nodeId;
@JsonProperty("user")
private GetCommentsResponseUser user;
@JsonProperty("created_at")
private String createdAt;
@JsonProperty("updated_at")
private String updatedAt;
@JsonProperty("author_association")
private String authorAssociation;
@JsonProperty("body")
private String body;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("url")
public String getUrl() {
return url;
}

@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

@JsonProperty("html_url")
public String getHtmlUrl() {
return htmlUrl;
}

@JsonProperty("html_url")
public void setHtmlUrl(String htmlUrl) {
this.htmlUrl = htmlUrl;
}

@JsonProperty("issue_url")
public String getIssueUrl() {
return issueUrl;
}

@JsonProperty("issue_url")
public void setIssueUrl(String issueUrl) {
this.issueUrl = issueUrl;
}

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("node_id")
public String getNodeId() {
return nodeId;
}

@JsonProperty("node_id")
public void setNodeId(String nodeId) {
this.nodeId = nodeId;
}

@JsonProperty("user")
public GetCommentsResponseUser getUser() {
return user;
}

@JsonProperty("user")
public void setUser(GetCommentsResponseUser user) {
this.user = user;
}

@JsonProperty("created_at")
public String getCreatedAt() {
return createdAt;
}

@JsonProperty("created_at")
public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

@JsonProperty("updated_at")
public String getUpdatedAt() {
return updatedAt;
}

@JsonProperty("updated_at")
public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

@JsonProperty("author_association")
public String getAuthorAssociation() {
return authorAssociation;
}

@JsonProperty("author_association")
public void setAuthorAssociation(String authorAssociation) {
this.authorAssociation = authorAssociation;
}

@JsonProperty("body")
public String getBody() {
return body;
}

@JsonProperty("body")
public void setBody(String body) {
this.body = body;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}