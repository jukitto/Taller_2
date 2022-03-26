package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Whendo {

    private AppiumDriver driver;
    @BeforeEach
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability( "appium:deviceName","POCO X3 NFC - Ju");
        capabilities.setCapability("appium:platformVersion","11");
        capabilities.setCapability("appium:appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appium:appActivity","ui.HomeActivity");
        capabilities.setCapability("platformName","Android");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));

    }
    @AfterEach
    public void cleanup(){
        driver.quit();
    }

    @Test
    public void crearListaTest() throws InterruptedException {
        //crear noa1
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys("Titulo_1");
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("Nota_1");
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
        String resultadoActual = driver.findElement(By.id("com.vrproductiveapps.whendo:id/home_list_item_text")).getText();

        String resultadoEsperado ="Titulo_1";
        Assertions.assertEquals(resultadoEsperado,resultadoActual,"no se encontró el título");

        //crear nota2
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys("Titulo_2");
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("Nota-2");
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
        String resultadoActual2 = driver.findElement(By.xpath("//android.widget.TextView[@text='Titulo_2']")).getText();

        String resultadoEsperado2 ="Titulo_2";
        Assertions.assertEquals(resultadoEsperado2,resultadoActual2,"no se encontró el título");

        //Eliminar
        driver.findElement(By.xpath("//android.widget.TextView[@text='Titulo_1']")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/deleteItem")).click();
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(3000);
    }
}
