package com.pibic.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Votos {
	private boolean matAcertos[][];
	public boolean[][] getMatAcertos() {
		return matAcertos;
	}

	private Arquivo votosFile;

	public Votos(String[] classeReal, String caminho, String nomeBase,
			int qtdInstances) throws IOException {
		int linha = 0, coluna = 0;

		matAcertos = new boolean[100][qtdInstances];
		String caminhoBase = caminho + nomeBase + "1.txt";

		FileReader fileFR = new FileReader(caminhoBase);
		BufferedReader fileBR = new BufferedReader(fileFR);
		String curline = fileBR.readLine();

		while (curline != null) {

			Pattern p = Pattern.compile("(\\w)+"); 
			Matcher m = p.matcher(curline);

			while (m.find()) {
				String toke = m.group();
				
				Pattern p2 = Pattern.compile(toke); 
				Matcher m2 = p2.matcher(classeReal[coluna]);
				if(m2.find()){
					matAcertos[linha][coluna] = true;
				}
				coluna++;
			}

			curline = fileBR.readLine();
			linha++;
			coluna = 0;
		}

	}	

}
