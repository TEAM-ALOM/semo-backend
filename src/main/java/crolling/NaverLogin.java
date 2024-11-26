package crolling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NaverLogin {

    private WebDriver driver;
    private WebElement element;
    private String url;

    public NaverLogin() {
        // WebDriverManager로 드라이버 관리
        WebDriverManager.chromedriver().driverVersion("131.0.6778.69").setup();


        // ChromeOptions 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");

        // 드라이버 생성
        driver = new ChromeDriver(options);

        url = "https://www.naver.com/";
    }

    public void activateBot() {
        try {
            driver.get(url);
            Thread.sleep(2000); // 페이지 로딩 대기

            // 로그인 버튼 클릭
            element = driver.findElement(By.className("link_login"));
            element.click();

            Thread.sleep(1000);

            // ID 입력
            element = driver.findElement(By.id("id"));
            element.sendKeys("아이디입니다");

            // 비밀번호 입력
            element = driver.findElement(By.id("pw"));
            element.sendKeys("비밀번호입니다");

            // 로그인 버튼 클릭
            element = driver.findElement(By.className("btn_global"));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 드라이버 종료
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void main(String[] args) {
        NaverLogin bot = new NaverLogin();
        bot.activateBot();
    }
}
