<h1>A Unit Conversion Microservice</h1>

Local Endpoint:

http://localhost:8080/api/convert?input=(4.2*5)/3gal

Sample Output:

`{
     "initNum": 7.0,
     "initUnit": "gal",
     "returnNum": 26.49787,
     "returnUnit": "l",
     "string": "7.0 gal converts to26.49787 l"
 }`
 
 To-Do:
 
     [] Request Params special character identification.
     
     [] Code Optimizations.
     
     [] Better Data-Structure for unit conversions.
          
     [] XSS-Attack Prevention
