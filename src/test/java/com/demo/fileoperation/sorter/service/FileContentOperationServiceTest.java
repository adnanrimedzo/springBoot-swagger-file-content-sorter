package com.demo.fileoperation.sorter.service;

import com.demo.fileoperation.sorter.exception.InvalidFileException;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class FileContentOperationServiceTest {

    @TestConfiguration
    static class FileContentOperationServiceTestContextConfiguration {
        @Bean
        public FileContentOperationService fileContentOperationService() {
            return new FileContentOperationService();
        }
    }

    @Autowired
    private FileContentOperationService fileContentOperationService;

    @Test
    public void getSortedFileContent() throws InvalidFileException {
        String test = "1 8 4 5 6 3";

        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", test.getBytes());
        List<List<Integer>> lists = fileContentOperationService.getSortedFileContent(mockMultipartFile);

        assertThat(lists.get(0), CoreMatchers.hasItems(1, 3, 4, 5, 6, 8));
    }

    @Test
    public void getSortedFileContent_exception() {
        String test = "invalid content";

        MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file", "test.txt",
                "text/plain", test.getBytes());
        try {
            fileContentOperationService.getSortedFileContent(mockMultipartFile);
        }catch (Exception e) {
            assertThat(e, instanceOf(InvalidFileException.class));
        }
    }
}