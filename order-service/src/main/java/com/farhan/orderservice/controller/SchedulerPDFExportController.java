package com.farhan.orderservice.controller;

import com.farhan.orderservice.service.impl.ShcedulerFilePdfGenerated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import java.io.FileNotFoundException;

@Controller
@RequiredArgsConstructor
public class SchedulerPDFExportController {
    private final ShcedulerFilePdfGenerated shcedulerFilePdfGenerated;

    public void pdf() throws FileNotFoundException {
        shcedulerFilePdfGenerated.pdfGenerated();
    }
}