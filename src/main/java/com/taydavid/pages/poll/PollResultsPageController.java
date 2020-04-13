package com.taydavid.pages.poll;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.taydavid.factory.DriverFactory;

public class PollResultsPageController {

	@FindBy(className = "pollBooth_view")
	private WebElement pollBooth;

	@FindBy(xpath = "//*[@class=\"poll-bar-group\"]")
	private List<WebElement> pollBarGroup;

	public PollResultsPageController() {
		PageFactory.initElements(DriverFactory.INSTANCE.getWebDriver(), this);
	}

	public PollResultsPageController printPollVotedResults(final String selectedPollOption) {
		DriverFactory.INSTANCE.jsDocumentStateReady();
		((JavascriptExecutor) DriverFactory.INSTANCE.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);",
				pollBooth);

		boolean isOriginalPollSelectionFound = pollBarGroup.stream().peek(poll -> {
			if (poll.findElement(By.className("poll-bar-label")).getText().equals(selectedPollOption)) {
				System.out.println(
						selectedPollOption + " has " + poll.findElement(By.className("poll-bar-text")).getText());
				return;
			}
		}).anyMatch(poll -> poll.findElement(By.className("poll-bar-label")).getText().equals(selectedPollOption));
		Assert.assertTrue(isOriginalPollSelectionFound, "Original poll selection was found on poll results page");
		return this;
	}
}
