Feature:
The user want to buy product from tiki, he/ she select random item in Hot Deal category. And check out he/she order.


Scenario: Buy product from Tiki and return result 
Given The user is ready login to Tiki.vn and staying in homepage
When The user add a product on "Ưu đãi HOT" into shopping cart, and the user will see product on a shopping cart. 
Then The system wil be show notification "Đơn hàng của bạn đặt thành công" 


