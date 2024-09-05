package ru.testing.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.testing.dto.TestingDto;
import ru.testing.service.TestingService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/testing")
public class TestingController {
    private final TestingService testingService;

    @GetMapping
    public ResponseEntity<TestingDto> getResult(
            @RequestParam("input") String input) {

        log.info("get result by input {}", input);

        try {
            TestingDto testingDto = testingService.getResult(input);
            return ResponseEntity.ok(testingDto);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
