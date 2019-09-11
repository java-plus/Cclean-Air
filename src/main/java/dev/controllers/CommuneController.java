package dev.controllers;

import dev.exceptions.CommuneInvalideException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class CommuneController {

    @ExceptionHandler(CommuneInvalideException.class)
    public ResponseEntity<String> handleException(CommuneInvalideException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

}
