# Voting lunch

API for voting system for deciding where to have lunch.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

Реализация:

Основано на СУБД Postgresql. Настройки в resources/postgres.properties/postgres.properties

Описание функциональности:
Реализованы 2 роли:
ROLE_ADMIN - администратор;
ROLE_USER - пользователь;

Реализованы rest сервисы:
1. /rest/admin/users: работа с пользователями.
	- get: /{id} - получение пользователя по id; 
		Response: {"id":100001,"deletionMark":false,"description":"Admin","email":"admin@gmail.com","roles":["ROLE_ADMIN","ROLE_USER"]}
	- get: / - получение списка всех пользователей:
		Response: [{"id":100000,"deletionMark":false,"description":"User","email":"user@yandex.ru","roles":["ROLE_USER"]},{"id":100001,"deletionMark":false,"description":"Admin","email":"admin@gmail.com","roles":["ROLE_ADMIN","ROLE_USER"]},{"id":100016,"deletionMark":false,"description":"User 2","email":"user2@yandex.ru","roles":["ROLE_USER"]}]
	- get: /by - получение пользователя по параметру email:
		Parameters = {email=[admin@gmail.com]}
		Response: {"id":100001,"deletionMark":false,"description":"Admin","email":"admin@gmail.com","roles":["ROLE_ADMIN","ROLE_USER"]}	
		
	- post:/ - создание пользователя; 
		Request: Body: {"deletionMark":false,"description":"New user","email":"new@gmail.com","roles":["ROLE_USER","ROLE_ADMIN"],"password":"newPass"}
		Response: {"id":100017,"deletionMark":false,"description":"Same admin","email":"new@gmail.com","roles":["ROLE_ADMIN","ROLE_USER"]}
		
	- put: /{id} - обновление пользователя;
		Request: Body: {"id":100000,"deletionMark":false,"description":"Updated user name","email":"user@yandex.ru","roles":["ROLE_ADMIN"],"password":"password"}
	
	- put: /{id}/mark - пометить на удаление;	
	- put: /{id}/unmark - убрать пометку на удаление;
	
	- delete: /{id} - удаление.
	
2. /rest/admin/restaurants: работа со справочником рестораны.
    - get: /{id} - получение ресторана по id; 
		Response: {"id":100002,"deletionMark":false,"description":"First Restaurant"}
	- get: / - получение списка всех ресторанов:
		Response: список реcторанов.
		
	- post:/ - создание ресторана; 
		Request: Body: {"deletionMark":false,"description":"First Restaurant"}
		Response: {"id":100018,"deletionMark":false,"description":"First Restaurant"}
		
	- put: /{id} - обновление ресторана;
		Request: Body: {"id":100018,"deletionMark":false,"description":"First Restaurant 2"}
	
	- put: /{id}/mark - пометить на удаление;	
	- put: /{id}/unmark - убрать пометку на удаление;
	
	- delete: /{id} - удаление.
	
3. /rest/admin/lunchmenu: работа со справочником меню ресторана.
	- get: /by - получение списка меню (у ресторана может быть несколько меню) по одному ресторану:
		Parameters = {restaurant_id=[100002]}		
		Response: [{"id":100004,"deletionMark":false,"description":"Menu of First restaurant","restaurant_id":100002}]
	
	- get: / - получение списка меню всех ресторанов:
		Response: [{"id":100004,"deletionMark":false,"description":"Menu of First restaurant","restaurant_id":100002},{"id":100005,"deletionMark":false,"description":"Menu of Second restaurant","restaurant_id":100003}]
		
	- post: / - создание меню ресторана:
		Request: Body: {"deletionMark":false,"description":"New menu 1 restaurant","restaurant_id":100002}	
		Response: {"id":100017,"deletionMark":false,"description":"New menu 1 restaurant","restaurant_id":100002}
	
	- put: /{id} - обновление меню рестора;
		Request: Body: {"id":100004,"deletionMark":false,"description":"New menu","restaurant_id":100002}
	
	- put: /{id}/mark - пометить на удаление;	
	- put: /{id}/unmark - убрать пометку на удаление;
	
	- delete: /{id} - удаление.
	
