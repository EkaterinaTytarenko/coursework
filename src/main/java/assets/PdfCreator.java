package assets;

import entities.CategoryList;
import entities.ItemInList;
import entities.TripsList;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceEntry;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PdfCreator {

    private final TripsList list;
    private final String filePath;
    private  PDDocument document;
    private final PDFont font;

    public PdfCreator(TripsList list, String filePath) throws IOException {
        this.list = list;
        this.filePath = filePath;
        document=new PDDocument();
        font=PDType0Font.load(document,new File("D:\\DISK_C\\Downloads\\dejavu-sans-font\\DejavuSansOblique-dvzg.ttf"));
    }

    public void createListPdf() throws IOException {
        PDPage page = new PDPage();
        document.addPage(page);

        PDAcroForm acroForm = new PDAcroForm(document);
        document.getDocumentCatalog().setAcroForm(acroForm);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        int x=50;
        float y=700;
        y=addTitle(list.getName(),x,y,contentStream)-40;
        for(CategoryList category:list.getCategories()) {
            addText(category.getCategoryListName(),17f,x,y,contentStream);
            y-=25;
            if(y<50) {
                page = new PDPage();
                document.addPage(page);
                contentStream.close();
                contentStream = new PDPageContentStream(document, page);
                y=750;
            }
            for(ItemInList item:category.getItems()) {
                addItem(item,x,y,document,page,acroForm,contentStream);
                y-=20;
                if(y<50) {
                    page = new PDPage();
                    document.addPage(page);
                    contentStream.close();
                    contentStream = new PDPageContentStream(document, page);
                    y=750;
                }
            }
                y-=40;
        }
        contentStream.close();
        document.save(new File(filePath));
        document.close();
    }

    private void addItem(ItemInList item,float x,float y,PDDocument document,PDPage page,PDAcroForm acroForm,PDPageContentStream contentStream) throws IOException {

        COSDictionary normalAppearances = new COSDictionary();
        PDAppearanceDictionary pdAppearanceDictionary = new PDAppearanceDictionary();
        pdAppearanceDictionary.setNormalAppearance(new PDAppearanceEntry(normalAppearances));
        pdAppearanceDictionary.setDownAppearance(new PDAppearanceEntry(normalAppearances));

        PDAppearanceStream pdAppearanceStream = new PDAppearanceStream(document);
        pdAppearanceStream.setResources(new PDResources());
        try (PDPageContentStream pdPageContentStream = new PDPageContentStream(document, pdAppearanceStream)) {
            pdPageContentStream.setFont(font, 14.5f);
            pdPageContentStream.beginText();
            pdPageContentStream.newLineAtOffset(3, 4);
            pdPageContentStream.showText("\u2714");
            pdPageContentStream.endText();
        }
        pdAppearanceStream.setBBox(new PDRectangle(18, 18));
        normalAppearances.setItem("Yes", pdAppearanceStream);

        pdAppearanceStream = new PDAppearanceStream(document);
        pdAppearanceStream.setResources(new PDResources());
        try (PDPageContentStream pdPageContentStream = new PDPageContentStream(document, pdAppearanceStream)) {
            pdPageContentStream.setFont(font, 14.5f);
            pdPageContentStream.beginText();
            pdPageContentStream.newLineAtOffset(3, 4);
            pdPageContentStream.showText("\u2718");
            pdPageContentStream.endText();
        }
        pdAppearanceStream.setBBox(new PDRectangle(18, 18));
        normalAppearances.setItem("Off", pdAppearanceStream);

        PDCheckBox checkBox = new PDCheckBox(acroForm);
        acroForm.getFields().add(checkBox);
        checkBox.setPartialName(item.getId()+"");
        checkBox.setFieldFlags(4);
        List<PDAnnotationWidget> widgets = checkBox.getWidgets();
        for (PDAnnotationWidget pdAnnotationWidget : widgets) {
            pdAnnotationWidget.setRectangle(new PDRectangle(x, y, 18, 18));
            pdAnnotationWidget.setPage(page);
            page.getAnnotations().add(pdAnnotationWidget);
            pdAnnotationWidget.setAppearance(pdAppearanceDictionary);
        }
        if(item.isChecked())
            checkBox.check();
        else
            checkBox.unCheck();

        addText(item.getName(),13.5f,x+19, y+5,contentStream);
        addText(item.getAmount()+"",13.5f,x+250, y+5,contentStream);

    }

    private void addText(String text,float fontSize,float x,float y,PDPageContentStream contentStream) throws IOException {

        contentStream.beginText();

        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);

        contentStream.endText();
    }

    private float addTitle(String text,float x,float y,PDPageContentStream contentStream) throws IOException {

        String[] title =text.split(" and ");
        int i=0;
        for(;i<title.length-1;++i){
            addText(title[i]+" and",20f,x,y,contentStream);
            y-=22;
        }
        addText(title[i],20f,x,y,contentStream);
        return y;
    }

}
