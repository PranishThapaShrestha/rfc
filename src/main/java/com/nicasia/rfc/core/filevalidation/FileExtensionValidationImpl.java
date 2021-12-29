//package com.nicasia.rfc.core.filevalidation;
//
//import com.nicasia.rfc.shared.exception.ClientException;
//import org.apache.tika.Tika;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class FileExtensionValidationImpl implements FileExtensionValidation {
//
//
//    @Value("${invalid.fileFormat}")
//    private String[] invalidFileFormat;
//
//    @Value("$invalid.invalidContentType")
//    private String[] invalidContentType;
//
//    @Override
//    public void isFileExtensionValidated(List<String> fileNames) {
//
//        boolean invalid = true;
//        for (String name : fileNames) {
//            for (String extension : Arrays.asList(invalidFileFormat)) {
//                if (name.contains(".")) {
//                    if (name.substring(name.lastIndexOf(".") + 1).equals(extension)) {
//                        invalid = false;
//                    }
//                } else {
//                    throw new ClientException("Invalid file format file name:: " + name);
//                }
//            }
//            if (invalid) {
//                throw new ClientException("Invalid file format file name:: " + name);
//            }
//        }
//    }
//
//    @Override
//    public void validateFileContentType(MultipartFile[] files) {
//        Tika tika = new Tika();
//        List<String> detectedContentTypes = new ArrayList<>();
//        try {
//            for (MultipartFile multipartFile : files) {
//                detectedContentTypes.add(tika.detect(multipartFile.getBytes()));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        validateFileContent(detectedContentTypes);
//    }
//
//    private void validateFileContent(List<String> detectedContentTypes) {
//        boolean invalid = true;
//        for (String contentType : detectedContentTypes) {
//            for (String extension : Arrays.asList(invalidContentType)) {
//                if (contentType.equals(extension)) {
//                    invalid = false;
//                }
//            }
//            if (invalid) {
//                throw new ClientException("Invalid file format ::" + contentType);
//            }
//        }
//    }
//
//
//}
