package com.virtusa;

import io.github.bonigarcia.wdm.WebDriverManager;

//import org.junit.Assert;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumTests {

    String url="https://www.automationanywhere.com/";
    String browser="edge"; //edge,chrome
    public static WebDriver driver;

        @Test
        public void launchBrowserVerifyLogoDisplayedTestCase(){
            System.out.println("browser :- "+browser);
            System.out.println("url :- "+url);

            switch(browser){
                case "chrome":
                    ChromeOptions chromeOptions=new ChromeOptions();
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "edge":
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(options);
                    break;
                default:
                    Assertions.fail("Provide correct Browser name(chrome/edge) to execute, currently provided browser name is :- "+browser);
//                Assert.fail("Provide correct Browser name(chrome/edge) to execute, currently provided browser name is :- "+browser);
            }



        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(190));
        driver.get(url);
        //Close cookie accept
        if(driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).isDisplayed()){
            System.out.println("Clicked cookie accept");
            driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();
            driver.manage().window().maximize();

        }else{
            System.out.println("Not displayed Clicked cookie accept");
        }

       boolean blnStatus = driver.findElement(By.xpath("//img[@class='coh-image coh-image-responsive-xl' and contains(@alt,'Automation Anywhere')]")).isDisplayed();
       if(blnStatus){
           System.out.println("Able to see the automationanywhere Logo image is displayed");
       }else{
           System.out.println("Not Able to see the automationanywhere Logo image  displayed");
       }
//       Assert.assertTrue("Logo is not displayed",blnStatus);
       Assertions.assertTrue(blnStatus,"Logo is not displayed");

       //Logo is displayed
        blnStatus =driver.findElement(By.xpath("//a[@title='Request Demo' and contains(@class,'coh-link utility-nav-link coh-style-solid-orange-button')]")).isEnabled();
        if(blnStatus){
            System.out.println("Able to see the automationanywhere 'Request Demo' button enabled");
        }else{
            System.out.println("Not Able to see the automationanywhere 'Request Demo' button not enabled");
        }
//        Assert.assertTrue("Logo is not displayed",blnStatus);
        Assertions.assertTrue(blnStatus,"Logo is not displayed");


//        //Navigate to correct page
//        String[] linkToClick=new String[]{"Products","Solutions","Resources","Beyond RPA","Company"};
//        String[] pageTitle=new String[]{"Scale Your Business with Enterprise Automation","Tailored Automation Solutions",
//                "Automation Resources","Optimize Efficiency with Business Process Automation","Intelligent Automation Companies"};
//
//                List<WebElement> webElements = driver.findElements(By.xpath("//div[contains(@class,'coh-column primary-menu')]/ul/li/a"));
//       for(int i=0; i<webElements.size();i++){
//           System.out.println("Link Displayed :- "+webElements.get(i).getAttribute("text"));
//           if(webElements.get(i).getAttribute("text").equalsIgnoreCase(linkToClick[i])){
//               System.out.println("Menu Item displayed");
//           }else{
//               System.out.println("Menu Not Item displayed");
//           }
//       }
//
//
//
//
//        for(int i=0;i<linkToClick.length;i++){
//            System.out.println("Navigating to page:-"+linkToClick[i]);
//            driver.findElement(By.xpath("//div[contains(@class,'coh-column primary-menu')]/ul/li/a[contains(text(),'"+linkToClick[i]+"')]")).click();
//
//            System.out.println("Page Title:-"+driver.getTitle());
//            if(driver.getTitle().contains(pageTitle[i])){
//                System.out.println("Page Title matched:- Actual Page Title :-"+driver.getTitle() +" Expected Page Title :-"+pageTitle[i]);
//            }else{
//                System.out.println("Page Title not matched:- Actual Page Title :-"+driver.getTitle() +" Expected Page Title :-"+pageTitle[i]);
//            }
//        }
//

//       driver.quit();

    }

        @Test
        public void verifyMenuItemsDisplayedAndAbleToNavigatetoExpectedPageTestCase() {
            //Navigate to correct page
            String[] linkToClick=new String[]{"Products","Solutions","Resources","Beyond RPA","Company"};
            String[] pageTitle=new String[]{"Scale Your Business with Enterprise Automation","Tailored Automation Solutions",
                    "Automation Resources","Optimize Efficiency with Business Process Automation","Intelligent Automation Companies"};
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(90));
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'coh-column primary-menu')]/ul/li/a")));
            //Menu Items Displayed
            List<WebElement> webElements = driver.findElements(By.xpath("//div[contains(@class,'coh-column primary-menu')]/ul/li/a"));
            for(int i=0; i<webElements.size();i++){
                System.out.println("Link Displayed :- "+webElements.get(i).getAttribute("text"));
                if(webElements.get(i).getAttribute("text").equalsIgnoreCase(linkToClick[i])){
                    System.out.println("Menu Item displayed");
                }else{
                    System.out.println("Menu Not Item displayed");
                }
                boolean blnStatusMenuItemDisplayed=webElements.get(i).getAttribute("text").equalsIgnoreCase(linkToClick[i]);
//                Assert.assertTrue("Menu Item:-'"+linkToClick[i]+"' is not displayed",blnStatusMenuItemDisplayed);
                Assertions.assertTrue(blnStatusMenuItemDisplayed,"Menu Item:-'"+linkToClick[i]+"' is not displayed");
            }



            //Able to click menu item and able to navigate to new page and able to see expected page title displayed
            for(int i=0;i<linkToClick.length;i++){
                System.out.println("Navigating to page:-"+linkToClick[i]);
                driver.findElement(By.xpath("//div[contains(@class,'coh-column primary-menu')]/ul/li/a[contains(text(),'"+linkToClick[i]+"')]")).click();

                System.out.println("Page Title:-"+driver.getTitle());
                if(driver.getTitle().contains(pageTitle[i])){
                    System.out.println("Page Title matched:- Actual Page Title :-"+driver.getTitle() +" Expected Page Title :-"+pageTitle[i]);
                }else{
                    System.out.println("Page Title not matched:- Actual Page Title :-"+driver.getTitle() +" Expected Page Title :-"+pageTitle[i]);
                }
                boolean blnPageTitleDisplayed=driver.getTitle().contains(pageTitle[i]);
//                Assert.assertTrue("Not navigated to expected page:-"+pageTitle[i]+" on clicking menu item:-'"+linkToClick[i]+"' ",blnPageTitleDisplayed);
                Assertions.assertTrue(blnPageTitleDisplayed,"Not navigated to expected page:-"+pageTitle[i]+" on clicking menu item:-'"+linkToClick[i]+"' ");

            }

            driver.quit();
        }


}
