package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SliderBase extends JPanel implements ActionListener{
	private static final long serialVersionUID = 781830428728796836L;
	private Image image;
	private Timer t;
	
	public ArrayList<String> rlist0 = new ArrayList<String>(Arrays.asList("00", "01", "02", "03"));
	public ArrayList<String> rlist1 = new ArrayList<String>(Arrays.asList("10", "11", "12", "13"));
	public ArrayList<String> rlist2 = new ArrayList<String>(Arrays.asList("20", "21", "22", "23"));
	public ArrayList<String> rlist3 = new ArrayList<String>(Arrays.asList("30", "31", "32", "em"));

	public ArrayList<String> list0 = new ArrayList<String>(Arrays.asList("00", "01", "02", "03"));
	public ArrayList<String> list1 = new ArrayList<String>(Arrays.asList("10", "11", "12", "13"));
	public ArrayList<String> list2 = new ArrayList<String>(Arrays.asList("20", "21", "22", "23"));
	public ArrayList<String> list3 = new ArrayList<String>(Arrays.asList("30", "31", "32", "em"));
	public ArrayList<ArrayList<String>> listY = new ArrayList<ArrayList<String>>();
	
	private ArrayList<String> all = new ArrayList<String>(Arrays.asList("00", "01", "02", "03", "10", "11", "12", "13", "20", "21", "22", "23", "30", "31", "32", "em"));

	public SliderBase() {
		listY.add(list0);
		listY.add(list1);
		listY.add(list2);
		listY.add(list3);
	}
	@Deprecated
	public void scramble(){
		Random rand = new Random();
		int x = 0;
		int y = 0;
		int n = 16;
		for (int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				int o = rand.nextInt(n);
				listY.get(x).set(y, all.get(o));
				all.remove(o);
				x++;
				n--;
			}
			x = 0;
			y++;
		}
		repaint();
	}
	
	public void possibleScramble(){
		Random rand = new Random();
		for(int i = 0; i<rand.nextInt(16) + 100000; i++){
			int r = rand.nextInt(getSurroundingTiles(getXOf("em"), getYOf("em")).size());
			moveTile(getXOf(getSurroundingTiles(getXOf("em"), getYOf("em")).get(r)), getYOf(getSurroundingTiles(getXOf("em"), getYOf("em")).get(r)));
		}
		t = new Timer(10,this);
		t.start();
	}
	
	public int getYOf(String tile){
		if(list0.indexOf(tile) != -1){
			return 0;
		}else
		if(list1.indexOf(tile) != -1){
			return 1;
		}else
		if(list2.indexOf(tile) != -1){
			return 2;
		}else{
			return 3;
		}
	}
	
	public int getXOf(String tile){
		return listY.get(getYOf(tile)).indexOf(tile);
	}

	public String getTile(int x, int y) {
		return listY.get(y).get(x);
	}

	public ArrayList<String> getSurroundingTiles(int x, int y) {
		ArrayList<String> a = new ArrayList<String>();
		if (x < 3) {
			a.add(getTile(x + 1, y));
		}
		if (x > 0) {
			a.add(getTile(x - 1, y));
		}
		if (y < 3) {
			a.add(getTile(x, y + 1));
		}
		if (y > 0) {
			a.add(getTile(x, y - 1));
		}
		return a;
	}

	public boolean reachableByEm(int x, int y) {
		if (list0.indexOf("em") != -1 && getSurroundingTiles(list0.indexOf("em"), 0).indexOf(getTile(x, y)) != -1) {
			return true;
		} else if (list1.indexOf("em") != -1
				&& getSurroundingTiles(list1.indexOf("em"), 1).indexOf(getTile(x, y)) != -1) {
			return true;
		} else if (list2.indexOf("em") != -1
				&& getSurroundingTiles(list2.indexOf("em"), 2).indexOf(getTile(x, y)) != -1) {
			return true;
		} else if (list3.indexOf("em") != -1
				&& getSurroundingTiles(list3.indexOf("em"), 3).indexOf(getTile(x, y)) != -1) {
			return true;
		} else {
			return false;
		}

	}

	public void moveString(int x, int y) {
		if (reachableByEm(x, y)) {
			if (list0.indexOf("em") != -1) {
				list0.set(list0.indexOf("em"), getTile(x, y));
				listY.get(y).set(x, "em");
			} else if (list1.indexOf("em") != -1) {
				list1.set(list1.indexOf("em"), getTile(x, y));
				listY.get(y).set(x, "em");
			} else if (list2.indexOf("em") != -1) {
				list2.set(list2.indexOf("em"), getTile(x, y));
				listY.get(y).set(x, "em");
			} else if (list3.indexOf("em") != -1) {
				list3.set(list3.indexOf("em"), getTile(x, y));
				listY.get(y).set(x, "em");
			}
		}
	}

	public void moveTile(int x, int y) {
		moveString(x, y);
		repaint();
	}

	public void paintComponent(Graphics g) {
		int x = 0;
		int y = 0;
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 4; i++) {
				image = new ImageIcon("resources/images/" + getTile(x, y) + ".jpeg").getImage();
				setPreferredSize(new Dimension(128, 128));
				setLayout(null);
				g.drawImage(image, x * 128, y * 128, 128, 128, null);
				x++;
			}
			y++;
			x = 0;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == t){
			if(list0.equals(rlist0) && list1.equals(rlist1) && list2.equals(rlist2) && list3.equals(rlist3)){
				list3.set(3, "33");
				repaint();
			}
		}
	}
}
