package com.projeto.marcus.utilitarios;

import java.io.BufferedReader;
import java.io.IOException;

public class UserInformationUtil {

	public static String converteUserInformationJsonEmString(BufferedReader buffereReader) throws IOException {
		String resposta, jsonString = "";
		while ((resposta = buffereReader.readLine()) != null) {
			jsonString += resposta;
		}
		return jsonString;
	}
}
