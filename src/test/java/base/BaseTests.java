package base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver(getChromeoptions());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        goHome();
        homePage = new HomePage(driver);
    }

    private void goHome(){
        String websiteURL = "https://magento.softwaretestingboard.com/";
        driver.get(websiteURL);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    private ChromeOptions getChromeoptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("autofill.profile_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        return chromeOptions;
    }

    public HashMap<String, String> getRegisterCredentials(){
        ObjectMapper objectMapper = new ObjectMapper();
        String registerCredentialsFilePath = "src/test/resources/RegisterCredentials.json";

        try {
            HashMap<String, String> credentials = objectMapper.readValue(new File(registerCredentialsFilePath),
                    new TypeReference<HashMap<String, String>>() {});
            String email = credentials.get("email") + UUID.randomUUID().toString().substring(0, 3) + "@email.com";
            credentials.put("email", email);
            return credentials;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addLoginCredential(HashMap<String, String> newCredentials){
        ObjectMapper objectMapper = new ObjectMapper();
        String loginCredentialsFilePath = "src/test/resources/LoginCredentials.json";
        try {
            List<HashMap<String, String>> credentialsList = objectMapper.readValue(
                    new File(loginCredentialsFilePath),
                    new TypeReference<List<HashMap<String, String>>>() {});
            credentialsList.add(newCredentials);
            objectMapper.writeValue(new File(loginCredentialsFilePath), credentialsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public HashMap<String, String> getLoginCredentials() {
        ObjectMapper objectMapper = new ObjectMapper();
        String loginCredentialsFilePath = "src/test/resources/LoginCredentials.json";
        try {
            List<HashMap<String, String>> credentialsList = objectMapper.readValue(
                    new File(loginCredentialsFilePath),
                    new TypeReference<List<HashMap<String, String>>>() {});

            if (!credentialsList.isEmpty()) {
                return credentialsList.get(credentialsList.size()-1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HashMap<String, String>> getAllLoginCredentials() {
        ObjectMapper objectMapper = new ObjectMapper();
        String loginCredentialsFilePath = "src/test/resources/LoginCredentials.json";
        try {
            List<HashMap<String, String>> credentialsList = objectMapper.readValue(
                    new File(loginCredentialsFilePath),
                    new TypeReference<List<HashMap<String, String>>>() {});

            if (!credentialsList.isEmpty()) {
                return credentialsList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HashMap<String, String>> getAddressesData() {
        ObjectMapper objectMapper = new ObjectMapper();
        String addressesDataFilePath = "src/test/resources/AddressesData.json";
        try {
            List<HashMap<String, String>> addressesData = objectMapper.readValue(
                    new File(addressesDataFilePath),
                    new TypeReference<List<HashMap<String, String>>>() {});
            if (!addressesData.isEmpty()) {
                return addressesData;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateLoginCredentials(String newEmail, String newPassword) {
        ObjectMapper objectMapper = new ObjectMapper();
        String loginCredentialsFilePath = "src/test/resources/LoginCredentials.json";

        try {
            List<HashMap<String, String>> credentialsList = objectMapper.readValue(
                    new File(loginCredentialsFilePath),
                    new TypeReference<List<HashMap<String, String>>>() {});
            HashMap<String, String> credentials = credentialsList.get(credentialsList.size()-1);
            credentials.put("email", newEmail);
            if(!Objects.equals(newPassword, credentials.get("password"))) {
                credentials.put("newPassword", credentials.get("password"));
                credentials.put("password", newPassword);
            }
            credentialsList.remove(credentialsList.size()-1);
            credentialsList.add(credentials);

            objectMapper.writeValue(new File(loginCredentialsFilePath), credentialsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
