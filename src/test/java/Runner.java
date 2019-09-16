import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/resources/feature/repo/",
    monochrome = true,
    format={"pretty","json:target/cucumber-report/cucumber.json"},
    glue = "reqres",
    //tags = {"@getcomments"}
    //tags = {"@createissue,@entercomments,@editcomments,@getcomments,@deletecomments"}
    tags= {"@createrepo,@editrepo,@deleterepo,@getarepo,@getallrepos"}
)

public class Runner extends AbstractTestNGCucumberTests{

}