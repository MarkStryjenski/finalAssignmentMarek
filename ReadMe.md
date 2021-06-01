# ReadMe for data processing final assignment 

#### Name: Marek Stryjenski

#### Student Number: 4716779

Installation Process:

1. Open the folder containing project in Intellij 

2. Make sure you have MongoDb Compass installed. If you do not have it installed yet, here is the link to the guide https://www.geeksforgeeks.org/install-mongodb-compass-on-windows/

3. After installing MongoDb Compass run it 

4. Create new Connection by clicking New Connection and next clicking connect. (Do not insert anything in the input field)

5. If connection was successful you should see the attached image. The highlighted area in red is your Port

6. ![mongoDbImg.png](imgs\mongoDbImg.png)

7. If your port is different then 27017 go to: src\main\resources\application.properties and change spring.data.mongodb.port=27017 to spring.data.mongodb.port=Your port number

8. Click the highlighted button and create new Database. Name it **Happiness** and collection name Covid

   ![mongoDbDatabase.png](imgs\mongoDbDatabase.png)

   

   

9. Click Plus symbol next to Happiness and create collection called InternetUsage

10. Create collection called WorldHappiness

11. Inside src\main\java\com.hapiness.dataprocessing\usedDataSets folder you will find three different data sets. Two of them will be in csv format and one in json. In the following steps you will import datasets to the MongoDb

12. Go to Happiness -> Covid and click on ADD DATA

13. Choose Import File

14. Select Json and click on browse and select Covid.json

15. Click Import

16. Go to InternetUsage Collection and click on ADD DATA

17. Choose Import File

18. Select this time csv and click on browse and select List of Countries by number of Internet Users.csv

19. Click Import

20. Go to WorldHappiness Collection and click on Add DATA

21. Choose Import File

22. Select this time csv and click on browse and World-Happiness-2020.csv

23. Click Import

24. Go to src\main\java\com.hapiness.dataprocessing\DataProcessingApplication.java

25. Run the DataProcessingApplication

26. You can make now Api calls in the postman 

**Important!!!!!!!!**

Before you do final step make sure you downloaded all the maven dependencies. This step is done usually automatically when you open the application

### Brief Description of application structure

- In the classes folder you will find class structure for all the datasets I use
- The controller folder contains Api calls for each data set I use. You can get all documents, get one document, insert new document, update existing document and delete existing document in each controller
- The repositories folder contains repositories and custom query calls I use for every data set 
- The json folder contains json structure for each dataset I use
- The jsonSchema folder contains jsonSchema  for each dataset I use
- The xml folder contains xml structure for each dataset I use. **<u>It also contains class XmlValidation that validates all xml files. To run make sure you run the main method of XmlValidation class.</u>**
- The xsd folder contains xsd structure for each dataset I use
- DataProcessingApplication is our main class that runs the application

### How to Display Charts

**In the src\main\resources\templates is the front-end code for the charts that my application displays**

To view the charts make sure the application is running and next

1. type in the url http://localhost:8080/home
2. There are two graphs. Bar-Graph presents combined data from all three data sets for the specified country in GraphController method barGraph(). The Pie-Chart Graph presents comparison between two countries in number of covid deaths . The countries are specified in GraphController method pieChart().



