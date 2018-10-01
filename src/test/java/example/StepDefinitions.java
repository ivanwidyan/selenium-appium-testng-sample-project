package example;

import com.testing.Handler;
import com.testing.constants.WebElementConstants;
import com.testing.utility.Utility;
import com.testing.utility.WebUtility;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.annotations.Test;

public class StepDefinitions {

    @Test
    @Given("^I am on the \"([^\"]*)\" page on URL \"([^\"]*)\"$")
    public void i_am_on_the_page_on_URL(String page, String url) throws Throwable {

        // Web
        WebUtility.GoTo(url);

    }

    @Test
    @When("^I fill in \"([^\"]*)\" with \"([^\"]*)\"$")
    public void i_fill_in_with(String arg1, String arg2) throws Throwable {


        if ("Masukan Email".equalsIgnoreCase(arg1))
            Utility.SendKeysElementByCssSelector(
                Handler.GetCurrentWebDriver(),
                WebElementConstants.PARAM_ID,
                "loginEmail",
                arg2);

        else if ("Kata Sandi".equalsIgnoreCase(arg1))
        Utility.SendKeysElementByCssSelector(
                Handler.GetCurrentWebDriver(),
                WebElementConstants.PARAM_ID,
                "loginPassword",
                arg2);

    }

    @Test
    @When("^I click on the \"([^\"]*)\" button$")
    public void i_click_on_the_button(String button) throws Throwable {

        if ("Masuk".equalsIgnoreCase(button)) {

            if ("https://www.blibli.com/".equalsIgnoreCase(
                    Handler.GetCurrentWebDriver().getCurrentUrl()))
                Utility.ClickElementByCssSelector(Handler.GetCurrentWebDriver(),
                        WebElementConstants.CLASS_SPAN,
                        WebElementConstants.PARAM_CLASS,
                        "login");

            else if ("https://www.blibli.com/#login-section".equalsIgnoreCase(
                    Handler.GetCurrentWebDriver().getCurrentUrl())) {
                Utility.ClickElementByCssSelector(
                        Handler.GetCurrentWebDriver(),
                        WebElementConstants.PARAM_ID,
                        "gdn-submit-login");

                Utility.WaitInvisibilityByCssSelector(
                        Handler.GetCurrentWebDriver(),
                        WebElementConstants.PARAM_CLASS,
                        "angular-loading");

            }
        } else if ("Keluar".equalsIgnoreCase(button)) {
            Utility.ClickElementByCssSelector(
                    Handler.GetCurrentWebDriver(),
                    WebElementConstants.PARAM_CLASS,
                    "logout");

        }

    }

    @Test
    @Then("^I should see \"([^\"]*)\" message$")
    public void i_should_see_message(String message) throws Throwable {

        if ("Account For".equalsIgnoreCase(message)) {
            Utility.WaitVisibilityByCssSelector(
                    Handler.GetCurrentWebDriver(),
                    WebElementConstants.PARAM_CLASS,
                    "poin-username");

        } else if ("Format email yang Anda masukkan salah".equalsIgnoreCase(message)
                || "Email atau Kata Sandi tidak boleh kosong".equalsIgnoreCase(message)
                || "Maaf, Email atau password yang Anda masukkan salah.".equalsIgnoreCase(message)) {
            Utility.GetElementByXPathAndContainsText(
                    Handler.GetCurrentWebDriver(),
                    WebElementConstants.CLASS_DIV,
                    WebElementConstants.PARAM_ID,
                    "gdnloginErrorLabel",
                    message);

        }

    }

    @Test
    @Then("^I should see the \"([^\"]*)\" button$")
    public void i_should_see_the_button(String button) throws Throwable {

        if ("Keluar".equalsIgnoreCase(button)) {
            Utility.WaitVisibilityByCssSelector(
                    Handler.GetCurrentWebDriver(),
                    WebElementConstants.PARAM_CLASS,
                    "logout");

        }
    }

}
