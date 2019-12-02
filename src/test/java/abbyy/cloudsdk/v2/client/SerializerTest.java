package abbyy.cloudsdk.v2.client;

import abbyy.cloudsdk.v2.client.models.enums.*;
import abbyy.cloudsdk.v2.client.models.requestparams.DocumentProcessingParams;
import abbyy.cloudsdk.v2.client.models.requestparams.ImageProcessingParams;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class SerializerTest {

    @Test
    public void serializeProcessImageParametersTest() {
        ImageProcessingParams imageProcessingParams = new ImageProcessingParams();
        imageProcessingParams.setPdfPassword("pass");
        imageProcessingParams.setDescription("Test parameters");
        imageProcessingParams.setExportFormats(new ExportFormat[]{ExportFormat.Docx,
                ExportFormat.Alto, ExportFormat.PdfTextAndImages, ExportFormat.XmlForCorrectedImage});
        imageProcessingParams.setProfile(ProcessingProfile.DocumentConversion);
        imageProcessingParams.setTextTypes(new TextType[]{TextType.Handprinted, TextType.Cmc7, TextType.Gothic});
        imageProcessingParams.setImageSource(ImageSource.Photo);
        imageProcessingParams.setCorrectOrientation(true);
        imageProcessingParams.setCorrectSkew(false);
        imageProcessingParams.setLanguage("English,French");
        imageProcessingParams.setWriteTags(WriteTags.Write);
        imageProcessingParams.setWriteRecognitionVariants(true);
        imageProcessingParams.setWriteFormatting(false);
        imageProcessingParams.setReadBarcodes(false);
        String expected = "pdfPassword=pass&description=Test+parameters&" +
                "exportFormat=docx%2calto%2cpdfTextAndImages%2cxmlForCorrectedImage&profile=DocumentConversion&" +
                "textType=handprinted%2ccmc7%2cgothic&imageSource=Photo&correctOrientation=True&" +
                "correctSkew=False&language=English%2cFrench&pdf%3awriteTags=Write&" +
                "xml%3awriteRecognitionVariants=True&xml%3awriteFormatting=False&readBarcodes=False";
        Assert.assertEquals(expected.toLowerCase(), Serializer.toQueryString(imageProcessingParams).toLowerCase());
    }

    @Test
    public void serializeProcessDocumentParametersTest() {
        DocumentProcessingParams documentProcessingParams = new DocumentProcessingParams();
        documentProcessingParams.setTaskId(UUID.fromString("253cad50-c633-4a5e-89f2-9d85443f07eb"));
        documentProcessingParams.setLanguage("English");
        documentProcessingParams.setProfile(ProcessingProfile.DocumentConversion);
        documentProcessingParams.setExportFormats(new ExportFormat[]{ExportFormat.PdfSearchable, ExportFormat.Rtf});

        String expected = "taskId=253cad50-c633-4a5e-89f2-9d85443f07eb&" +
                "exportFormat=pdfSearchable%2Crtf&profile=DocumentConversion&language=English";
        Assert.assertEquals(expected, Serializer.toQueryString(documentProcessingParams));
    }
}
