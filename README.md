# MyFirstSQL
Using mySQL as database and spring boot framework to get and post data into the mySQL database  
  
## API  
All data will be sent and accepted in json format  
GET /books : retrieving book data from mySQL database with default limit of 10 and offset of 10  
GET /books?limit=20 : retrieving book data from mySQL database with limit of 20 and default offset of 10  
GET /books?limit=20&offset=15 : retrieving book data from mySQL database with limit of 20 and offset of 15  
POST /books : in json format such as {"bookid": "123", "title":"test", "pages":30, "author":"ys"}  
