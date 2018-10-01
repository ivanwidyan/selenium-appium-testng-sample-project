@LoginProfile
Feature: Login Profile
  As a user of the Blibli applications
  I want to login my member profile using my credentials
  In order to access privileges as a member

  Background: User login via Blibli Home page
    Given I am on the "Home" page on URL "https://www.blibli.com"
    Then I click on the "Masuk" button

  Scenario: Successful login
    When I fill in "Masukan Email" with "emailforautomatetesting@gmail.com"
    And I fill in "Kata Sandi" with "emailforautomatetesting123"
    And I click on the "Masuk" button
    Then I am on the "Profile" page on URL "https://www.blibli.com/member/profile"
    And I should see "Account For" message
    And I should see the "Keluar" button
    And I click on the "Keluar" button

  Scenario Outline: Failed login using wrong credentials
    When I fill in "Masukan Email" with "<email>"
    And I fill in "Kata Sandi" with "<password>"
    And I click on the "Masuk" button
    And I should see "<warning>" message
    Examples:
      | email          | password | warning                                             |
      | test           | 1234     | Format email yang Anda masukkan salah               |
      | test@test      | 1234     | Format email yang Anda masukkan salah               |
      | test@test.test | 1234     | Maaf, Email atau password yang Anda masukkan salah. |
      | test@test      |          | Email atau Kata Sandi tidak boleh kosong            |
      | test@test.test |          | Email atau Kata Sandi tidak boleh kosong            |
      |                | 123      | Email atau Kata Sandi tidak boleh kosong            |
      |                |          | Email atau Kata Sandi tidak boleh kosong            |