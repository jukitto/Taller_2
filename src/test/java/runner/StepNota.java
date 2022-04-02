package runner;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class StepNota {

    private AppiumDriver driver;

    @Given("mi aplicacion WhenDo esta abierto")
    public void miAplicacionWhenDoEstaAbierto() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability( "appium:deviceName","POCO X3 NFC - Ju");
        capabilities.setCapability("appium:platformVersion","11");
        capabilities.setCapability("appium:appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appium:appActivity","ui.HomeActivity");
        capabilities.setCapability("platformName","Android");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
    }

    @When("yo registro una nueva Nota")
    public void yoRegistroUnaNuevaNota(Map<String,String> values) {
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(values.get("titulo"));
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(values.get("Dnota"));
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();

    }

    @Then("la nota {string} deberia ser creado")
    public void laNotaDeberiaSerCreado(String resultadoEsperado) {
        String resultadoActual = driver.findElement(By.id("com.vrproductiveapps.whendo:id/home_list_item_text")).getText();
        Assertions.assertEquals(resultadoEsperado,resultadoActual,"no se encontró el título");
    }

    @When("selecciono Nota con titulo: {string}")
    public void seleccionoNotaConTitulo(String titulo) throws InterruptedException {
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+titulo+"']")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/deleteItem")).click();
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(3000);
    }

    @Then("valido que ya no se visualice la nota")
    public void validoQueYaNoSeVisualiceLaNota() {
    }


}
