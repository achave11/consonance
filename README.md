# Consonance

[![Build Status](https://travis-ci.org/Consonance/consonance.svg?branch=develop)](https://travis-ci.org/Consonance/Consonance)
[![Coverage Status](https://coveralls.io/repos/Consonance/consonance/badge.svg?branch=develop)](https://coveralls.io/r/Consonance/consonance?branch=develop)

## Components

There are the following main components that are visible to the end-user. Each component usually contains just the unit tests for each component in isolation:

* consonance-arch: contains the main consonance daemons that handle things like provisioning VMs, running jobs, etc. 
* consonance-client: contains the client classes that will form the basis for the command line client
* consonance-webservice: containers the webservice which is built as a facade between the client and our daemons
* consonance-reporting: contains reporting utilities for those running the daemons

There are the following support components:

* consonance-common: contains classes common to both the client and server (just testing utilities)
* consonance-server-common: contains classes common to the daemons and the webservice
* swagger-java-client: contains classes auto-generated b swagger-codegen to support the client
* consonance-integration-testing: contains integration tests that test the whole system


## Release Process

Use the standard mvn release:prepare and mvn release:perform procedures

## Updating

### Schema

The schema is generated by dumping the auto-generated schema from Dropwizard/Hibernate. 

    pg_dump queue_status --schema-only &> schema.sql
    mv schema.sql consonance-arch/sql

### Swagger Client

The basis for the swagger client is auto-generated from Dropwizard. While you have the web service running, create the classes here using the [https://github.com/swagger-api/swagger-codegen/blob/master/README.md](swagger-codegen) project.                    
                                                                                                                                                      
    cd swagger-codegen
    ./run-in-docker.sh generate   -i http://10.0.3.18:8080/swagger.json   -l java   -o test_swagger_output --library jersey2                                          
    cp -R  test_swagger_output/src ~/consonance/swagger-java-client/
                                                                                                                                                      
We modified the pom.xml to be compatible with our parent pom, but otherwise no files in here should be manually changed in case we wish to automatically regenerate the classes above based on changes in the web service.     