4. /rest/admin/dish: работа со справочником блюд ресторана.
	- get: /by - получение всех блюд конкретного ресторана:
		Parameters = {restaurant_id=[100002]}
		Response: [{"id":100006,"deletionMark":false,"description":"Dish 1 of First restaurant","restaurant_id":100002,"price":100},{"id":100007,"deletionMark":false,"description":"Dish 2 of First restaurant","restaurant_id":100002,"price":200}]
	
	- get: /{id} - получение блюда (по id):
		Response: {"id":100006,"deletionMark":false,"description":"Dish 1 of First restaurant","restaurant_id":100002,"price":100}
	
	- get: / - получение списка блюд всех ресторанов:
		Response: [{"id":100006,"deletionMark":false,"description":"Dish 1 of First restaurant","restaurant_id":100002,"price":100},{"id":100007,"deletionMark":false,"description":"Dish 2 of First restaurant","restaurant_id":100002,"price":200},{"id":100008,"deletionMark":false,"description":"Dish 1 of Second restaurant","restaurant_id":100003,"price":300}]
		
	- post: / - создание блюда ресторана:
		Request: Body: {"deletionMark":false,"description":"New Dish 3 of First restaurant","restaurant_id":100002,"price":500}
		Response: {"id":100017,"deletionMark":false,"description":"New Dish 3 of First restaurant","restaurant_id":100002,"price":500}
	
	- put: /{id} - обновление меню рестора;
		Request: Body: {"id":100004,"deletionMark":false,"description":"New menu","restaurant_id":100002}
	
	- put: /{id}/mark - пометить на удаление;	
	- put: /{id}/unmark - убрать пометку на удаление;
	
	- delete: /{id} - удаление.

5. /rest/admin/menuconsist: работа с составом меню ресторана.
	- post: / - создание/обновление состава меню ресторана:
		Request: 
			Parameters = {date=[2017-10-16], restaurant_id=[100002], lunchMenu_id=[100004]}
			Body: [{"date":"2017-10-16","restaurant_id":100002,"lunchMenu_id":100004,"dish_id":100006},{"date":"2017-10-16","restaurant_id":100002,"lunchMenu_id":100004,"dish_id":100007}]
		Response: [{"id":100017,"date":"2017-10-16","restaurant_id":100002,"lunchMenu_id":100004,"dish_id":100006},{"id":100018,"date":"2017-10-16","restaurant_id":100002,"lunchMenu_id":100004,"dish_id":100007}]
		Параметры должны обязательно совпадать с содержанием Body, таким образом гарантируется целостность набора меню.
		
	- get: / - получение состава меню ресторана:
		Parameters = {date=[2017-10-16], restaurant_id=[100002], lunchMenu_id=[100004]}
		Response: [{"id":100009,"date":"2017-10-16","restaurant_id":100002,"lunchMenu_id":100004,"dish_id":100006},{"id":100010,"date":"2017-10-16","restaurant_id":100002,"lunchMenu_id":100004,"dish_id":100007}]
		
	- delete: / - удаление состава меню ресторана:
		Parameters = {date=[2017-10-16], restaurant_id=[100002], lunchMenu_id=[100004]}
		
6. /rest/voting: работа с голосованием за ресторан.
	- post: / - учет/обновление голосования за ресторан текущим пользователем:
		Request: Body: {"date":"2017-10-10","restaurant_id":100003}
		Response: {"id":100017,"date":"2017-10-10","restaurant_id":100003}
		
	- get: / - получение голоса за ресторан текущим пользователем:
		Parameters = {date=[2017-10-16]}
		Response: {"id":100013,"date":"2017-10-16","restaurant_id":100002}
		
	- delete: / - удаление голоса за ресторан:
		Parameters = {date=[2017-10-16], restaurant_id=[100002], lunchMenu_id=[100004]}
		Response: Parameters = {date=[2017-10-16]}
		
Роли ROLE_ADMIN доступны все сервисы.
Роли ROLE_USER:
	Полный запрет к /rest/admin/users;
	Разрешен доступ на /rest/admin на get;
	Полный доступ на /rest/voting с ограничением по текущему пользователю.
	
Настрока запрета голосовния в текущем дне настраивается в файле: /resources/config/application.properties. Свойство ExpirationTimeVoting указывается в HH:mm

Для логирования необходимо указать системную переменную %VOTINGLUNCH%, в которой должен быть каталог: log. Файл лога: votinglunch.log