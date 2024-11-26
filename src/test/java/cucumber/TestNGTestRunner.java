package cucumber;

@CucumberOptions(features="src/test/java/cucumber",glue="RahulShettyAcademy.StepDefinitions",
monochrome=true,tags="@Regression",plugin= {html:target/cucumber.html})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}