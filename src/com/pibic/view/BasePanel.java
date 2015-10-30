package com.pibic.view;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.commons.collections15.map.HashedMap;

import com.pibic.controller.Base;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.util.Map;

public class BasePanel extends JLabel  {
	private Base b;
	private Map <String,String> listDados = new HashedMap<String,String>();	
	private DefaultListModel model = new DefaultListModel();
	private JList list = new JList(model);
	private JScrollPane jscroll;

	public BasePanel(String dirBase) {
		b = new Base(dirBase);
		listDados = b.getDados();

		setLayout(new BorderLayout(0, 0));

		JPanel basePanel = new JPanel();
		add(basePanel, BorderLayout.WEST);
	}

	public void inserePanelDados(Object dados[][]) {
		int i = 0;
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.gridheight = 8;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 5;
		gbc_panel.gridy = 2;
		
		for (String key : listDados.keySet()) {
			model.add(i, key);
			i++;
		}
		
		// adicionar dados no list
		// ..
		//scroll
		jscroll = new JScrollPane(list);
		//scroll sempre no final
		jscroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
		    public void adjustmentValueChanged(AdjustmentEvent e) {  
		         e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
		}});
		
		// retorna o indice do primeiro item selecioando na lista
		int index = list.getSelectedIndex();
		 
		// retorna array contendo todos os indices selecionados na lista
		int[] indexes = list.getSelectedIndices();
		add(list, gbc_panel);

	}

}
