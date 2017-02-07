Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: scenario description
Given I open a browser
And I go to http://ss.lv
And I change language to RU
And I click section Электротехника
And I open search page
And I search Компьютер with min cost 100 with max cost 1000 with region Рига with period За последний месяц
And I sort results by Цена
And I select deal type as Продажа
And I open expanded search
And I search with min cost 0 with max cost 300
And I select randomly 3 adverts
And I open bookmarks page
And I add adverts to favourites
When I open bookmarks page
Then I see that my bookmarks match previously selected adverts
