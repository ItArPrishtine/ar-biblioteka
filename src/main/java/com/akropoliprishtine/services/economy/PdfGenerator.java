package com.akropoliprishtine.services.economy;


import com.akropoliprishtine.entities.economy.Payment;
import com.akropoliprishtine.enums.PaymentType;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;


public class PdfGenerator {

    private static void createTableColumns (PdfPTable table) throws DocumentException {
        table.setWidthPercentage(80);
        table.setWidths(new int[]{3, 3, 3, 3});

        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Data", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setPadding(8);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Personi", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setPadding(8);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Lloji", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setPadding(8);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Shuma", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setPadding(8);
        table.addCell(hcell);
    }

    private static void createData (PdfPTable table, Payment payment) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        PdfPCell cell;

        cell = new PdfPCell(new Phrase(dateFormat.format(payment.getPaymentDate())));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(payment.getApplicationUser().getFirstName() + " " + payment.getApplicationUser().getLastName()));
        cell.setPadding(10);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase(String.valueOf(payment.getPaymentType().toString())));
        cell.setPadding(10);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);


        cell = new PdfPCell(new Phrase(payment.getPrice().toString() + " €"));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPadding(10);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.addCell(cell);
    }

    private static void setTotal(PdfPTable table, int total) {
        PdfPCell cell;
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        cell = new PdfPCell(new Phrase("Totali: ", headFont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell();
        cell.setPadding(10);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.addCell(cell);


        cell = new PdfPCell();
        cell.setPadding(10);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);

        cell = new PdfPCell(new Phrase(String.valueOf(total) + " €", headFont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPadding(10);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.addCell(cell);
    }

    public static ByteArrayInputStream paymentReports(List<Payment> payments) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        List<Payment> mujoret = payments.stream().filter(item -> item.getPaymentType() == PaymentType.MUJORE).collect(Collectors.toList());
        List<Payment> dhurime = payments.stream().filter(item ->
                item.getPaymentType() == PaymentType.MARKETING ||
                item.getPaymentType() == PaymentType.BARI ||
                item.getPaymentType() == PaymentType.DHURIM
        ).collect(Collectors.toList());

        List<Payment> shpenzime = payments.stream().filter(item ->
                item.getPaymentType() == PaymentType.SHPENZIM
        ).collect(Collectors.toList());

        int totalShpenzime = 0;
        int totalDhurime = 0;
        int totalMujore = 0;

        for (Payment value : shpenzime) {
            totalShpenzime += value.getPrice();
        }

        for (Payment value : dhurime) {
            totalDhurime += value.getPrice();
        }

        for (Payment value : mujoret) {
            totalMujore += value.getPrice();
        }

        try {
            PdfPTable table = new PdfPTable(4);
            createTableColumns(table);
            for (Payment payment : mujoret) {
                PdfGenerator.createData(table, payment);
            }

            setTotal(table, totalMujore);

            PdfPTable table2 = new PdfPTable(4);
            createTableColumns(table2);
            for (Payment payment : dhurime) {
                PdfGenerator.createData(table2, payment);
            }
            setTotal(table2, totalDhurime);


            PdfPTable table3 = new PdfPTable(4);
            createTableColumns(table3);
            for (Payment payment : shpenzime) {
                PdfGenerator.createData(table3, payment);
            }
            setTotal(table3, totalShpenzime);

            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph mujoreTitle = new Paragraph("TË ARDHURA MUJORE ANETARESH & PROVUESISH ");
            mujoreTitle.setAlignment(Element.ALIGN_CENTER);

            document.add(mujoreTitle);
            document.add(table);

            Paragraph dhurimeTitle = new Paragraph("TË ARDHURA MARKETING & BARI & DHURIME ");
            dhurimeTitle.setAlignment(Element.ALIGN_CENTER);

            document.add(dhurimeTitle);
            document.add(table2);

            Paragraph shpenzimeTitle = new Paragraph("SHPENZIME ");
            shpenzimeTitle.setAlignment(Element.ALIGN_CENTER);

            document.add(shpenzimeTitle);
            document.add(table3);

            document.addTitle("Raport");
            document.addSubject("Raport");

            document.close();

        } catch (DocumentException ex) {
            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
