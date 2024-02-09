package my.junit5.cucumber.main;

import my.junit5.cucumber.test.RunCucumber;
import net.serenitybdd.junit5.SerenityTestExecutionListener;
import net.thucydides.core.reports.html.HtmlAggregateStoryReporter;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class Main {

    public static void main(String[] args) throws IOException {
        Launcher launcher = LauncherFactory.create();
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectClass(RunCucumber.class)
                )
                .enableImplicitConfigurationParameters(true)
                .build();

        SerenityTestExecutionListener serenityListener = new SerenityTestExecutionListener();
        SummaryGeneratingListener summaryListener = new SummaryGeneratingListener();
        launcher.execute(request, serenityListener, summaryListener);

        TestExecutionSummary summary = summaryListener.getSummary();

        HtmlAggregateStoryReporter reporter = new HtmlAggregateStoryReporter("default");
        File reportOutput = new File("target");
        if (!reportOutput.exists()) {
            Files.createDirectory(reportOutput.toPath());
        }
        reporter.setOutputDirectory(reportOutput);
        reporter.setGenerateTestOutcomeReports();
        reporter.generateReportsForTestResultsFrom(reportOutput);

        System.out.println(summary.getTestsFoundCount() + " tests found");
        System.out.println(summary.getTestsSucceededCount() + " tests passed");
    }
}
