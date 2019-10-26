package reqres.helpers.repo;

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
"name",
"description",
"homepage",
"private",
"has_issues",
"has_projects",
"has_wiki"
})
public class CreateRepo {

@JsonProperty("name")
private String name;
@JsonProperty("description")
private String description;
@JsonProperty("homepage")
private String homepage;
@JsonProperty("private")
private Boolean _private;
@JsonProperty("has_issues")
private Boolean hasIssues;
@JsonProperty("has_projects")
private Boolean hasProjects;
@JsonProperty("has_wiki")
private Boolean hasWiki;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("description")
public String getDescription() {
return description;
}

@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

@JsonProperty("homepage")
public String getHomepage() {
return homepage;
}

@JsonProperty("homepage")
public void setHomepage(String homepage) {
this.homepage = homepage;
}

@JsonProperty("private")
public Boolean getPrivate() {
return _private;
}

@JsonProperty("private")
public void setPrivate(Boolean _private) {
this._private = _private;
}

@JsonProperty("has_issues")
public Boolean getHasIssues() {
return hasIssues;
}

@JsonProperty("has_issues")
public void setHasIssues(Boolean hasIssues) {
this.hasIssues = hasIssues;
}

@JsonProperty("has_projects")
public Boolean getHasProjects() {
return hasProjects;
}

@JsonProperty("has_projects")
public void setHasProjects(Boolean hasProjects) {
this.hasProjects = hasProjects;
}

@JsonProperty("has_wiki")
public Boolean getHasWiki() {
return hasWiki;
}

@JsonProperty("has_wiki")
public void setHasWiki(Boolean hasWiki) {
this.hasWiki = hasWiki;
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

