package com.farhan.orderservice.service.impl;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ShcedulerFilePdfGenerated {

    @Scheduled(cron = "*/30 * * * * *")
    public void pdfGenerated() throws FileNotFoundException {
        String path = "D:\\Report.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        float col =560f;
        float columWidth[] = {col};
        Table table = new Table(columWidth);
        table.setBackgroundColor(new DeviceRgb(63, 169, 219));
        table.addCell("REPORTING").setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setMarginTop(30f)
                        .setMarginBottom(30f)
                        .setFontSize(30f)
                        .setBorder(Border.NO_BORDER);

        Table reportDateTbale = new Table(columWidth);
        reportDateTbale.addCell("Date : "+fmt.format(new Date())).addCell(new Cell(0, 4))
                .setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(14f);

        float colWitdh[] = {30, 75, 100, 80, 275};
        Table customerinforTbale = new Table(colWitdh);
        customerinforTbale.addCell("No").setBorder(Border.NO_BORDER);
        customerinforTbale.addCell("Schedule").setBorder(Border.NO_BORDER);
        customerinforTbale.addCell("Studio").setBorder(Border.NO_BORDER);
        customerinforTbale.addCell("Seat Sold").setBorder(Border.NO_BORDER);
        customerinforTbale.addCell("Total").setBorder(Border.NO_BORDER);


        document.add(table);
        document.add(reportDateTbale);
        document.add(customerinforTbale);
        document.close();
        System.out.println("Pdf created");
    }
}
