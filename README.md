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

Realization:

Based on DBMS Postgresql. Settings at resources/postgres.properties/postgres.properties

Functionality description:
Realized 2 roles:
ROLE_ADMIN - admistrator;
ROLE_USER - user;

Realized rest services:
1. /rest/admin/users: work with users.
	- get: /{id} - get user by id; 
		Response: {"id":100001,"deletionMark":false,"description":"Admin","email":"admin@gmail.com","roles":["ROLE_ADMIN","ROLE_USER"]}
	- get: / - get list all users:
		Response: [{"id":100000,"deletionMark":false,"description":"User","email":"user@yandex.ru","roles":["ROLE_USER"]},{"id":100001,"deletionMark":false,"description":"Admin","email":"admin@gmail.com","roles":["ROLE_ADMIN","ROLE_USER"]},{"id":100016,"deletionMark":false,"description":"User 2","email":"user2@yandex.ru","roles":["ROLE_USER"]}]
	- get: /by - get user by parameter  email:
		Parameters = {email=[admin@gmail.com]}
		Response: {"id":100001,"deletionMark":false,"description":"Admin","email":"admin@gmail.com","roles":["ROLE_ADMIN","ROLE_USER"]}	
		
	- post:/ - create user; 
		Request: Body: {"deletionMark":false,"description":"New user","email":"new@gmail.com","roles":["ROLE_USER","ROLE_ADMIN"],"password":"newPass"}
		Response: {"id":100017,"deletionMark":false,"description":"Same admin","email":"new@gmail.com","roles":["ROLE_ADMIN","ROLE_USER"]}
		
	- put: /{id} - update user;
		Request: Body: {"id":100000,"deletionMark":false,"description":"Updated user name","email":"user@yandex.ru","roles":["ROLE_ADMIN"],"password":"password"}
	
	- put: /{id}/mark - mark to delete;	
	- put: /{id}/unmark - unmark to delete;
	
	- delete: /{id} - delete.
	
2. /rest/admin/restaurants: work with catalogue restaurants.
    - get: /{id} - get restaurant by id; 
		Response: {"id":100002,"deletionMark":false,"description":"First Restaurant"}
	- get: / - get list all restaurants:
		Response: list of restaurants.
		
	- post:/ - create restaurant; 
		Request: Body: {"deletionMark":false,"description":"First Restaurant"}
		Response: {"id":100018,"deletionMark":false,"description":"First Restaurant"}
		
	- put: /{id} - update restaurant;
		Request: Body: {"id":100018,"deletionMark":false,"description":"First Restaurant 2"}
	
	- put: /{id}/mark - mark to delete;	
	- put: /{id}/unmark - unmark to delete;
	
	- delete: /{id} - delete.
	
3. /rest/admin/lunchmenu: work with catalogue lunchmenu of restaurant.
	- get: /by - get list lunchmenu (restaurant may have many of lunch menu) by one restaurant:
		Parameters = {restaurant_id=[100002]}		
		Response: [{"id":100004,"deletionMark":false,"description":"Menu of First restaurant","restaurant_id":100002}]
	
	- get: / - get list all lunchmenu of all restaurants:
		Response: [{"id":100004,"deletionMark":false,"description":"Menu of First restaurant","restaurant_id":100002},{"id":100005,"deletionMark":false,"description":"Menu of Second restaurant","restaurant_id":100003}]
		
	- post: / - create lunchmenu of restaurant:
		Request: Body: {"deletionMark":false,"description":"New menu 1 restaurant","restaurant_id":100002}	
		Response: {"id":100017,"deletionMark":false,"description":"New menu 1 restaurant","restaurant_id":100002}
	
	- put: /{id} - udate lunchmenu of restaurant;
		Request: Body: {"id":100004,"deletionMark":false,"description":"New menu","restaurant_id":100002}
	
	- put: /{id}/mark - mark to delete;	
	- put: /{id}/unmark - unmark to delete;
	
	- delete: /{id} - delete.
	
