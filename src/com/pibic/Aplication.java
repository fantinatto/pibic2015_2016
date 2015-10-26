package com.pibic;

import javax.swing.SwingUtilities;

import com.pibic.controller.Controller;
import com.pibic.model.Model;
import com.pibic.view.MainFrame;

public class Aplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				runApp();

			}
		});

	}

	// model interface
	public static void runApp() {

		// cria os diretórios para organização agrupamentos e resultados
		Model model = new Model();
		// cria a Frame Inicial
		MainFrame view = new MainFrame(model);
		// controller leastener to view
		Controller controller = new Controller(view, model);
		// objeto passado implementa uma interface
		view.setDirListener(controller);
	}
}
