package Main;


import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class HtmlToCsv implements Helpers.HtmlToCsv{

    // requires: an html file that is a specific url of the aub dynammic schedule
    // effects: parse it into a csv file
    @Override
    public void htmlToCsv(String htmlFile, String csvFile) {
        try {
            String html = Files.readString(Path.of(htmlFile));
            String fall = html.substring(html.indexOf("Fall 2021-2022(202210)"));
            fall = fall.substring( 0 , fall.indexOf("Spring 2021-2022(202220)")).trim();
            String[] rows  = fall.split("</TR>");
            File file = new File(csvFile);
            PrintWriter output = new PrintWriter(csvFile);
            output.println("CRN , Subject , Course Number , Section , Title ,  Credits , College , Actual Enroll , Seats Available ,Begin Time , End Time, Buildings, Room , Monday , Tuesday , Wednesday , Thursday , Friday , Saturday , Instructor First , Instructor Last");
            for(String row : rows){
                try {
                    String[] tds = row.split("</TD>");
                    String beginTime = tds[11].replace("<TD>","").trim();
                    String endTime = tds[12].replace("<TD>","").trim();
                    if (beginTime.length()==4) {beginTime = beginTime.substring(0 , 2) +":"+ beginTime.substring(2);}
                    if (endTime.length()==4) {endTime = endTime.substring(0 , 2) +":"+ endTime.substring(2);}
                    String Title = tds[5].replace("<TD>","").trim();
                    if (Title.contains(",")){Title = Title.replace(",", " ");}
                    output.print(tds[1].replace("<TD>","").trim()+",");
                    output.print(tds[2].replace("<TD>","").trim()+",");
                    output.print(tds[3].replace("<TD>","").trim()+",");
                    output.print(tds[4].replace("<TD>","").trim()+",");
                    output.print(Title+",");
                    output.print(tds[7].replace("<TD>","").trim()+",");
                    output.print(tds[8].replace("<TD>","").trim()+",");
                    output.print(tds[9].replace("<TD>","").trim()+",");
                    output.print(tds[10].replace("<TD>","").trim()+",");
                    output.print(beginTime+",");
                    output.print(endTime+",");
                    output.print(tds[13].replace("<TD>","").trim()+",");
                    output.print(tds[14].replace("<TD>","").trim()+",");
                    output.print(tds[15].replace("<TD>", "").trim().equals("M") + ",");
                    output.print(tds[16].replace("<TD>", "").trim().equals("T") + ",");
                    output.print(tds[17].replace("<TD>", "").trim().equals("W") + ",");
                    output.print(tds[18].replace("<TD>", "").trim().equals("R") + ",");
                    output.print(tds[19].replace("<TD>", "").trim().equals("F") + ",");
                    output.print(tds[20].replace("<TD>", "").trim().equals("S") + ",");
                    output.print(tds[33].replace("<TD>","").trim()+",");
                    output.println(tds[34].replace("<TD>","").trim());

                }catch (Exception e){}
            }
            output.close();
        }
        catch (Exception e){
        }
    }
}