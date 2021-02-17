package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Main extends JFrame implements MouseListener{
	
	
	
	private static final long serialVersionUID = 4016949133602386634L;
	public JFrame f;
	SliderBase base;
	public Main(){
		f = new JFrame("Game");
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setBounds(488,200,512,534);
		f.setResizable(false);
		
		base = new SliderBase();
		f.getContentPane().add(base);
		f.setVisible(true);
		
		addMouseListener(this);
		base.addMouseListener(this);
		
		base.possibleScramble();
	}
	public static void main(String[] args){
		new Main();
	}
	public int getWidth(){
		return f.getWidth();
	}
	public int getHeight(){
		return f.getHeight();
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = (int) Math.floor(e.getX()/128);
		int y = (int) Math.floor(e.getY()/128);
		base.moveTile(x, y);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
}
