1. Eureka server - discovery service that monitors servers, their health. </br>
Client -> (register) Eureka server </br> 
Client -> (lookup for other service) Eureka server - service discovery request </br>
   Client <-> (connect) Eureka server </br>

Eureka server knows all the client applications (their IPs and ports). 
What if Eureka server goes down?