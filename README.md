# Coding Challenge - RMM Platform.

## Introduction
A Remote Monitoring and Management (RMM) platform helps IT professionals manage a
fleet of Devices with Services associated with them. This Web Service will fulfill the most
basic requirements of an RMM by keeping a simple inventory of Devices and Services to
calculate their total costs.


## Starting the Application

The project is configured to use an in-memory H2 database that is volatile. If you wish to make it maintain data on application shut down, you can change the spring.database.jdbc-url to point at a file like `jdbc:h2:file:/{your file path here}`

Run the `BackendInterviewProjectApplication` class

Go to:
* http://localhost:8080/sample/1
* http://localhost:8080/sample/2

You should see results for both of these. The application is working and connected to the H2 database. 

## H2 Console 

In order to see and interact with your db, access the h2 console in your browser.
After running the application, go to:

http://localhost:8080/h2-console

Enter the information for the url, username, and password in the application.yml:

```yml
url: jdbc:h2:mem:localdb
username: sa 
password: password
```

You should be able to see a db console now that has the Sample Repository in it.

## STRUCTURE

 I set as basic entites 
| Entity | Description |
| ------ | ------ |
| Devices | represents the device. this includes properties like id, system name, type |
| Services | It is defined as Service Detail because by naming it only as a service we fell into the use of reserved words. it represents the service and includes properties like id, name, type, cost |

For each entity you will find : repository,service and controller class respectively

I also include a relation between Devices and Services so as a result I have the service by device relation
it is  ManytoMany relationship (bidirectional relation)  for both classes and it appears at the database as:

| Table | Description |
| ------ | ------ |
| Service by Device | it storage the id for devices and the id for the services uses for this device  |

## ENDPOINT DETAIL

FOR DEVICES 

| Endpoint | HTTP METHOD| Description |
| ------ | ------ | ------ |
|/devices | POST  | create a device |
|/devices/{id} | PUT  | update a device, send in the body the hole device |
|/devices/{id} | DELETE  | delete a device |
|/devices | GET  | find all the devices |
|/devices/{id} | GET  | find a device by id |

FOR SERVICES 

| Endpoint | HTTP METHOD| Description |
| ------ | ------ | ------ |
|/services | POST  | create a service |
|/services/{id} | PUT  | update a service, send in the body the hole service |
|/services/{id} | DELETE  | delete a service |
|/services | GET  | find all the service |
|/services/{id} | GET  | find a service by id |

FOR SERVICES BY DEVICE

| Endpoint | HTTP METHOD| Description |
| ------ | ------ | ------ |
|/serviceByDevice/servicesCost | GET  |Gets the total services cost for the all the services registered in the db |
|/serviceByDevice/servicesCostByDevices | GET  | Gets the total services cost for all the devices registered in the db |
|/serviceByDevice/totalServiceCostByDevices | POST  | Calculate total services cost for a receive devices list |
|/serviceByDevice/totalServiceCostByServices | POST  | Calculate total services cost for a receive services list |
