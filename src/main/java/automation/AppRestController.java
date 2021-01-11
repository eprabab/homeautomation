package automation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController {

    public static boolean sendError = false;

    @RequestMapping(value = "/ping")
    @ResponseBody
    public ResponseEntity<String> ping() {
        if(!sendError) {
            return ResponseEntity.ok().body("pong");
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("error");
    }

    @PostMapping(value = "/generateError")
    public void invalidateHeader() {
        System.out.println("Invalidated Header !!!! ");
        sendError = true;
    }
}
