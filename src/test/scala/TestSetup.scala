import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.WebDriverWait

/**
  * Created by knoldus on 28/3/17.
  */
trait TestSetup {

  val BASE_URL = "http://www.flipkart.com/"
  val EMAIL = "ashish1269@gmail.com"
  val PASSWORD = "91v1c@45722"
  System.setProperty("webdriver.chrome.driver", "/home/knoldus/Documents/chromedriver")
  val capabilities = DesiredCapabilities.chrome()
  val driver = new ChromeDriver(capabilities)
  val webdriverwait = new WebDriverWait(driver, 10)

}
