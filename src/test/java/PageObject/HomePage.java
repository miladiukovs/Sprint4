package PageObject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    //надо протестить
    private final String HOME_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    //локатор стрелки в блоке "Вопросы о важном"
    private String arrowButton = ".//div[@id='accordion__heading-%d']";

    // локатор области с текстом в блоке "Вопросы о важном"
    private String textArea = ".//div[@id='accordion__panel-%d']//p";

    //локатор кнопки заказа самоката
    private String orderButtonLink = ".//div[contains(@class,'%s')]/button[text()='Заказать']";

    //кнопка заказа в шапке страницы
    private static String orderButtonHeader = "Header_Nav";

    //кнопка заказа в теле страницы
    private static String orderButtonBody = "Home_FinishButton__1_cWm";

    //локатор кнопки куки
    private By cookieButton = By.className("App_CookieButton__3cvqF");

    public String getHOME_PAGE_URL() {
        return HOME_PAGE_URL;
    }

    public static String getOrderButtonHeader() {
        return orderButtonHeader;
    }

    public static String getOrderButtonBody() {
        return orderButtonBody;
    }

    //конструктор класса
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //метод, скрывающий баннер про куки
    public void hideCookieBanner() {
        driver.findElement(cookieButton).click();
    }

    public void findAndClickArrowButton(int faqIndex) {
        WebElement element = driver.findElement(By.xpath(String.format((arrowButton), faqIndex)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public String getFaqText(int faqIndex) {
        return driver.findElement(By.xpath(String.format(textArea, faqIndex))).getText();
    }

    public void locateAndClickOrderButton(String orderButtonClass) {
        String orderButton = String.format(orderButtonLink, orderButtonClass);
        Assert.assertTrue(driver.findElement(By.xpath(orderButton)).isEnabled());
        driver.findElement(By.xpath(orderButton)).click();
    }
}
