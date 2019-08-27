import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/resources/feature/user/UserDetails.feature",
    monochrome = true,
    format={"pretty","json:target/cucumber-report/cucumber.json"},
    glue = "reqres.apitests.get"
)

public class Runner extends AbstractTestNGCucumberTests{

}