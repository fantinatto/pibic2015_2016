package com.pibic.controller;

public class Diretorios {
	private String dirRaiz;
	//Arquivo arff com nome das bases
	private String foldBase = "";
	//pastas numeradas de 1  .. 20
	//Arquivo Teste+nome+nº+.arff
	private String foldTeste = "";
	//pastas numeradas de 1  .. 20
	//Arquivo Treino+nome+nº+.arff
	private String foldTreino= "";
	//pastas numeradas de 1  .. 20
	//Arquivo Valida+nome+nº+.arff
	private String foldValidacao = "";
	//Arquivo nome+nº+.txt
	private String foldVotoClassifTeste = "";
	//Arquivo nome+nº+.txt
	private String foldVotoClassifValidacao = "";
	
	public Diretorios(String dirRaiz){
		setDirRaiz(dirRaiz);
		setDir(dirRaiz);
	}
	
	public String getDirRaiz() {
		return dirRaiz;
	}
	
	public void setDirRaiz(String dirRaiz) {
		this.dirRaiz = dirRaiz;		
	}
	
	public String getFoldBase() {
		return foldBase;
	}

	public String getFoldTeste() {
		return foldTeste;
	}

	public String getFoldTreino() {
		return foldTreino;
	}

	public String getFoldValidacao() {
		return foldValidacao;
	}

	public String getFoldVotoClassifTeste() {
		return foldVotoClassifTeste;
	}

	public String getFoldVotoClassifValidacao() {
		return foldVotoClassifValidacao;
	}

	public void setDir(String dirRaiz){
		this.foldBase = dirRaiz + "\\Bases\\";
		this.foldTeste = dirRaiz + "\\Teste\\"; 
		this.foldTreino = dirRaiz + "\\Treino\\";
		this.foldValidacao = dirRaiz + "\\Validacao\\";
		this.foldVotoClassifTeste = dirRaiz + "\\VotosClassifTeste\\";
		this.foldVotoClassifValidacao = dirRaiz + "\\VotosClassifValidacao\\";
		
		
	}

}