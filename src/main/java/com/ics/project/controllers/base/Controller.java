package com.ics.project.controllers.base;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Expose base endpoint that handles all unhandled routes
 *
 * @author Dr H
 */
@RestController
public class Controller {
    @GetMapping
    public String home() {
        return "<!DOCTYPE html><html><body><h1>NAPI</h1><p>Welcome to the Netflix API Mock Server</p></body></html>";
    }
}
