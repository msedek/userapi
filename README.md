# userapi project deployment UBUNTU

Open a bash console then: **sudo apt install gradle -y**

#Project Build
- In your bash go inside the **/userapi** folder and type: **gradle bootjar**, this command will build the
project and generate the userapi.jar (then with the bash, go to **/userapi/build/libs**) to be able to run the project.\
**(if the build process fails, just retry with the same gradle bootjar again)**
- proceed to execute: **java -jar userapi.jar** (use TAB to autocomplete the .jar file name)

#Check Database
- In the terminal scroll up and search for **H2ConsoleAutoConfiguration    : H2 console available at '/h2'. Database available at 'jdbc:h2:mem:xxxxxxx-xxxxxx-xxx-xxx-xxxxxx'**
- then open a browser and type in the url **localhost:8080/h2** and it will ask for the **jdbc:h2:mem:xxxxxxx-xxxxxx-xxx-xxx-xxxxxx** value, paste it at **JDBC URL**, all the others parameters are default

#Usage
- Import the included curl into POSTMAN REST CLIENT to start creating users.

curl --location --request POST 'localhost:8080/createuser' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Julio Gonzalez",
"email": "julio@test.cl",
"password": "K45g",
"phones": [
{
"number": "87650009",
"citycode": "7",
"contrycode": "25"
}
]
}'

Enjoy!

Ing. Miguel Sedek.
