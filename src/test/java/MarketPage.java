import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class MarketPage {
    /**
     * Конструктор класса MarketPage
     *
     * @param driver передаваемый экземпляр WebDriver
     */
    public WebDriver driver;
    public MarketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * определение локатора поля поиска
     */
    @FindBy(xpath = "//*[@id=\"header-search\"]")
    private WebElement searchField;

    /**
     * определение локатора для кнопки поиска
     */
    @FindBy(xpath = "//button[@data-r=\"search-button\"]")
    private WebElement searchButton;

    /**
     * определение локатора для первого продукта в списке
     */
    @FindBy(xpath = "//h3[@data-tid='1410220a']/a")
    private List<WebElement> productLinks;

    /**
     * определение локатора сортировки по цене
     */
    @FindBy(xpath = "//button[@data-autotest-id=\"dprice\"]")
    private WebElement apriceBTN;

    /**
     * определения локатора по первому элементу в списке после сортировки
     */
    @FindBy(xpath = "//h3[@data-tid='1410220a']/a")
    private List<WebElement> productLinksClick;

    /**
     * определение локатора с информацией о стоимости товара
     */
    @FindBy(xpath = "//div[@data-baobab-name=\"price\"]/h3/span[2]")
    private WebElement costProduct;

    /**
     * определение локатора с информацией о магазине товара
     */
    @FindBy(xpath = "//div[@data-zone-name=\"shop-name\"]")
    private WebElement magazinProduct;

    /**
     * Метод для ввода текста в поле поиска
     *
     * @param product передаваемый текст для поиска
     */

    //@Step("Ввод текста в поле поиска")
    public void inputSearch(String product){
        searchField.sendKeys(product);
    }

    /**
     * Метод для клика по кнопке поиска
     */
    public void clickSearchButton(){
        searchButton.click();
    }

    /**
     * Метод для проверки первого продукта в списке
     *
     * @return возвращает текст первого продукта в списке
     */
  //  @Step("Получение первого продукта поиска, проверка того, что вышло именно то, что ищем")
    public String checkFirstProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstProductLink = wait.until(ExpectedConditions.visibilityOf(productLinks.get(0)));
        String firstProduct = firstProductLink.getText();
        Assert.assertTrue(firstProduct.contains("Xiaomi Redmi Note 8 Pro"), "Первый элемент - это не то, что мы искали");
        return firstProductLink.getText();
    }

    /**
     * Метод для сортировки по цене
     */
   // @Step("Сортировка по цене")
    public void sortPrice(){
        apriceBTN.click();
    }

    /**
     * Метод для клика по первому продукту в списке
     */
  //  @Step("Клик по первому продукту после сортировки")
    public void clickProductLink(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstProductLink = wait.until(ExpectedConditions.visibilityOf(productLinks.get(0)));
        firstProductLink.click();
    }

    /**
     * Метод для вывода информации о продукте
     */
  //  @Step("Вывод информации о продукте")
    public void infoProduct(){
        System.out.println("Магазин: " + magazinProduct.getText());
        System.out.println("Стоимость: " + costProduct.getText());
    }
}
