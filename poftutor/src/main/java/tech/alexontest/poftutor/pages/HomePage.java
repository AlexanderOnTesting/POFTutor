package tech.alexontest.poftutor.pages;

import com.google.inject.ImplementedBy;
import org.openqa.selenium.WebElement;

import java.util.List;

@ImplementedBy(PfHomePage.class)
public interface HomePage extends Page {
    List<WebElement> getWidgets();

    List<WebElement> getArticles();
}