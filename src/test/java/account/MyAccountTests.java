package account;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AccountAddressBookPage;
import pages.AccountInformationPage;
import pages.LoginPage;
import pages.MyAccountPage;

import java.util.HashMap;
import java.util.List;

public class MyAccountTests extends BaseTests {

    @Test(priority = 1)
    public void changeEmailWithValidEmail(){
        HashMap<String, String> loginCredentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(loginCredentials.get("email"));
        loginPage.setPassword(loginCredentials.get("password"));
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        AccountInformationPage accountInformationPage = myAccountPage.clickEditContactInfoButton();
        accountInformationPage.checkChangeEmail();
        String newEmail = getRegisterCredentials().get("email");
//        String newEmail = loginCredentials.get("email");
        accountInformationPage.setNewEmail(newEmail);
        accountInformationPage.setCurrentPassword(loginCredentials.get("password"));
        accountInformationPage.clickSave();
        Assert.assertEquals(accountInformationPage.getInfoSavedMessage(), "You saved the account information.", "Email wasn't changed successfully");
        updateLoginCredentials(newEmail, loginCredentials.get("password"));
    }

    @Test(priority = 2)
    public void changeEmailWithExistingEmail(){
        List<HashMap<String, String>> allLoginCredentials = getAllLoginCredentials();
        HashMap<String, String> loginCredentials = allLoginCredentials.get(allLoginCredentials.size() - 1);
        String existingLoginEmail = allLoginCredentials.get(allLoginCredentials.size() - 2).get("email");
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(loginCredentials.get("email"));
        loginPage.setPassword(loginCredentials.get("password"));
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        AccountInformationPage accountInformationPage = myAccountPage.clickEditContactInfoButton();
        accountInformationPage.checkChangeEmail();
        accountInformationPage.setNewEmail(existingLoginEmail);
        accountInformationPage.setCurrentPassword(loginCredentials.get("password"));
        accountInformationPage.clickSave();
        Assert.assertEquals(accountInformationPage.getInfoSavedErrorMessage(), "A customer with the same email address already exists in an associated website.", "Email already exists error message didn't appear");
        homePage.logOut();
    }

    @Test(priority = 3)
    public void changeEmailWithInvalidEmail(){
        HashMap<String, String> loginCredentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(loginCredentials.get("email"));
        loginPage.setPassword(loginCredentials.get("password"));
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        AccountInformationPage accountInformationPage = myAccountPage.clickEditContactInfoButton();
        accountInformationPage.checkChangeEmail();
        accountInformationPage.setNewEmail("fake.com");
        accountInformationPage.setCurrentPassword(loginCredentials.get("password"));
        accountInformationPage.clickSave();
        Assert.assertEquals(accountInformationPage.getEmailErrorMessage(), "Please enter a valid email address.", "Invalid email error message didn't appear");
        homePage.logOut();
    }

    @Test(priority = 4)
    public void changePasswordWithValidPassword(){
        HashMap<String, String> loginCredentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(loginCredentials.get("email"));
        loginPage.setPassword(loginCredentials.get("password"));
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        //TODO use data driven
        String newPassword = loginCredentials.get("newPassword");
        AccountInformationPage accountInformationPage = myAccountPage.clickChangePasswordButton();
        accountInformationPage.setCurrentPassword(loginCredentials.get("password"));
        accountInformationPage.setNewPassword(newPassword);
        accountInformationPage.setConfirmNewPassword(newPassword);
        accountInformationPage.clickSave();
        Assert.assertEquals(accountInformationPage.getInfoSavedMessage(), "You saved the account information.", "Password change unsuccessful");
        updateLoginCredentials(loginCredentials.get("email"), newPassword);
    }

    @Test(priority = 5)
    public void changePasswordWithInvalidPassword(){
        HashMap<String, String> loginCredentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(loginCredentials.get("email"));
        loginPage.setPassword(loginCredentials.get("password"));
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        AccountInformationPage accountInformationPage = myAccountPage.clickChangePasswordButton();
        accountInformationPage.setCurrentPassword(loginCredentials.get("password"));
        accountInformationPage.setNewPassword("123 ");
        accountInformationPage.setConfirmNewPassword("12345");
        accountInformationPage.clickSave();
        Assert.assertEquals(accountInformationPage.getNewPasswordErrorMessage(), "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.", "New password error message didn't appear");
        Assert.assertEquals(accountInformationPage.getConfirmNewPasswordErrorMessage(), "Please enter the same value again.", "Confirm new password error message didn't appear");
        homePage.logOut();
    }

