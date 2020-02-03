package com.bis.operox.inv.service;

import java.io.File;
import java.io.IOException;

public interface BarcodeEncryptionAndDecriptionService {

	public String encode(String tmp) throws IOException;

	public String decode(File whatFile) throws Exception;

}
