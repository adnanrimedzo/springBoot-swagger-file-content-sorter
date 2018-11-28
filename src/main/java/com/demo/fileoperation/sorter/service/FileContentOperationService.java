package com.demo.fileoperation.sorter.service;

import com.demo.fileoperation.sorter.Util.SortList;
import com.demo.fileoperation.sorter.exception.InvalidFileException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileContentOperationService {

    public List<List<Integer>> getSortedFileContent(MultipartFile file) throws InvalidFileException {
        try {
            List<List<Integer>> numberList = getFileContent(file.getInputStream());
            return numberList.stream().map(SortList::mergeSort).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InvalidFileException("Invalid File");
        }
    }

    private List<List<Integer>> getFileContent(InputStream content) {

        BufferedReader br = new BufferedReader(new InputStreamReader(content));
        List<String> lineList = br.lines().collect(Collectors.toList());

        return lineList.stream().map(l -> Stream.of(l.split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList())).collect(Collectors.toList());

    }


}
