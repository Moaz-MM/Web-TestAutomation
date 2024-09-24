package BDD.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/BDD/features",
        glue = {"BDD.steps"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

