package com.pibic.controller;

import java.io.File;
import java.util.Map;

import org.apache.commons.collections15.map.HashedMap;

public class Base {	
	private File theDir;
	private String directoryName;
	private Map <String,String> listDados = new HashedMap<String,String>();
	
	public Map <String,String> getDados() {
		return listDados;
	}

	public Base(String dirBase){
		this.theDir = new File(dirBase);
		this.directoryName = theDir.toString();
		montaListaDeBase();
		
	}
	
	public void montaListaDeBase(){
		File folder = new File(directoryName);	
		//verifica se diretorio existe
		if (folder.isDirectory()) {
			File[] sun = folder.listFiles();
			// percorre todos os arquivos do diretório
			// após ler deleta o arquivo			
			
			for (File toFile : sun) {	
				//captura nome do anntecedente do arquivo
				String nomeConsequente = toFile.toString().substring(
						directoryName.length() + 1,
						toFile.toString().length() - 5);
				//dados[i][0] = new J;
				//add botão check
				listDados.put(nomeConsequente, toFile.toString());
				
			}
		}
	}
	
}
