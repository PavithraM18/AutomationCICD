package SelfLearning.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/SelfLearning/Cucumber",
glue = {"SelfLearning.StepDefinitions"},
monochrome = true,
plugin = {"html:target/cucumber.html"},
publish = true)
 //tags =  "@Regression",
public class TestNgTestRunner extends AbstractTestNGCucumberTests  {

}
