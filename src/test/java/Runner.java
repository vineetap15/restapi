import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/resources/feature",
    monochrome = true,
    format={"pretty","json:target/cucumber-report/cucumber.json"},
    glue = "reqres",
    //tags = {"@deletecomments"}
    tags = {"@createissue,@entercomments,@editcomments,@getcomments,@deletecomments"}
)

public class Runner extends AbstractTestNGCucumberTests{

}