    @Test(priority = 6)
    public void addFirstAddressToAddressBook(){
        HashMap<String, String> loginCredentials = getLoginCredentials();
        HashMap<String, String> addressData = getAddressesData().get(0);
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(loginCredentials.get("email"));
        loginPage.setPassword(loginCredentials.get("password"));
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        AccountAddressBookPage accountAddressBookPage = myAccountPage.clickAddressBookButton();
        accountAddressBookPage.setCompany(addressData.get("company"));
        accountAddressBookPage.setPhone(addressData.get("phone"));
        accountAddressBookPage.setStreetAddress1(addressData.get("street1"));
        accountAddressBookPage.setStreetAddress2(addressData.get("street2"));
        accountAddressBookPage.setStreetAddress3(addressData.get("street3"));
        accountAddressBookPage.setCity(addressData.get("city"));
        accountAddressBookPage.setZipPostalCode(addressData.get("zipcode"));
        accountAddressBookPage.selectCountry(addressData.get("country"));
        accountAddressBookPage.setStateProvince(addressData.get("state"));
        accountAddressBookPage.clickSaveAddressButton();
        Assert.assertEquals(accountAddressBookPage.getAddressSavedSuccessMessage(), "You saved the address.", "Address wasn't saved successfully");
        homePage.logOut();
    }

    @Test(priority = 7, dataProvider = "getAdditionalAddressesData")
    public void addNewAddressesToAddressBook(HashMap<String, String> addressData){
        HashMap<String, String> loginCredentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(loginCredentials.get("email"));
        loginPage.setPassword(loginCredentials.get("password"));
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        AccountAddressBookPage accountAddressBookPage = myAccountPage.clickAddressBookButton();
        accountAddressBookPage.clickAddNewAddressButton();
        accountAddressBookPage.setCompany(addressData.get("company"));
        accountAddressBookPage.setPhone(addressData.get("phone"));
        accountAddressBookPage.setStreetAddress1(addressData.get("street1"));
        accountAddressBookPage.setStreetAddress2(addressData.get("street2"));
        accountAddressBookPage.setStreetAddress3(addressData.get("street3"));
        accountAddressBookPage.setCity(addressData.get("city"));
        accountAddressBookPage.setZipPostalCode(addressData.get("zipcode"));
        accountAddressBookPage.selectCountry(addressData.get("country"));
        accountAddressBookPage.setStateProvince(addressData.get("state"));
        accountAddressBookPage.clickSaveAddressButton();
        Assert.assertEquals(accountAddressBookPage.getAddressSavedSuccessMessage(), "You saved the address.", "Address wasn't saved successfully");
        homePage.logOut();
    }

    @Test(priority = 8, dataProvider = "defaultAddressesUsageChange")
    public void editAdditionalAddresses(String usage){
        HashMap<String, String> loginCredentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(loginCredentials.get("email"));
        loginPage.setPassword(loginCredentials.get("password"));
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        AccountAddressBookPage accountAddressBookPage = myAccountPage.clickAddressBookButton();
        accountAddressBookPage.clickAdditionalAddress1EditButton();
        accountAddressBookPage.changeAddressUsage(usage);
        accountAddressBookPage.clickSaveAddressButton();
        Assert.assertEquals(accountAddressBookPage.getAddressSavedSuccessMessage(), "You saved the address.", "Address wasn't saved successfully");
        homePage.logOut();
    }

    @Test(priority = 9)
    public void deleteAdditionalAddress(){
        HashMap<String, String> loginCredentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(loginCredentials.get("email"));
        loginPage.setPassword(loginCredentials.get("password"));
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        AccountAddressBookPage accountAddressBookPage = myAccountPage.clickAddressBookButton();
        accountAddressBookPage.clickAdditionalAddress1DeleteButton();
        accountAddressBookPage.clickConfirmAdditionalAddressDeletion();
        Assert.assertEquals(accountAddressBookPage.getAddressSavedSuccessMessage(), "You deleted the address.", "Additional address wasn't deleted successfully");
        homePage.logOut();
    }

    @DataProvider
    public HashMap<String, String>[] getAdditionalAddressesData(){
        List<HashMap<String, String>> additionalAddressesData = getAddressesData();
        HashMap<String, String>[] additionalAddressesArray = new HashMap[2];
        additionalAddressesArray[0] = additionalAddressesData.get(1);
        additionalAddressesArray[1] = additionalAddressesData.get(2);
        return additionalAddressesArray;
    }

    @DataProvider
    public String[] defaultAddressesUsageChange(){
        String[] usages = new String[2];
        usages[0] = "primary_billing";
        usages[1] = "primary_shipping";
        return usages;
    }
}
