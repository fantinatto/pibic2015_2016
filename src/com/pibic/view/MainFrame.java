package com.pibic.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.pibic.controller.Controller;
import com.pibic.controller.Diretorios;
import com.pibic.model.Model;
import java.awt.BorderLayout;

public class MainFrame extends JFrame implements ActionListener {

	// COntroleer
	private Diretorios allDir;

	// corpo
	private JTabbedPane bodyPane;
	private InicioLabel inicioTab;
	private JMenuBar menuBar;
	private JMenu mnHelp;
	private JMenuItem mFile;

	// Model to View
	private Model model;

	// Console
	private JTextArea display;

	// interface
	private Controller dirListener;
	private SpringLayout springLayout;
	private JScrollPane scrollPane;
	private JPanel ferramentasPanel;
	private JPanel consolePanel;
	private JButton taxonomiaBtn;

	// numero de tabs
	private int numTelas = 0;
	private JButton btnRegrasDeAssociao;
	private JMenuItem mntmNewMenuItem;

	public MainFrame(Model model) throws HeadlessException {
		// Insere Titulo na Janela
		super("");
		this.model = model;
		springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		// Insere TabbedPane
		getContentPane().add(criaTabbedPane());
		// insere menu
		setJMenuBar(criaMenuBar());
		// Insere Tb
		bodyPane.add("Inicio", criaTabInicial());
		{

			consolePanel = new JPanel();
			springLayout.putConstraint(SpringLayout.NORTH, consolePanel, 6,
					SpringLayout.SOUTH, bodyPane);
			springLayout.putConstraint(SpringLayout.SOUTH, consolePanel, -10,
					SpringLayout.SOUTH, getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, consolePanel, -10,
					SpringLayout.EAST, getContentPane());
			consolePanel.setBorder(new TitledBorder(new EtchedBorder(),
					"Console"));
			getContentPane().add(consolePanel);
			GridBagLayout gbl_consolePanel = new GridBagLayout();
			gbl_consolePanel.columnWidths = new int[] { 0, 0 };
			gbl_consolePanel.rowHeights = new int[] { 0, 0 };
			gbl_consolePanel.columnWeights = new double[] { 1.0,
					Double.MIN_VALUE };
			gbl_consolePanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
			consolePanel.setLayout(gbl_consolePanel);
			{

				display = new JTextArea();
				display.setFont(new Font("Monospaced", Font.BOLD, 13));
				display.setEditable(false); // set textArea non-editable

				scrollPane = new JScrollPane(display);
				scrollPane
						.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				consolePanel.add(scrollPane, gbc_scrollPane);
			}
		}
		{
			ferramentasPanel = new JPanel();
			springLayout.putConstraint(SpringLayout.WEST, consolePanel, 6,
					SpringLayout.EAST, ferramentasPanel);
			springLayout.putConstraint(SpringLayout.EAST, ferramentasPanel, -6,
					SpringLayout.WEST, bodyPane);
			ferramentasPanel.setBorder(new TitledBorder(new EtchedBorder(),
					"Ferramentas"));

			springLayout.putConstraint(SpringLayout.NORTH, ferramentasPanel,
					19, SpringLayout.NORTH, getContentPane());
			springLayout.putConstraint(SpringLayout.WEST, ferramentasPanel, 10,
					SpringLayout.WEST, getContentPane());
			springLayout.putConstraint(SpringLayout.SOUTH, ferramentasPanel,
					-10, SpringLayout.SOUTH, getContentPane());
			getContentPane().add(ferramentasPanel);

			{
				ferramentasPanel.setLayout(new BorderLayout(0, 0));
			}
			
			JPanel panel = new JPanel();
			ferramentasPanel.add(panel, BorderLayout.NORTH);
			taxonomiaBtn = new JButton("Diretório");
			panel.add(taxonomiaBtn);
			{
				btnRegrasDeAssociao = new JButton("KNN");
				panel.add(btnRegrasDeAssociao);
				
				JPanel basePanel = new JPanel();
				ferramentasPanel.add(basePanel, BorderLayout.SOUTH);
				btnRegrasDeAssociao.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						display.append("Iniciando KNN \n");
						
					}
				});
			}
			taxonomiaBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					display.append("Selecione o diretorio da base! \n");
					JFileChooser fc = new JFileChooser("base");
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					fc.setMultiSelectionEnabled(false);
					int escolha = fc.showOpenDialog(null);
					// tratando janela cancelada
					if (escolha == JFileChooser.CANCEL_OPTION) {

					} else {
						// organiza pastas conforme diretorio
						allDir = new Diretorios(fc.getSelectedFile()
								.getPath());

						BaseDadosPanel nnn = new BaseDadosPanel(display,
								allDir);
						numTelas++;

						bodyPane.add("Base de Dados", nnn);
						bodyPane.setSelectedIndex(numTelas);

					}

				}
			});
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setVisible(true);

	}

	// JTabbed 1
	public JTabbedPane criaTabbedPane() {

		bodyPane = new JTabbedPane();
		springLayout.putConstraint(SpringLayout.NORTH, bodyPane, 0,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bodyPane, 219,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, bodyPane, -171,
				SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, bodyPane, 1904,
				SpringLayout.WEST, getContentPane());
		return bodyPane;
	}

	// Jlable 1.1 tela inicial
	public JLabel criaTabInicial() {
		inicioTab = new InicioLabel();
		return inicioTab;
	}

	// barra menu 1
	private JMenuBar criaMenuBar() {
		menuBar = new JMenuBar();
		menuBar.add(criaMenu());
		{
			mntmNewMenuItem = new JMenuItem("Sobre");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane
							.showMessageDialog(
									null,
									"GREEN.TX foi desenvolvido durante o Trabalho de Conclusão de Curso, "
											+ "\nna Pontifícia Universidade Católica do Paraná, dentro do Curso de  "
											+ "\nBacharelado em Ciência da Computação."
											+ " \n\nCréditos \nVinícius Fantinatto de Medeiros "
											+ "\nDra. Deborah Ribeiro Carvalho"
											+ "\noutubro - 2015", "" + "Sobre",
									JOptionPane.INFORMATION_MESSAGE);
				}
			});
			mnHelp.add(mntmNewMenuItem);
		}
		return menuBar;
	}

	// tree menu 1.1
	private JMenu criaMenu() {
		mnHelp = new JMenu("Help");
		mnHelp.add(createFileItens());
		return mnHelp;
	}

	// bptton menu 1.1.1
	private JMenuItem createFileItens() {
		mFile = new JMenuItem("Manual");
		class MenuItemListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				Process pro;
				try {
					java.awt.Desktop.getDesktop().open(
							new File("Manual GreenTx.pdf"));

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Abrir o Arquivo do Manual Aqui
				// &&&&
			}
		}
		mFile.addActionListener(new MenuItemListener());
		return mFile;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (dirListener != null) {

		}

	}

	public void setDirListener(Controller controller) {
		this.dirListener = controller;

	}
}
