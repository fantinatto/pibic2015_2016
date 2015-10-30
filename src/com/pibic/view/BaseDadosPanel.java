package com.pibic.view;

import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.apache.commons.collections15.map.HashedMap;

import com.pibic.controller.Base;
import com.pibic.controller.Diretorios;
import com.pibic.controller.Grafo;

import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import net.miginfocom.swing.MigLayout;

public class BaseDadosPanel extends JPanel {
	private Base b;
	private Diretorios allDirs;
	private Map<String, String> listDados = new HashedMap<String, String>();
	private DefaultListModel model = new DefaultListModel();
	
	
	//base
	private int index;
	private int[] indexes;
	//interface
	private JScrollPane jscroll;
	private JList list_1;
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JPanel leftDownPanel;
	private JButton btnNewButton;

	public BaseDadosPanel(final JTextArea display, Diretorios allDir) {
		this.allDirs = allDir;
		
		b = new Base(allDirs.getFoldBase());
		listDados = b.getDados();
		setLayout(new BorderLayout(0, 0));
		
		
		topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		
		downPanel = new JPanel();
		add(downPanel, BorderLayout.SOUTH);
		
		rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);
		
		leftPanel = new JPanel();
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		leftDownPanel = new JPanel();
		leftPanel.add(leftDownPanel, BorderLayout.SOUTH);
		leftDownPanel.setLayout(new MigLayout("", "[]", "[]"));
		
		btnNewButton = new JButton("Grafo Inicial");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// retorna o indice do primeiro item selecioando na lista
				index = list_1.getSelectedIndex();	
				// retorna array contendo todos os indices selecionados na lista
				indexes = list_1.getSelectedIndices();
				
				if(index < 0){
					//display.append(String.valueOf(index));
					display.append("Selecione uma Base!\n");
				}
				else{				
					
					try {
						
						Grafo grafo = new Grafo(model.getElementAt(index).toString(), allDirs, display);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
							
			
								
			}
		});
		leftDownPanel.add(btnNewButton, "cell 0 0");
		list_1 = new JList(model);
		list_1.setVisibleRowCount(20);
		
		
		jscroll = new JScrollPane(list_1);
		leftPanel.add(jscroll, BorderLayout.WEST);
		jscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
				jscroll.getVerticalScrollBar().addAdjustmentListener(
						new AdjustmentListener() {
							public void adjustmentValueChanged(AdjustmentEvent e) {
								e.getAdjustable().setValue(
										e.getAdjustable().getMaximum());
							}
						});
		
		insereLista();
		

	}


	public void insereLista() {
		int i = 0;
		for (String key : listDados.keySet()) {
			model.add(i, key);
			i++;
		}
	}

}