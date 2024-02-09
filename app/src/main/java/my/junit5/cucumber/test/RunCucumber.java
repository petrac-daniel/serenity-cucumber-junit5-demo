package my.junit5.cucumber.test;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@SelectClasspathResource("features")
//@IncludeEngines("cucumber")
@ExtendWith(SerenityJUnit5Extension.class)
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "my.junit5.cucumber.stepdefs")
public class RunCucumber {
}
