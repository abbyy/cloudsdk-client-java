package com.ocrsdk.abbyy.v2.client;

import com.ocrsdk.abbyy.v2.client.models.enums.*;
import com.ocrsdk.abbyy.v2.client.models.requestparams.ImageProcessingParams;
import org.junit.Assert;
import org.junit.Test;

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
        String expected = "pdfPassword=pass&description=Test+parameters&exportFormat=docx%2calto%2cpdfTextAndImages%2cxmlForCorrectedImage&profile=DocumentConversion&textType=handprinted%2ccmc7%2cgothic&imageSource=Photo&correctOrientation=True&correctSkew=False&language=English%2cFrench&pdf%3awriteTags=Write&xml%3awriteRecognitionVariants=True&xml%3awriteFormatting=False&readBarcodes=False";
        Assert.assertEquals(expected.toLowerCase(), Serializer.toQueryString(imageProcessingParams).toLowerCase());
    }


}
