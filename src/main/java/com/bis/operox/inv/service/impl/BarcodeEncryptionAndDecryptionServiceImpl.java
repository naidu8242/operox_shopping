package com.bis.operox.inv.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import javax.imageio.ImageIO;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import com.bis.operox.inv.service.BarcodeEncryptionAndDecriptionService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarcodeEncryptionAndDecryptionServiceImpl implements BarcodeEncryptionAndDecriptionService {

	@Override
	public String encode(String tmp) throws IOException {
		Code128Bean bean = new Code128Bean();
		final int dpi = 200;
		File outputFile = new File("sh.png");
		OutputStream out = new FileOutputStream(outputFile);
		try {
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi, BufferedImage.TYPE_INT_RGB,false, 0);
			bean.generateBarcode(canvas, tmp);
			canvas.finish();
		} finally {
			out.close();
		}
		return null;
	}

	@Override
	public String decode(File whatFile) throws Exception {

		Map<DecodeHintType, Object> tmpHintsMap = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
		tmpHintsMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
		tmpHintsMap.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));

		// check the required parameters
		if (whatFile == null || whatFile.getName().trim().isEmpty())
			throw new IllegalArgumentException("File not found, or invalid file name.");
		BufferedImage tmpBfrImage;
		try {
			tmpBfrImage = ImageIO.read(whatFile);
		} catch (IOException tmpIoe) {
			throw new Exception(tmpIoe.getMessage());
		}

		if (tmpBfrImage == null)
			throw new IllegalArgumentException("Could not decode image.");
		LuminanceSource tmpSource = new BufferedImageLuminanceSource(tmpBfrImage);
		BinaryBitmap tmpBitmap = new BinaryBitmap(new HybridBinarizer(tmpSource));
		MultiFormatReader tmpBarcodeReader = new MultiFormatReader();
		Result tmpResult;
		String tmpFinalResult;
		try {
			if (tmpHintsMap != null && !tmpHintsMap.isEmpty())
				tmpResult = tmpBarcodeReader.decode(tmpBitmap, tmpHintsMap);
			else
				tmpResult = tmpBarcodeReader.decode(tmpBitmap);
			tmpFinalResult = String.valueOf(tmpResult.getText());
		} catch (Exception tmpExcpt) {
			throw new Exception(
					"decode Excpt err - " + tmpExcpt.toString() + " - " + tmpExcpt.getMessage());
		}
		return tmpFinalResult;

	}

}
