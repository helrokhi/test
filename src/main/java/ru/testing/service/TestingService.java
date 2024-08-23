package ru.testing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.testing.dto.TestingDto;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestingService {
    public TestingDto getResult(String input) {
        char[] chars = input.toCharArray();

        Map<Character, Integer> charsMap = new LinkedHashMap<>();
        for (char aChar : chars) {
            if (charsMap.containsKey(aChar)) {
                int count = charsMap.get(aChar);
                charsMap.replace(aChar, count + 1);
            } else {
                charsMap.put(aChar, 1);
            }

        }

//        Map<Character, Integer> sortedMap = charsMap.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue())
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Map<Character, Integer> sortedMap = charsMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(
                        Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        TestingDto testingDto = TestingDto.builder()
                .result(sortedMap)
                .build();
        log.info("get result by charsMap {} ", charsMap);
        log.info("get result by sortedMap {} ", sortedMap);
        return testingDto;
    }
}
