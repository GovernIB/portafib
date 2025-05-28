package es.caib.portafib.api.interna.secure.signature.v1;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.swagger.v3.core.filter.AbstractSpecFilter;
import io.swagger.v3.oas.models.media.Schema;

/**
 * 
 * @author anadal
 * 28 may 2025 8:31:36
 */
// https://github.com/swagger-api/swagger-core/blob/master/modules/swagger-core/src/main/java/io/swagger/v3/core/filter/AbstractSpecFilter.java
public class CustomSwaggerFilter extends AbstractSpecFilter {

    @SuppressWarnings("rawtypes")
    @Override
    public Optional<Schema> filterSchema(Schema schema, Map<String, List<String>> params, Map<String, String> cookies,
            Map<String, List<String>> headers) {

        System.out.println("CustomSwaggerFilter::filterSchema(" + schema.getName() + ")");

        if ("ValidacioCompletaResponse".equals(schema.getName()) 
                || "ValidateSignatureResponse".equals(schema.getName())
                || "SignatureDetailInfo".equals(schema.getName())
                || "SignatureCheck".equals(schema.getName())
                || "InformacioCertificat".equals(schema.getName())
                || "TimeStampInfo".equals(schema.getName())
                || "ValidationStatus".equals(schema.getName()) ) {
            return Optional.empty();
        } else {

            return super.filterSchema(schema, params, cookies, headers);
        }
    }

    /*
    @Override
    public Optional<Schema> filterSchemaProperty(Schema arg0, Schema arg1, String arg2, Map<String, List<String>> arg3,
            Map<String, String> arg4, Map<String, List<String>> arg5) {
        
    return Optional.empty();
    }
    
    
    @Override
    public Optional<OpenAPI> filterOpenAPI(OpenAPI arg0, Map<String, List<String>> arg1, Map<String, String> arg2,
            Map<String, List<String>> arg3) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }
    
    @Override
    public Optional<Operation> filterOperation(Operation arg0, ApiDescription arg1, Map<String, List<String>> arg2,
            Map<String, String> arg3, Map<String, List<String>> arg4) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }
    
    @Override
    public Optional<Parameter> filterParameter(Parameter arg0, Operation arg1, ApiDescription arg2,
            Map<String, List<String>> arg3, Map<String, String> arg4, Map<String, List<String>> arg5) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }
    
    @Override
    public Optional<PathItem> filterPathItem(PathItem arg0, ApiDescription arg1, Map<String, List<String>> arg2,
            Map<String, String> arg3, Map<String, List<String>> arg4) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }
    
    @Override
    public Optional<RequestBody> filterRequestBody(RequestBody arg0, Operation arg1, ApiDescription arg2,
            Map<String, List<String>> arg3, Map<String, String> arg4, Map<String, List<String>> arg5) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }
    
    @Override
    public Optional<ApiResponse> filterResponse(ApiResponse arg0, Operation arg1, ApiDescription arg2,
            Map<String, List<String>> arg3, Map<String, String> arg4, Map<String, List<String>> arg5) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }
    
    
    
    
    
    @Override
    public boolean isRemovingUnreferencedDefinitions() {
        // TODO Auto-generated method stub
        return false;
    }
    */
}