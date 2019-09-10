import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/resources/repo",
    monochrome = true,
    format={"pretty","json:target/cucumber-report/cucumber.json"},
    glue = "reqres"
    //tags = {"@getcomments"}
    //tags = {"@createissue,@entercomments,@editcomments,@getcomments,@deletecomments"}
    //tags= {"@getarepo,@getallrepos"}
)

public class Runner extends AbstractTestNGCucumberTests{

}