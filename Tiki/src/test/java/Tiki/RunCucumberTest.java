package Tiki;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-reports"}, 
				glue="stepdefinition",  //set add pack stepdefi
				features="src/test/resources/Tiki", //set src file feature (dang chay het, neu muon chay file feature nao thi them vao sau tiki)
				monochrome=true) //loai bo cac ky tu kho' doc khi Run
public class RunCucumberTest {
}