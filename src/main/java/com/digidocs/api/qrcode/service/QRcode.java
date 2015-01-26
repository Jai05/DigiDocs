package com.digidocs.api.qrcode.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

public interface QRcode {

	public void createQRCode(String qrCodeData, String filePath,
			String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
			throws WriterException, IOException;
	
	public String readQRCode(String filePath, String charset, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException;
}
