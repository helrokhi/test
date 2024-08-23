package ru.testing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.testing.dto.TestingDto;

import java.util.HashMap;
@Slf4j
@Service
@RequiredArgsConstructor
public class TestingService {
    public TestingDto getResult(String input) {
        char[] chars = input.toCharArray();

        HashMap<Character, Integer> charIntegerHashMap = new HashMap<>();
        for (char aChar : chars) {
            if (charIntegerHashMap.containsKey(aChar)) {
                int count = charIntegerHashMap.get(aChar);
                charIntegerHashMap.replace(aChar, count + 1);
            } else {
                charIntegerHashMap.put(aChar, 1);
            }

        }
        TestingDto testingDto = TestingDto.builder()
                .result(charIntegerHashMap)
                .build();
        System.out.println(charIntegerHashMap);
        return testingDto;
    }
}
