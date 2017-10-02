Required entities:

1. Roles:
  - admin
  - user
  
BaseEntity:
  - id
  - description 
  - deletionMark
  
Users extends BaseEntity:
  - email;
  - Consist roles (ConsistRoles):
    - role_id
  
Restaurants extends BaseEntity:
  - Adress; 
  
LunchMenu extends BaseEntity:
  - restaurant_id

Dish extends BaseEntity:
  - menu_id

MenuConsist extends BaseEntity:
  - date (without time)
  - restaurant_id
  - menu_id
  - dish_id
clustered index: date, restaurant_id, menu_id, dish_id

Voting:
  - date (without time)
  - restaurant_id
  - user_id;
clustered index: date, restaurant_id, user_id
  


  

  
