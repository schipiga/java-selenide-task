Meta:

Narrative:
As a curious user
I want to look through computer adverts
So that I can choose a computer for me

Scenario: scenario description
Given I navigate to http://ss.lv in browser
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
