# Consonance

[![Build Status](https://travis-ci.org/Consonance/consonance.svg?branch=develop)](https://travis-ci.org/Consonance/Consonance)
[![Coverage Status](https://coveralls.io/repos/Consonance/consonance/badge.svg?branch=develop)](https://coveralls.io/r/Consonance/consonance?branch=develop)

## Release Process

Use the standard mvn release:prepare and mvn release:perform procedures

## Updating

### Schema

The schema is generated by dumping the auto-generated schema from Dropwizard/Hibernate. 

    pg_dump queue_status --schema-only &> schema.sql

### Swagger Client

The basis for the swagger client is auto-generated from Dropwizard. While you have the web service running, create the classes here using the [https://github.com/swagger-api/swagger-codegen/blob/master/README.md](swagger-codegen) project.                    
                                                                                                                                                      
    cd swagger-codegen
    ./run-in-docker.sh generate   -i http://10.0.3.18:8080/swagger.json   -l java   -o test_swagger_output                                            
    cp -R  test_swagger_output/src ~/consonance/swagger-java-client/
                                                                                                                                                      
We modified the pom.xml to be compatible with our parent pom, but otherwise no files in here should be manually changed in case we wish to automatically regenerate the classes above based on changes in the web service.     
