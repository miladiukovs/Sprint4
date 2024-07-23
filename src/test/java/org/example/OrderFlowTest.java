package org.example;

import PageObject.HomePage;
import PageObject.OrderPage;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


@RunWith(Parameterized.class)
public class OrderFlowTest {
    private WebDriver driver;

    private final String orderButtonClass;
    private final String userName;
    private final String userLastname;
    private final String userAddress;
    private final String subwayStationName;
    private final String userPhone;
    private final String date;
    private final String duration;
    private final String colour;
    private final String comment;

    public OrderFlowTest(String orderButtonClass,
                          String userName, String userLastname, String userAddress, String subwayStationName, String userPhone,
                          String date, String duration, String colour, String comment) {
        this.orderButtonClass = orderButtonClass;
        this.userName = userName;
        this.userLastname = userLastname;
        this.userAddress = userAddress;
        this.subwayStationName = subwayStationName;
        this.userPhone = userPhone;

        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] setUserData() {

        return new Object[][] {
                {HomePage.getOrderButtonHeader(),
                        "Тест", "Тестов", "Москва, ул. Тверская, д. 1", "Сокольники", "+79090909090",
                        "26", "сутки", "black", "коммент"},
                {HomePage.getOrderButtonBody(),
                        "Толик", "Толиков", "Москва, ул. Тестовая, д. 1", "Чистые пруды", "+79898989898",
                        "24", "двое суток", "grey", "тест"},
        };
    }

    @Test
    public void orderTestChrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        HomePage objHomePage = new HomePage(driver);
        OrderPage orderPage = new OrderPage(driver);
        driver.get(objHomePage.getHOME_PAGE_URL());
        objHomePage.hideCookieBanner();

        objHomePage.locateAndClickOrderButton(orderButtonClass);

        orderPage.fillUserData(userName, userLastname, userAddress, subwayStationName, userPhone);
        orderPage.clickNextButton();

        orderPage.fillDeliveryDetails(date, duration, colour, comment);

        orderPage.checkConfimationModalFormIsVisible();

        orderPage.checkSuccesfullOrderFormIsVisible();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
//