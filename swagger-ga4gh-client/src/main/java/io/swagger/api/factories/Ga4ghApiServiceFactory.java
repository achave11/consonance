package io.swagger.api.factories;

import io.swagger.api.Ga4ghApiService;
import io.swagger.api.impl.Ga4ghApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-29T15:53:35.382-08:00")
public class Ga4ghApiServiceFactory {
    private final static Ga4ghApiService service = new Ga4ghApiServiceImpl();

    public static Ga4ghApiService getGa4ghApi() {
        return service;
    }
}
