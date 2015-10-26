package com.pibic.model;

public class DirFormEvent {
	private String dirTaxonomia;
	private String dirDados;
	private String dirRegras;

	public DirFormEvent(String dirTaxonomia, String dirDados, String dirRegras) {
		this.dirTaxonomia = dirTaxonomia;
		this.dirDados = dirDados;
		this.dirRegras = dirRegras;
	}

	public String getDirTaxonomia() {
		return dirTaxonomia;
	}

	public void setDirTaxonomia(String dirTaxonomia) {
		this.dirTaxonomia = dirTaxonomia;
	}

	public String getDirDados() {
		return dirDados;
	}

	public void setDirDados(String dirDados) {
		this.dirDados = dirDados;
	}

	public String getDirRegras() {
		return dirRegras;
	}

	public void setDirRegras(String dirRegras) {
		this.dirRegras = dirRegras;
	}
}
