package com.demo.fileoperation.sorter.controller;

import com.demo.fileoperation.sorter.exception.InvalidFileException;
import com.demo.fileoperation.sorter.service.FileContentOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/fileOperation")
@Api(value = "fileoperations", description = "Operation for files and their contents")
public class FileContentOperationController {

    protected Logger logger = Logger.getLogger(FileContentOperationController.class.getName());

    @Autowired
    private FileContentOperationService fileContentOperationService;

    @ApiOperation(value = "file upload", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved lists"),
            @ApiResponse(code = 400, message = "Invalid Input file")
    }
    )
    @PostMapping("/getSortedLists")
    public List<List<Integer>> handleFileUpload(@RequestParam("file") MultipartFile file) throws InvalidFileException {
        logger.info("handleFileUpload executed");
        return fileContentOperationService.getSortedFileContent(file);
    }

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity invalidFileExceptionHandler(InvalidFileException exc) {
        return ResponseEntity.badRequest().build();
    }

}
