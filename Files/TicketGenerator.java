import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class TicketGenerator {
    private static final BaseColor DARK_BLUE = new BaseColor(14, 54, 99);
    private static final BaseColor GOLD = new BaseColor(180, 145, 60);
    private static final BaseColor LIGHT_BLUE = new BaseColor(220, 235, 252);
    private static final BaseColor MID_GRAY = new BaseColor(110, 110, 110);
    private static final BaseColor WHITE = BaseColor.WHITE;
    public static void generateTicket(Booking b) throws IOException, SQLException {
        Manger manger = new Manger();
        Trip trip = manger.getTripByNumber(b.getNumber_flight());
        try {
            float W = 595f;
            float H = 420f;
            Document doc = new Document(new Rectangle(W, H), 0, 0, 0, 0);
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("ticket_" + b.getBookingId() + ".pdf"));
            doc.open();
            PdfContentByte cb = writer.getDirectContent();
            BaseFont enBF = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, false);
            BaseFont enNF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, false);

            float mainW = W * 0.71f;
            float stubX = mainW + 2;
            float stubW = W - stubX - 5;

            fillRect(cb, 5, 5, mainW - 5, H - 10, 10, new BaseColor(245, 249, 255));
            fillRect(cb, 5, H - 80, mainW - 5, 75, 10, DARK_BLUE);
            fillRect(cb, 15, H - 73, 52, 52, 5, GOLD);
            txt(cb, enBF, 15, WHITE, 78, H - 44, "Arabian Airlines");
            txt(cb, enNF, 9, GOLD, 78, H - 62, "arabianairlines.com");
            txt(cb, enBF, 13, WHITE, mainW - 195, H - 43, "BOARDING PASS");
            txt(cb, enNF, 9, GOLD, mainW - 195, H - 60, "Flight Booking Ticket");

            float y = H - 100;

            txt(cb, enBF, 11, DARK_BLUE, 15, y, "Name: " + b.getName_passenger().toUpperCase());
            y -= 16;
            txt(cb, enNF, 9, MID_GRAY, 15, y, "Booking ID: " + b.getBookingId() + " PNR: " + b.getBookingId());
            y -= 10;

            dashedLine(cb, 15, y, mainW - 15, y, GOLD);

            y -= 52;

            fillRect(cb, 15, y, 108, 44, 5, DARK_BLUE);

            txt(cb, enNF, 7, GOLD, 22, y + 34, "FLIGHT");
            txt(cb, enBF, 16, WHITE, 20, y + 10, "AA" + b.getNumber_flight());

            fillRect(cb, 135, y, 140, 44, 5, DARK_BLUE);

            txt(cb, enNF, 7, GOLD, 142, y + 34, "DATE");
            txt(cb, enBF, 14, WHITE, 142, y + 10, trip.getDay() + "-" + trip.getMonth() + "-" + trip.getYear());

            y -= 62;

            fillRect(cb, 15, y, 135, 54, 5, LIGHT_BLUE);

            txt(cb, enBF, 11, DARK_BLUE, 20, y + 36, "From: " + trip.getFrom());

            txt(cb, enNF, 9, DARK_BLUE, 20, y + 22, trip.getFrom()+"Airport");

            txt(cb, enNF, 7, MID_GRAY, 22, y + 8, trip.getFrom() + "Intl Airport");

            txt(cb, enBF, 20, GOLD, 158, y + 22, "->");

            fillRect(cb, 185, y, 135, 54, 5, LIGHT_BLUE);

            txt(cb, enBF, 11, DARK_BLUE, 190, y + 36, "To: " + trip.getTo());

            txt(cb, enNF, 9, DARK_BLUE, 190, y + 22, trip.getTo()+"Airport");

            txt(cb, enNF, 7, MID_GRAY, 192, y + 8, trip.getTo() + "Intl Airport");

            y -= 56;

            String[] labels = {"Boarding", "Gate", "Seat", "Class", "Arr", "Dep"};

            String[] vals = {"08:30", "A22", "12A", (b.getIo()), "13:30", "09:15"};

            float cw = (mainW - 30) / 6f;

            for (int i = 0; i < 6; i++) {

                float cx = 15 + i * cw;

                fillRect(cb, cx, y, cw - 3, 44, 4, LIGHT_BLUE);

                txt(cb, enNF, 6, MID_GRAY, cx + 3, y + 33, labels[i]);

                txt(cb, enBF, 9, DARK_BLUE, cx + 5, y + 12, vals[i]);
            }

            y -= 40;

            Barcode128 bc = new Barcode128();
            bc.setCode("AA" + b.getNumber_flight() + "524" + b.getBookingId());
            bc.setBarHeight(24);
            bc.setFont(null);

            Image bcImg = bc.createImageWithBarcode(cb, DARK_BLUE, null);

            bcImg.scaleToFit(mainW - 30, 30);
            bcImg.setAbsolutePosition(15, y - 26);

            doc.add(bcImg);

            txt(cb, enNF, 8, MID_GRAY, 15, 14, "www.arabianairlines.com");

            txt(cb, enNF, 8, DARK_BLUE, mainW - 160, 14, "Have a great flight!");

            fillRect(cb, stubX, 5, stubW, H - 10, 10, WHITE);

            dashedLine(cb, stubX, 15, stubX, H - 15, GOLD);

            fillRect(cb, stubX + 2, H - 80, stubW - 4, 75, 8, DARK_BLUE);

            txt(cb, enBF, 8, WHITE, stubX + 10, H - 46, "Arabian Airlines");

            txt(cb, enNF, 7, GOLD, stubX + 10, H - 62, "Passenger Copy");

            BarcodeQRCode qr = new BarcodeQRCode("ID:" + b.getBookingId() + "|FL:AA" + b.getNumber_flight(), 1, 1, null);

            Image qrImg = qr.getImage();

            float qrS = stubW - 20;

            qrImg.scaleAbsolute(qrS, qrS);
            qrImg.setAbsolutePosition(stubX + 10, H - 80 - qrS - 4);

            doc.add(qrImg);

            float sy = H - 80 - qrS - 18;

            dashedLine(cb, stubX + 8, sy, W - 8, sy, GOLD);

            sy -= 14;

            txt(cb, enBF, 8, DARK_BLUE, stubX + 10, sy, b.getName_passenger().toUpperCase());

            sy -= 18;

            txt(cb, enNF, 7, MID_GRAY, stubX + 10, sy, "Flight");
            txt(cb, enNF, 7, MID_GRAY, stubX + stubW / 2f, sy, "Seat");

            sy -= 12;

            txt(cb, enBF, 12, DARK_BLUE, stubX + 10, sy, "AA" + b.getNumber_flight());

            txt(cb, enBF, 12, DARK_BLUE, stubX + stubW / 2f, sy, "12A");

            sy -= 18;

            txt(cb, enNF, 7, MID_GRAY, stubX + 10, sy, "Gate");
            txt(cb, enNF, 7, MID_GRAY, stubX + stubW / 2f, sy, "Date");

            sy -= 12;

            txt(cb, enBF, 12, DARK_BLUE, stubX + 10, sy, "A22");
            txt(cb, enBF, 10, DARK_BLUE, stubX + stubW / 2f, sy, "25 OCT");

            sy -= 22;

            Barcode128 sbc = new Barcode128();
            sbc.setCode("AA" + b.getNumber_flight() + b.getBookingId());
            sbc.setBarHeight(16);
            sbc.setFont(null);

            Image sbcImg = sbc.createImageWithBarcode(cb, DARK_BLUE, null);

            sbcImg.scaleToFit(stubW - 20, 22);
            sbcImg.setAbsolutePosition(stubX + 10, sy - 20);

            doc.add(sbcImg);

            doc.close();

            System.out.println(
                    "Ticket generated: ticket_" + b.getBookingId() + ".pdf"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fillRect(PdfContentByte cb, float x, float y, float w, float h, float r, BaseColor c) {
        cb.saveState();
        cb.setColorFill(c);
        cb.roundRectangle(x, y, w, h, r);
        cb.fill();
        cb.restoreState();
    }

    private static void txt(PdfContentByte cb, BaseFont bf, float size, BaseColor color, float x, float y, String text) {
        cb.saveState();
        cb.beginText();
        cb.setFontAndSize(bf, size);
        cb.setColorFill(color);
        cb.setTextMatrix(x, y);
        cb.showText(text);
        cb.endText();
        cb.restoreState();
    }

    private static void dashedLine(PdfContentByte cb, float x1, float y1, float x2, float y2, BaseColor color) {

        cb.saveState();
        cb.setColorStroke(color);
        cb.setLineWidth(0.7f);
        cb.setLineDash(4f, 3f, 0f);
        cb.moveTo(x1, y1);
        cb.lineTo(x2, y2);
        cb.stroke();
        cb.restoreState();
    }
}