4. /rest/admin/dish: work with catalogue dish of restaurant.
	- get: /by - get list all dishes concrete restaurant:
		Parameters = {restaurant_id=[100002]}
		Response: [{"id":100006,"deletionMark":false,"description":"Dish 1 of First restaurant","restaurant_id":100002,"price":100},{"id":100007,"deletionMark":false,"description":"Dish 2 of First restaurant","restaurant_id":100002,"price":200}]
	
	- get: /{id} - get disg (by id):
		Response: {"id":100006,"deletionMark":false,"description":"Dish 1 of First restaurant","restaurant_id":100002,"price":100}
	
	- get: / - get list dishes of all restaurants:
		Response: [{"id":100006,"deletionMark":false,"description":"Dish 1 of First restaurant","restaurant_id":100002,"price":100},{"id":100007,"deletionMark":false,"description":"Dish 2 of First restaurant","restaurant_id":100002,"price":200},{"id":100008,"deletionMark":false,"description":"Dish 1 of Second restaurant","restaurant_id":100003,"price":300}]
		
	- post: / - create dish of restaurant:
		Request: Body: {"deletionMark":false,"description":"New Dish 3 of First restaurant","restaurant_id":100002,"price":500}
		Response: {"id":100017,"deletionMark":false,"description":"New Dish 3 of First restaurant","restaurant_id":100002,"price":500}
	
	- put: /{id} - udate dish of restaurant;
		Request: Body: {"id":100004,"deletionMark":false,"description":"New menu","restaurant_id":100002}
	
	- put: /{id}/mark - mark to delete;	
	- put: /{id}/unmark - unmark to delete;
	
	- delete: /{id} - delete.

5. /rest/admin/menuconsist: work with menuconsist restaurant.
	- post: / - create/update menuconsist of restaurant:
		Request: 
			Parameters = {date=[2017-10-16], restaurant_id=[100002], lunchMenu_id=[100004]}
			Body: [{"date":"2017-10-16","restaurant_id":100002,"lunchMenu_id":100004,"dish_id":100006},{"date":"2017-10-16","restaurant_id":100002,"lunchMenu_id":100004,"dish_id":100007}]
		Response: [{"id":100017,"date":"2017-10-16","restaurant_id":100002,"lunchMenu_id":100004,"dish_id":100006},{"id":100018,"date":"2017-10-16","restaurant_id":100002,"lunchMenu_id":100004,"dish_id":100007}]
		The parameters must match the Body content, thus ensuring the integrity of the menu set.
		
	- get: / - get menuconsist of restaurant:
		Parameters = {date=[2017-10-16], restaurant_id=[100002], lunchMenu_id=[100004]}
		Response: [{"id":100009,"date":"2017-10-16","restaurant_id":100002,"lunchMenu_id":100004,"dish_id":100006},{"id":100010,"date":"2017-10-16","restaurant_id":100002,"lunchMenu_id":100004,"dish_id":100007}]
		
	- delete: / - delete menuconsist of restaurant:
		Parameters = {date=[2017-10-16], restaurant_id=[100002], lunchMenu_id=[100004]}
		
6. /rest/voting: work with voting for a restaurant.
	- post: / - register/update voting for a restaurant by the current user:
		Request: Body: {"date":"2017-10-10","restaurant_id":100003}
		Response: {"id":100017,"date":"2017-10-10","restaurant_id":100003}
		
	- get: / - get voting for a restaurant by the current user:
		Parameters = {date=[2017-10-16]}
		Response: {"id":100013,"date":"2017-10-16","restaurant_id":100002}
		
	- delete: / - delete voting for a restaurant:
		Parameters = {date=[2017-10-16], restaurant_id=[100002], lunchMenu_id=[100004]}
		Response: Parameters = {date=[2017-10-16]}
		
Role ROLE_ADMIN - all services are available.
Role ROLE_USER:
	Full forbidden to /rest/admin/users;
	Access allowed to /rest/admin на get;
	Full access to /rest/voting with restriction by the current user.
	
The setting of the voting prohibition in a current day is configured in the file: /resources/config/application.properties. Property format "ExpirationTimeVoting" is "HH:mm".

For logging, you must specify a system variable %VOTINGLUNCH%, in which must be the catalogue: log. Файл лога: votinglunch.log