package com.demo.fileoperation.sorter.controller;

import com.demo.fileoperation.sorter.exception.InvalidFileException;
import com.demo.fileoperation.sorter.service.FileContentOperationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FileContentOperationController.class)
public class FileContentOperationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FileContentOperationService fileContentOperationService;

    @Test
    public void handleFileUpload() throws Exception {
        String test = "1 8 4 5 6 3";
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", test.getBytes());

        given(fileContentOperationService.getSortedFileContent(mockMultipartFile))
                .willReturn(Arrays.asList(Arrays.asList(1, 3, 4, 5, 6, 8)));

        this.mvc.perform(fileUpload("/fileOperation/getSortedLists").file(mockMultipartFile))
                .andExpect(status().isOk())
                .andExpect(content().string("[[1,3,4,5,6,8]]"));
    }

    @Test
    public void invalidFileExceptionHandler() throws Exception {
        String test = "1 8 4 5 6 3";
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", test.getBytes());

        given(fileContentOperationService.getSortedFileContent(mockMultipartFile))
                .willThrow(InvalidFileException.class);

        this.mvc.perform(fileUpload("/fileOperation/getSortedLists").file(mockMultipartFile))
                .andExpect(status().isBadRequest());
    }
}