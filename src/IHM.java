import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class IHM implements Runnable, ActionListener, KeyListener {
	
	private volatile boolean pret;
	private JFrame fenetre;
	private JMenuItem menuItemFermer;
	private JMenuItem menuItemAPropos;
	private JPanel panneauCarte;
	private boolean attendreReaction;
	private JScrollPane panneau;
	
	private Partie partie;
	private JPanel panneauActuel;
	private int couche;
	
	public IHM()
	{
		this.pret = false;
		this.couche = 0;
	}
	
	public boolean obtenirPret()
	{
		return this.pret;
	}
	
	public void afficherMessage(String nom, String message)
	{
		JOptionPane.showMessageDialog(this.fenetre, message, nom, JOptionPane.PLAIN_MESSAGE);
	}
	
	public int proposerChoix(String nom, String question, String[] options)
	{
		int reponse = JOptionPane.showOptionDialog(this.fenetre, question, nom, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
		return reponse;
	}
	
	public void afficherCarte()
	{
		this.panneauActuel = panneauCarte;
		Position position;
		Carte carte = this.partie.obtenirCarte();
		Equipe equipe = this.partie.obtenirEquipe();
		this.panneauCarte.removeAll();
		this.panneauCarte.setLayout(new GridLayout(carte.obtenirHauteur(), carte.obtenirLargeur()));
		
		for (int ligne = 0; ligne < carte.obtenirHauteur(); ligne++)
		{
			for (int colonne = 0; colonne < carte.obtenirLargeur(); colonne++)
			{
				position = new Position(ligne,colonne);
				
				BufferedImage img_finale = new BufferedImage(Application.LARGEUR_TILE, Application.HAUTEUR_TILE, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2 = img_finale.createGraphics();
				g2.drawImage(carte.obtenirCase(position), 0, 0, null);
				if(equipe.obtenirPosition().equals(position))
				{
					g2.drawImage(equipe.obtenirApparence(), 0, 0, null);
				}
				g2.dispose();
				
				this.panneauCarte.add((new JLabel(new ImageIcon(img_finale))));
				img_finale = null;
			}
		}
		
		this.panneauActuel.updateUI();
		this.panneau.getViewport().setViewPosition(equipe.obtenirPosition().toPointCentre());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();

		if (source == this.menuItemAPropos)
		{
			JOptionPane.showMessageDialog(this.fenetre, "Tentative de RPG\nPar Loïc BOUIX, Boris HENROT, Nicolas ROUGE, Max SANFILIPPO et Valentin VERVACKE", "A propos", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		else if (source == this.menuItemFermer)
		{
			if (JOptionPane.showConfirmDialog(this.fenetre, "Fermer l'application ?", "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION)
				this.fenetre.dispose();
		}
		else
		{
			
		}
	}

	@Override
	public void run() {
		this.fenetre = new JFrame();
		this.fenetre.setTitle("Projet 17");
		this.fenetre.setSize(Application.LARGEUR_ECRAN+10, Application.HAUTEUR_ECRAN+54);
		this.fenetre.setResizable(false);
		this.fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.fenetre.setLocationRelativeTo(null);
		
		JMenuBar barreDeMenu = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		this.menuItemAPropos = new JMenuItem("A propos");
		this.menuItemAPropos.addActionListener(this);
		menu.add(this.menuItemAPropos);
		this.menuItemFermer = new JMenuItem("Fermer");
		this.menuItemFermer.addActionListener(this);
		menu.add(this.menuItemFermer);
		barreDeMenu.add(menu);
		this.fenetre.setJMenuBar(barreDeMenu);

		this.panneau = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.panneauCarte = new JPanel();
		this.panneauCarte.setBackground(Color.BLACK);
		this.panneauActuel = panneauCarte;
		this.panneau.getViewport().add(this.panneauActuel);
		this.panneau.getViewport().setBackground(Color.BLACK);
		this.panneau.setPreferredSize(new Dimension(Application.LARGEUR_ECRAN, Application.HAUTEUR_ECRAN));
		this.fenetre.add(this.panneau);
		
		this.fenetre.addKeyListener(this);
		this.fenetre.setVisible(true);
		
		this.pret = true;
	}
	
	public void attendreReaction() {
		this.attendreReaction = true;
		while(this.attendreReaction)
		{
			try {
			    TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}

	public void transmettrePartie(Partie partie) {
		this.partie = partie;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(this.attendreReaction)
		{
			char touche = arg0.getKeyChar();
			if(this.partie.obtenirEtat()=="Carte")
			{
				Direction direction;
				switch(touche)
				{
					case 'z':
						direction = Direction.HAUT;
						if(this.partie.obtenirCarte().peutAller(direction, this.partie.obtenirEquipe().obtenirPosition()))
						{
							this.partie.obtenirEquipe().deplacer(direction);
						}
						else
						{
							this.partie.obtenirEquipe().changerDirection(direction);
						}
						break;
					case 'd':
						direction = Direction.DROITE;
						if(this.partie.obtenirCarte().peutAller(direction, this.partie.obtenirEquipe().obtenirPosition()))
						{
							this.partie.obtenirEquipe().deplacer(direction);
						}
						else
						{
							this.partie.obtenirEquipe().changerDirection(direction);
						}
						break;
					case 's':
						direction = Direction.BAS;
						if(this.partie.obtenirCarte().peutAller(direction, this.partie.obtenirEquipe().obtenirPosition()))
						{
							this.partie.obtenirEquipe().deplacer(direction);
						}
						else
						{
							this.partie.obtenirEquipe().changerDirection(direction);
						}
						break;
					case 'q':
						direction = Direction.GAUCHE;
						if(this.partie.obtenirCarte().peutAller(direction, this.partie.obtenirEquipe().obtenirPosition()))
						{
							this.partie.obtenirEquipe().deplacer(direction);
						}
						else
						{
							this.partie.obtenirEquipe().changerDirection(direction);
						}
						break;
					case '*':
						if(this.couche==0)
							this.couche = 1;
						else
							this.couche = 0;
						break;
					case '1':
						if(this.couche == 0)
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.MUR_PIERRE, this.couche);
						else
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.ROCHER, this.couche);
						break;
					case '2':
						if(this.couche == 0)
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.SOL_PIERRE_1, this.couche);
						else
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.TABLE, this.couche);
						break;
					case '3':
						if(this.couche == 0)
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.SOL_PIERRE_2, this.couche);
						else
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.TABOURET, this.couche);
						break;
					case '4':
						if(this.couche == 0)
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.SOL_BOIS, this.couche);
						else
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.TABLE_CASSE, this.couche);
						break;
					case '5':
						if(this.couche == 0)
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.SOL_TROU, this.couche);
						else
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.TABOURET_CASSE, this.couche);
						break;
					case '6':
						if(this.couche == 1)
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.TAS_PIERRE, this.couche);
						break;
					case '7':
						if(this.couche == 1)
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.TOMBE, this.couche);
						break;
					case '9':
						if(this.couche == 0)
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), Tile.VIDE, this.couche);
						else
							this.partie.obtenirCarte().setCase(this.partie.obtenirEquipe().obtenirPosition(), null, this.couche);
						break;
					case '!':
						System.out.println(this.partie.obtenirCarte().toString());
						break;
				}
				this.attendreReaction = false;
			}
		}
	}
	
	public Partie renvoyerPartie()
	{
		return this.partie;
	}
	
}
