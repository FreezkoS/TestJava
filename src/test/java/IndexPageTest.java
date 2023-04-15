import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 Класс для тестирования страницы маркета.
 */
public class IndexPageTest {

    public static MarketPage marketPage;
    public static WebDriver driver;

    /**
     Метод для настройки драйвера и открытия страницы маркета перед тестированием.
     */
    @BeforeTest
    public static void setup(){
        //путь к драйверу
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("ChromeDriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        marketPage = new MarketPage(driver);
        //браузер на весь экран
        driver.manage().window().maximize();
        //добавление неявного ожидания на 10 сек
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //ссылка на страницу маркета
        driver.get(ConfProperties.getProperty("MarketPage"));
    }

    /**
     * закрытие браузера после выполнения всех тестов
     */
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    /**
     * Метод для тестирования страницы маркета.
     * @throws InterruptedException если произошла ошибка во время ожидания.
     */
    @Test
    public void marketPageTest() throws InterruptedException {
        Thread.sleep(2000);
        marketPage.inputSearch(ConfProperties.getProperty("Product"));
        System.out.println("Ввод телефона произошел");
        Thread.sleep(2000);
        marketPage.clickSearchButton();
        System.out.println("Кнопка поиска нажата");
        marketPage.checkFirstProduct();
        System.out.println("Проверка первого продукта из списка выполнена");
        Thread.sleep(2000);
        marketPage.sortPrice();
        System.out.println("Выполнена сортировка по цене");
        Thread.sleep(2000);
        marketPage.clickProductLink();
        System.out.println("Совершен переход по первому товару");
        Thread.sleep(2000);
        // сохраняем идентификатор текущей вкладки
        String currentHandle = driver.getWindowHandle();

        // переключаемся на новую вкладку
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // получаем информацию из открытой вкладки
        System.out.println("Вывод информации по продукту");
        marketPage.infoProduct();
        Thread.sleep(2000);

        // возвращаемся на исходную вкладку
        driver.switchTo().window(currentHandle);
    }
}
