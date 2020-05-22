Project is build with maven. It contains 3 web services as project submodules and a database component. Each one is run as docker containerusing docker-compose.
To run project proceed one of the following options:
Execute command from base project directory: <project_dir>\EvaluationBank
a)	1. mvn clean package
	2. mvn verify
or
b) 	1. mvn clean package
	2. docker-compose up --build

Project endpoints:
POST /credit/createCredit		
GET /credit/getCredits			
POST /customer/createCustomer	
GET /customer/getCustomers		
POST /product/createProducts	
GET /product/getProducts		

Customer and Product services are used for internal porposes.
For creating and reading Credit data first two endpoints are destined.

1. CreateCredit: POST localhost:8080/credit/createCredit
Incoming requests for saving credit's data require complex data: Credit, Product and Customer information in JSON format. Lack of any of required causes error response.
Also attributes are required in request (except id)
Example start data:
{
	"creditDTO":
	{
		"name": "credit"
	},
	"productDTO": 
	{
		"productName": "product",
		"value": 150.5
	},
	"customerDTO":
	{
		"firstName": "Jon",
		"surname": "Knox",
		"pesel": "92042255123"
	}
}

Example start data causing Bad Request response:

{
	"creditDTO":
	{
		"name": "credit"
	}
}

2. GetCredits localhost:8080/credit/getCredits
Use GET method without any parameters to get all credits info.

Output data are in the same format as required input, list of related:
    -Credit
    -Product
    -Customer