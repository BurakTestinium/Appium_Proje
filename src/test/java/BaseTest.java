import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class BaseTest extends Driver {

    @Step("<int> saniye kadar bekle")
    public void waitForsecond(int s) throws InterruptedException {
        Thread.sleep(1000*s);
    }

    @Step("<id> elementin sayfada gorunur olduğunu kontrol et ve tıkla")
    public void findByelementEndclick(String id){
        MobileElement element = appiumDriver.findElement(By.id(id));
        if (element.isDisplayed()){
            element.click();
        }else{
            System.out.println("Kontrol edilen element görünür olmadı");
        }
    }
    @Step("Sayfayı aşağı doğru scroll yap")
    public void swipeUp(){
        final int ANIMATION_TIME = 200;
        final int PRESS_TIME = 200;
        int edgeBorder = 10;
        PointOption pointOptionStart, pointOptionEnd;
        Dimension dims = appiumDriver.manage().window().getSize();
        System.out.println("Telefon Ekran Boyutu " + dims);
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        System.out.println("Baslangıc noktası " + pointOptionStart);
        pointOptionEnd = PointOption.point(dims.width / 2, dims.height / 4);
        System.out.println("Bitis noktası " + pointOptionEnd);
        try {
            new TouchAction(appiumDriver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }


        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {

        }
    }

    @Step("Xpath <xpath> li elementi bul ve tıkla")
    public void clickByxpath(String xpath){
        appiumDriver.findElement(By.xpath(xpath)).click();

    }


    @Step("Id <id> li elementi bul ve tıkla")
    public void clickByid(String id){
        appiumDriver.findElement(By.id(id)).click();

    }

    @Step("Xpath <xpath> kullanarak random ürün secimi")
     public void randomProductSelect(String xpath){
        List<MobileElement> elements = appiumDriver.findElements(By.xpath("//*[@resource-id=\"com.ozdilek.ozdilekteyim:id/imageView\"]"));
        Random rand = new Random();
        int rndInt = rand.nextInt(elements.size());
        elements.get(rndInt).click();
        System.out.println("Random ürün secildi");
    }

    @Step("Id <id> li elementi bul ve <info> gir")
    public void sendKeys(String id, String info) {

        System.out.println("Giris yapma sayfasi acildi");
        MobileElement element = appiumDriver.findElement(By.id(id));
        if (element.isDisplayed()) {
            element.sendKeys(info);

        } else {
            System.out.println("Giriş yapma sayfası açılamadı.");
        }

    }

    @Step("Xpath <xpath> kullanarak random beden secimi")
    public void randomBedenSecimi(String xpath){
        List<MobileElement> elements = appiumDriver.findElements(By.xpath("//*[@resource-id=\"com.ozdilek.ozdilekteyim:id/tvInSizeItem\"]"));
        Random rand = new Random();
        int rndInt = rand.nextInt(elements.size());
        elements.get(rndInt).click();
        System.out.println("Random beden secildi");
    }

}



