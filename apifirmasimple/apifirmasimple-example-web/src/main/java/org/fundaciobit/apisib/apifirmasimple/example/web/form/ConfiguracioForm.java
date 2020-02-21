package org.fundaciobit.apisib.apifirmasimple.example.web.form;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 * 
 * @author anadal
 *
 */
public class ConfiguracioForm {

    
    @URL
    @NotBlank(message="{errorBuit}")
    protected String endPoint;

    @NotBlank(message="{errorBuit}")
    protected String username;

    @NotBlank(message="{errorBuit}")
    protected String password;

    public ConfiguracioForm() {
        super();
    }

    public ConfiguracioForm(String endPoint, String username, String password) {
        super();
        this.endPoint = endPoint;
        this.username = username;
        this.password = password;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
