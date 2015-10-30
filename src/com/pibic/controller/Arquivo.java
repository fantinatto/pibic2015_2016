package com.pibic.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {
	protected FileReader fileFR;
	protected BufferedReader fileBR;
	protected FileWriter fileFW;
	protected BufferedWriter fileBW;
	
	public FileReader getFileFR() {
		return fileFR;
	}

	public BufferedReader getFileBR() {
		return fileBR;
	}

	public FileWriter getFileFW() {
		return fileFW;
	}

	public BufferedWriter getFileBW() {
		return fileBW;
	}

	
	
	public void abreArqLeitura(String caminho) throws FileNotFoundException {
		this.fileFR = new FileReader(caminho);
		this.fileBR = new BufferedReader(fileFR); 
	}
	
	public void fechaArqLeitura() throws IOException {
		this.fileFR.close();
		this.fileBR.close();
	}

	public void abreArqEscrita(String caminho) throws IOException {
		this.fileFW = new FileWriter(caminho);
		this.fileBW = new BufferedWriter(fileFW);
	}
	
	public void fechaArqEscrita() throws IOException {
		this.fileFW.close();
		this.fileBW.close();
	}
}
