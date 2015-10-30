package com.pibic.controller;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;



import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;

public class Grafo {
	private Diretorios allDir;
	private Validacao validacao;
	private Votos votos;
	private UndirectedGraph<Integer, Link> G = new UndirectedSparseMultigraph<Integer, Link>();
	private int idLink = 0;

	public Grafo(String nameBase, Diretorios x, JTextArea display)
			throws IOException {
		this.allDir = x;
		display.append("Carregando Validação!\n");
		validacao = new Validacao(allDir.getFoldValidacao(), nameBase);
		display.append("Validação Carregada com sucesso!\n");
		display.append("Carregando voto dos Classificadores!\n");
		votos = new Votos(validacao.getClasseReal(),
				allDir.getFoldVotoClassifValidacao(), nameBase,
				validacao.getQtdInstances());
		display.append("!\n");
		display.append("Montando o Grafo Inicial!\n");
		montaGrafo(votos.getMatAcertos(), validacao.getQtdInstances());

		//carregaGUI();
	}

	public void montaGrafo(boolean[][] matAcertos, int qtdInstancias) {

		// insere todos os classificadores no grafo
		for (int z = 0; z < 100; z++) {
			G.addVertex(z);
		}

		// percorre todas as opniões
		for (int i = 0; i < qtdInstancias; i++) {
			// percorre todas as linhas
			for (int k = 0; k < 100; k++) {
				// volta e insere uma ligação
				if (matAcertos[k][i]) {
					for (int z = k; z >= 0; z--) {
						Link edge = G.findEdge(z, k);
						if (edge != null) {
							edge.incPeso();
							G.addEdge(edge, z, k);
						} else {
							Link newLink = new Link(idLink);
							idLink++;
							G.addEdge(newLink, z, k);
						}

					}
				}
			}
		}

	}

	public void carregaGUI() {

		Layout<Integer, Integer> layout = new CircleLayout(G);
		layout.setSize(new Dimension(900, 900)); // sets the initial size of the
													// space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		BasicVisualizationServer<Integer, Integer> vv = new BasicVisualizationServer<Integer, Integer>(
				layout);
		vv.setPreferredSize(new Dimension(350, 350)); // Sets the viewing area
														// size
		
		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
	


		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}

}
