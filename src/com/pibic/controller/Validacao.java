package com.pibic.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import weka.clusterers.forOPTICSAndDBScan.Databases.SequentialDatabase;
import weka.core.Instances;

public class Validacao {
	private Instances data;
	private SequentialDatabase sdata;
	int qtdAtributos, qtdInstances;
	private String[] classeReal;
	private FileReader fileFRtxt, fileFRarff;
	private BufferedReader fileBRtxt, fileBRarff;


	public Validacao(String caminho, String nomeBase) throws IOException {
		String baseval = caminho + "1" + "\\" + "Valida" + nomeBase + "1";

		fileFRtxt = new FileReader(baseval+".txt");
		fileBRtxt = new BufferedReader(fileFRtxt);
		
		fileFRarff = new FileReader(baseval+".arff");
		fileBRarff = new BufferedReader(fileFRarff);		

	}

	public String[] getClasseReal() throws IOException {
		int linha = 0;
		
		data = new Instances(fileBRarff);
		qtdInstances = data.numInstances();
		classeReal = new String[qtdInstances];
		fileFRarff.close();
			
		String curLine = fileBRtxt.readLine();

		while (curLine != null) {

			String minusculo = curLine.toLowerCase(); // normaliza em minusculo
			// e sem acento
			minusculo = Normalizer.normalize(minusculo, Normalizer.Form.NFD);
			minusculo = minusculo.replaceAll("[^\\p{ASCII}]", "");
			Pattern p = Pattern.compile("(\\A\\w+)|(\\A\\d)"); // pega apenas
																// token no
																// começo da
																// string
			Matcher m = p.matcher(minusculo);

			while (m.find()) {
				classeReal[linha] = m.group();

			}
			linha++;
			curLine = fileBRtxt.readLine();
		}
		return classeReal;
	}
	
	

	public int getQtdInstances() {
		return qtdInstances;
	}
}
