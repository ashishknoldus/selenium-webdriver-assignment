import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium._
import org.scalatest.FlatSpec

/**
  * Created by knoldus on 28/3/17.
  */
class FlipkartOrderTest extends FlatSpec with TestSetup {

  "A user" should "place order for 2 products" in {

    driver.manage().window().maximize()
    driver.get(BASE_URL)
    val title = driver.getTitle()
    val page_source = driver.getPageSource().length
    if (driver.getCurrentUrl == BASE_URL) {
      println("WELCOME TO Flipkart.com with title : " + title)
      println("the lenght of the pagesource is: " + page_source)
    } else {
      println("something went wrong")
    }

    /*--- Login click ---*/
    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul._3Ji-EC")))

    val loginLink: WebElement = driver.findElementByCssSelector("ul._3Ji-EC li._2sYLhZ:nth-child(9)")
    loginLink.click

    /*--- Wait for the form to load ---*/
    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._1XBjg-.row div.Km0IJL.col.col-3-5 div form")))

    /*--- Fill Login Form ---*/

    val emailTextBox: WebElement = driver.findElementByCssSelector("div._1XBjg-.row div.Km0IJL.col.col-3-5 div "+
      "form div._39M2dM:nth-child(1) input[type=\"text\"]")

    val passwordTextBox: WebElement = driver.findElementByCssSelector("div._1XBjg-.row div.Km0IJL.col.col-3-5 div "+
      "form div._39M2dM:nth-child(2) input[type=\"password\"]")

    emailTextBox.sendKeys(EMAIL)

    passwordTextBox.sendKeys(PASSWORD)

    /*--- Submit login form ---*/
    val loginForm: WebElement = driver.findElementByCssSelector("div._1XBjg-.row div.Km0IJL.col.col-3-5 div form")
    loginForm.submit

    /*--- Wait until user gets logged in ---*/
    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul._3Ji-EC li._2sYLhZ._2mEF1S a._1AHrFc._2k0gmP")))

    /*--- Search the product (iPhone 7) ---*/

    val searchBox: WebElement = driver.findElementByCssSelector("div.O8ZS_U input.LM6RPg")
    searchBox.sendKeys("lenovo phab 2")
    searchBox.sendKeys(Keys.ESCAPE)

    /*--- Click search button ---*/

    driver.findElementByCssSelector("div.O8ZS_U input.LM6RPg").submit()

    /*--- Wait for appearance of Heading saying -- "Showing 1 – x of y results for "lenovo phab 2" ---*/
    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_1ZODb3")))

    /*--- Filter the results ----*/

    /*--- Based on category ---*/
    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._2YW4dZ")))
    val mobilesCategory = driver.findElementByLinkText("Mobiles")
    mobilesCategory.click()
    webdriverwait.until(ExpectedConditions.urlContains("/mobiles/"))

    /*--- Select item from result ---*/

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._2SxMvQ div.col")))
    val firstProduct = driver.findElementByCssSelector("div._2SxMvQ div.col.zZCdz4:nth-child(1) a")
    firstProduct.click
    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._2SIJjY")))

    /*--- Enter the Area PIN code ---*/

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form.EJrIpC ")))

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._3l12t9")))

    /*--- Item isn't available on a PINCODE provide another one ---*/
    val pincodeMsg = driver.findElementByCssSelector("div._3l12t9")

    println("Msg--------------- " + pincodeMsg.getText)

    if(pincodeMsg.getText.contains("No sellers deliver in this area.") ) {

      val pincodeTextBox = driver.findElementByCssSelector("form.EJrIpC input._3X4tVa")
      println("Assigning the PINCODE as 110001")
      pincodeTextBox.clear()
      pincodeTextBox.sendKeys("110001") //Delhi Pincode
      pincodeTextBox.submit()
      println("Pincode submitted")
      webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._2h4rON")))
    }

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button._2AkmmA._3Plo8Q._16LyaZ._7UHT_c")))
    val addToCartButton = driver.findElementByCssSelector("button._2AkmmA._3Plo8Q._19RW-r")

    if(addToCartButton.getText == "ADD TO CART"){
      addToCartButton.click()
      webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.JAUzCh")))

      println("Mobile Added to cart")
    }

    addToCartButton.click()

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button._2AkmmA._3qVHVC._3MBbhf")))
    val continueShoppingBtn = driver.findElementByCssSelector("button._2AkmmA._3qVHVC._3MBbhf")
    continueShoppingBtn.click()

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a._1DLn60")))
    val brandsCategoryLink = driver.findElementByCssSelector("a._1DLn60")
    brandsCategoryLink.click()

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Cameras")))
    val cameraCategoryLink = driver.findElementByLinkText("Cameras")
    cameraCategoryLink.click()

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Panasonic")))
    val panasonicCategoryLink = driver.findElementByLinkText("Panasonic")
    panasonicCategoryLink.click()

    /*--- Filter the results ----*/

    /*--- Based on category ---*/
    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._2YW4dZ")))
    val cameraCategory = driver.findElementByLinkText("Cameras")
    cameraCategory.click()
    webdriverwait.until(ExpectedConditions.urlContains("/cameras/"))

    /*--- Based on minimum price ---*/
        webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._1QaKk1 select.a_eCSK")))
        val minimumPriceSelect: WebElement  = driver.findElementByCssSelector("div._1QaKk1 select.a_eCSK")

        minimumPriceSelect.click
        webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._1QaKk1 select.a_eCSK option")))

        val minimumPriceOption: WebElement = minimumPriceSelect.findElement(By.cssSelector("option[value=\"15000\"]"))
        minimumPriceOption.click
        webdriverwait.until(ExpectedConditions.urlContains("15000"))

    /*--- Select item from result ---*/

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._2SxMvQ div.col")))
    val firstProduct2 = driver.findElementByCssSelector("div._2SxMvQ div.col.zZCdz4:nth-child(1) a")
    firstProduct2.click
    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._2SIJjY")))

    /*--- Enter the Area PIN code ---*/

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form.EJrIpC ")))

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._3l12t9")))

    /*--- Item isn't available on a PINCODE provide another one ---*/
    val pincodeMsg2 = driver.findElementByCssSelector("div._3l12t9")

    println("Msg--------------- " + pincodeMsg2.getText)

    if(pincodeMsg2.getText.contains("No sellers deliver in this area.") ) {

      val pincodeTextBox = driver.findElementByCssSelector("form.EJrIpC input._3X4tVa")
      println("Assigning the PINCODE as 110001")
      pincodeTextBox.clear()
      pincodeTextBox.sendKeys("110001") //Delhi Pincode
      pincodeTextBox.submit()
      println("Pincode submitted")
      webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._2h4rON")))
    }

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button._2AkmmA._3Plo8Q._16LyaZ._7UHT_c")))
    val addToCartButton2 = driver.findElementByCssSelector("button._2AkmmA._3Plo8Q._19RW-r")

    if(addToCartButton2.getText == "ADD TO CART"){
      addToCartButton2.click()
      webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.JAUzCh")))

      println("Camera Added to cart")
    }
    addToCartButton2.click()
    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button._2AkmmA._3qVHVC._7UHT_c")))
    val placeOrderBtn = driver.findElementByCssSelector("button._2AkmmA._3qVHVC._7UHT_c")

    placeOrderBtn.click()

    val name = "ashish tomer"
    val pincode = "110001"
    val address = "Khandahar Makaan, Purani Gali, Naya Mohalla, Kabristanpur"
    val landmark = "Peepal Ka Ped"
    val phone = "9458656298"

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.content li.ng-scope:nth-child(1)")))

    if(driver.findElementByCssSelector("a.collapsed-state.pure-g-r.ng-hide").isDisplayed) {
      val addressForm = driver.findElementByName("address.form")

      val nameInput = driver.findElementById("name")
      val pincodeInput = driver.findElementById("pincode")
      val addressInput = driver.findElementById("address")
      val landmarkInput = driver.findElementById("landmark")
      val phoneInput = driver.findElementById("phone")

      nameInput.clear()
      pincodeInput.clear()
      addressInput.clear()
      landmarkInput.clear()
      phoneInput.clear()

      nameInput.sendKeys(name)
      pincodeInput.sendKeys(pincode)
      addressInput.sendKeys(address)
      landmarkInput.sendKeys(landmark)
      phoneInput.sendKeys(phone)

      Thread.sleep(500) //Without this sleep, form doesn't submit because of race conditions
      addressForm.submit()
    }

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.btn.btn-orange.btn-large.btn-continue.no-underline")))

    val continueOrderBtn = driver.findElementByCssSelector("a.btn.btn-orange.btn-large.btn-continue.no-underline")

    continueOrderBtn.click()

    webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pure-u-1-5.link-container")))

    driver.executeScript("alert('We\\'re done running tests successfully')")

    Thread.sleep(1000) //This sleep is used to show the final result for a second
    driver.quit()
  }

}
