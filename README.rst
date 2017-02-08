================
Task description
================

The script must implement the following steps:

1. Open the browser and maximize it.
2. Open  ss.lv
3. switch to Russian language.
4. Go to the section “Электротехника”, open search ('Поиск') and in Search enter the search phrase (eg. 'Компьютер') and select a different search parameters.
5. Click Search
6. Sort the results by price and select option 'Продажа' in "Тип сделки" dropdown.
7. Open “Расширенный поиск”. (advanced search)
8. Enter search option price between 0 and 300.
9. Choose at least 3 random ads.
10. Press “Добавить выбранные в закладки” ( = add to memo)
11. Open “Закладки” and check that the ads on the page match the previously selected
12. Close the browser.

========
Solution
========

Includes two variants:

- Classic **Page Object** pattern, and **maven** build manager.
- Steps implementation with **jbehave**, and **gradle** build manager.

All variants include next features and tools:

- ``Junit`` (http://junit.org/) - powerful framework for testing.
- ``Selenide`` (http://selenide.org/) - extremely excellent wrapper over selenium.
- ``Allure`` (http://allure.qatools.ru/) - great test reporter from Yandex.
- ``Video capture`` (http://automation-remarks.com/video-recorder-java/) - easy wrapper over ffmpeg (and other) to capture video of UI tests.
- ``Webdriver manager`` (https://github.com/bonigarcia/webdrivermanager/) - cool manager to get requested selenium webdriver.

In order to launch it requires installed software:

- ``java1.8, gradle3.3, maven3.3`` (tested)
- ``ffmpeg`` (to capture video)
- ``google-chrome`` (to launch UI tests)

More information about both variants you can get from corresponding README inside each project.

============
Nice to have
============

Some things, which would be nice to implement. But it is actual more for the test framework that for single test task:

- ``I18n`` - currently russian strings are hardcoded. To provide more flexibility it's good variant to choose messages according to language settings.
- ``Logging`` - to capture information about what happens in code level. Allure is nice, but logs never hurt.
- ``Xvfb`` - to launch tests in headless mode on CI.
