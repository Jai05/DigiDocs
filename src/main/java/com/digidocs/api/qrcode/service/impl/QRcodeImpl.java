package com.digidocs.api.qrcode.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.digidocs.api.qrcode.model.Candidate;
import com.digidocs.api.qrcode.service.QRcode;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRcodeImpl implements QRcode{

	static Gson gson = new Gson();
	
	public static void main(String[] args) throws WriterException, IOException,
			NotFoundException {
		
		String filePath = "QRCode.png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		
		Candidate candidate = new Candidate();
		candidate.setFname("Akshit");
		candidate.setLname("Mahajan");
		candidate.setAge(24);
		candidate.setAddress_perm("933-D Model Town Extension, Bangalore");
		candidate.setMobile_num("7760306836");
			
		String qrCodeData = gson.toJson(candidate);
		
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		QRcodeImpl qr = new QRcodeImpl();
		qr.createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
		
		System.out.println("QR Code image created successfully!");

		String qrText = qr.readQRCode(filePath, charset, hintMap);
		
		Candidate candidateInfo = gson.fromJson(qrText, Candidate.class);
		System.out.println(candidateInfo);
	}

	@SuppressWarnings("deprecation")
	public void createQRCode(String qrCodeData, String filePath,
			String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
			throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}

	public String readQRCode(String filePath, String charset, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		return qrCodeResult.getText();
	}
}
