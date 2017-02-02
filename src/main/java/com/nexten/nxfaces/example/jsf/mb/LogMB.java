package com.nexten.nxfaces.example.jsf.mb;

import com.nexten.nxfaces.logging.HtmlHandler;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Jaques Claudino
 */
@Named
@RequestScoped
public class LogMB implements Serializable {

    public String getLog() {
        return HtmlHandler.getInstance().getAsHtml();
    }
    
}