package com.pibic.controller;

import com.pibic.model.DirFormEvent;
import com.pibic.model.Model;
import com.pibic.view.MainFrame;

public class Controller {
	private MainFrame view;
	private Model model;

	public Controller(MainFrame view, Model model) {
		super();
		this.view = view;
		this.model = model;
	}

	public void DirPerformed(DirFormEvent event) {
		System.out.println("tx: " + event.getDirTaxonomia());
		System.out.println("d: " + event.getDirDados());
		System.out.println("r: " + event.getDirRegras());		
	}
}
