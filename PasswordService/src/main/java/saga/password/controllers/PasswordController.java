package saga.password.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {

    private static final Logger log = LogManager.getLogger(PasswordController.class);

 
    @RequestMapping("/send/{word}")
    public String checkPassword(@PathVariable String password){
        
        return "OK";
    }
}
