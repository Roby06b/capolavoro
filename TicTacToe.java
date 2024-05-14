package Default;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class TicTacToe extends JFrame {

	private JPanel contentPaneBottoni = new JPanel();
	private JPanel contentPaneTesto = new JPanel();
	
	private int larghezza = 600;
	private int altezza = 650;
	JLabel testo = new JLabel("Tic-Tac-Toe");
	
	JButton[][] bottoni = new JButton[3][3];
	String giocatore1 = "X";
	String giocatore2 = "O";
	String giocatoreAttuale = giocatore1;	//variabile che tiene conto di chi tocca fare la mossa durante un turno
	
	boolean gameOver;
	int turni = 0;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicTacToe frame = new TicTacToe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TicTacToe() {
		
		//caratteristiche frame
		setTitle("Tic-Tac-Toe");
		setBackground(Color.darkGray);	//sfondo JPanel

		setVisible(true);
		setSize(larghezza, altezza);
		setResizable(false);	//impedisce la modifica della dimensione della finestra
		setLocationRelativeTo(null); //apre la pagina al centro dello schermo
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		
	
		//caratteristiche JLabel testo
		testo.setBackground(Color.darkGray);	
		testo.setForeground(Color.white);
		testo.setFont(new Font("Arial", Font.BOLD, 50));	
		testo.setHorizontalAlignment(SwingConstants.CENTER);//centralizza all'interno di contentPaneTesto la JLabel testo 
		testo.setOpaque(true);	//rende visibile il background della JLabel testo
		
		contentPaneTesto.setLayout(new BorderLayout());
		contentPaneTesto.add(testo);
		add(contentPaneTesto, BorderLayout.NORTH);
		
		contentPaneBottoni.setLayout(new GridLayout(3, 3));
		contentPaneBottoni.setBackground(Color.DARK_GRAY);
		add(contentPaneBottoni);
		
		
		//aggiunta dei bottoni e le loro caratteristiche
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				JButton bottone = new JButton();
				bottoni[i][j] = bottone;
				contentPaneBottoni.add(bottone);
				
				bottone.setBackground(Color.DARK_GRAY);
				bottone.setForeground(Color.white);
				bottone.setFont(new Font("Arial", Font.BOLD, 120));
				bottone.setFocusable(false);	
				/*quando un bottone viene cliccato normalmente comparirebbe intorno al testo una cornice azzurra, con il metodo
				 *  setFocusable messo a false, questo contorno scompare
				*/
				
				
				//gestione evento quando l'utente clicca un bottone
				bottone.addActionListener(new ActionListener(){
					
					
					
					public void actionPerformed(ActionEvent e) {
						
						
						if(gameOver)
							return;
				
						
						if(bottone.getText() == "") {	//if che controlla se un bottone è già stato cliccato oppure no
							
							if(giocatoreAttuale == giocatore1) {	//se è il turno di X
								bottone.setText(giocatore1);
								turni++;
								controlloVincitore();
								giocatoreAttuale = giocatore2;
								
							}
							else {									//se è il turno dell'altro giocatore cioè O
								bottone.setText(giocatore2);
								turni++;
								controlloVincitore();
								giocatoreAttuale = giocatore1;
							}
						}
						
						if(gameOver)
							return;
						testo.setText("turno di " + giocatoreAttuale); 
					}
				});
			}	
		}
	}
	
	void controlloVincitore() {
		//controllo orizzontale
		for(int i = 0; i < 3; i++) {
			
			if(bottoni[i][0].getText() == "")
				continue;
			
			if(bottoni[i][0].getText() == bottoni[i][1].getText() && bottoni[i][1].getText() == bottoni[i][2].getText()) {
				for(int j = 0; j < 3; j++) {
					setColoreVittoria(bottoni[i][j]);
				}
				gameOver = true;
				return;
			}
		}
		
		//controllo verticale
		for(int i = 0; i < 3; i++) {
			
			if(bottoni[0][i].getText() == "")
				continue;
			
			if(bottoni[0][i].getText() == bottoni[1][i].getText() && bottoni[1][i].getText() == bottoni[2][i].getText()) {
				for(int j = 0; j < 3; j++) {
					setColoreVittoria(bottoni[j][i]);
				}
				gameOver = true;
				return;
			}
		}
		//controllo diagonale principale
		for(int i = 0; i < 3; i++) {
			
			if(bottoni[i][i].getText() == "")
				continue;
			
			if(bottoni[0][0].getText() == bottoni[1][1].getText() && 
			   bottoni[1][1].getText() == bottoni[2][2].getText() &&
			   bottoni[2][2].getText() == bottoni[0][0].getText()){
				for(int j = 0; j < 3; j++) {
					setColoreVittoria(bottoni[j][j]);
				}
				gameOver = true;
				return;
			}
		}
		//controllo antidiagonale
		for(int i = 0; i < 3; i++) {
			
			if(bottoni[0][2].getText() == "")
				continue;
			
			if(bottoni[0][2].getText() == bottoni[1][1].getText() && 
			   bottoni[1][1].getText() == bottoni[2][0].getText() &&
			   bottoni[2][0].getText() == bottoni[0][2].getText()) {
				
				for(int j = 0; j < 3; j++) {
					setColoreVittoria(bottoni[j][2-j]);
				}
				gameOver = true;
				return;
			}
		}
		
		if(turni == 9) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					setColorePareggio(bottoni[i][j]);
				}
			}
			gameOver = true;
			return;
		}
	}
	
	//cambia il colore del testo dei bottoni e della JLabel testo in caso di vittoria
	void setColoreVittoria(JButton bottone) {
		bottone.setForeground(Color.green);
		testo.setText("ha vinto il giocatore: " + giocatoreAttuale);
		testo.setForeground(Color.green);
	}
	
	//cambia il colore del testo dei bottoni e della JLabel testo in caso di pareggio
	void setColorePareggio(JButton bottone) {
		bottone.setForeground(Color.orange);
		testo.setText("Pareggio!");
		testo.setForeground(Color.orange);
	}
}
	
	




	
	
		
	

