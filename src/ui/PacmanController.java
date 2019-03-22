package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import javafx.stage.StageStyle;
import model.Move;
import model.Pacman;

import threads.ControlThread;
import threads.MovingThread;

public class PacmanController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane;
    
    @FXML
    private Pane pane2;

    @FXML
    private MenuItem level0;

    @FXML
    private MenuItem level1;

    @FXML
    private MenuItem level2;

    @FXML
    private MenuItem saveGame;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem highScore;

    @FXML
    private MenuItem info;

    @FXML
    private Arc pac;

    @FXML
    private Label points;

    private boolean move;
    
    private int level;
    
    private int wait;
    
    private int point;
    
    //Relation
    
    private Pacman paco;
       
    private ArrayList<Pacman> pacos;
    private ArrayList<Arc> arcPacos;
    //private MovingThread mt;
    
    
    
    @FXML
    void exit(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void information(ActionEvent event) {
    	Alert info = new Alert(AlertType.INFORMATION);
    	info.setTitle("PacMan");
    	info.setHeaderText(null);
    	info.initStyle(StageStyle.UTILITY);
    	info.setContentText(" INSTRUCTIONS \n\n"+
    	" Choose a level of dificulty in the ''load Game'' option\n"+
    	" Click on the Pac-Mans before they bounce on the walls "+
    	" Every time they bounce you get a point get as less point you can"+
    	" so you can be in our hall of fame"+
    	" \n\ngood luck MotherF*kcers");
    	info.show();
    }

    @FXML
    void loadLevel0(ActionEvent event) {
    	pane2.getChildren().clear();
    	pacos.clear();
    	arcPacos.clear();
    	pane2.getChildren().clear();
    	setLevel(0);
    	String filePath = "Levels/level0.txt";
    	loadLevel(filePath);
    	
    	
    	
    	
    }

    @FXML
    void loadLevel1(ActionEvent event) {
    	pane2.getChildren().clear();
    	pacos.clear();
    	arcPacos.clear();
    	setLevel(1);
    	String filePath = "Levels/level1.txt";
    	loadLevel(filePath);
    	
    }

    @FXML
    void loadLevel2(ActionEvent event) {
    	pane2.getChildren().clear();
    	pane2.getChildren().clear();
    	pacos.clear();
    	arcPacos.clear();
    	setLevel(2);
    	String filePath = "Levels/level2.txt";
    	loadLevel(filePath);
    	
    }
    
    public void loadLevel(String filePath) {
	    double radio;
	    double posx;
	    double posy;
	    String direction;
	    int bounces;
	    boolean stoped;
    	try {
			FileReader levels = new FileReader(filePath);
			BufferedReader br = new BufferedReader(levels);
			String thisLine = br.readLine();
			while(thisLine != null) {
				String[] variables = thisLine.split("\t");
				radio = Double.parseDouble(variables[0]);
				posx = Double.parseDouble(variables[1]);
				posy = Double.parseDouble(variables[2]);
				wait = Integer.parseInt(variables[3]);
				direction = variables[4];
				
                Move move;
				
				if(direction.equalsIgnoreCase("RIGHT")) {
					move = Move.RIGHT;
				}else if(direction.equalsIgnoreCase("LEFT")) {
					move = Move.LEFT;
				}else if(direction.equalsIgnoreCase("DOWN")){
					move = Move.DOWN;
				}else {
					
					move = Move.UP;
				}
				bounces = Integer.parseInt(variables[5]);
				if(variables[6]=="false") {
					stoped = false;
				}else {
					stoped = true;
				}
				paco = new Pacman(radio, posx, posy, wait, bounces, stoped, move);
				pacos.add(paco);
				thisLine = br.readLine();
			}
			levels.close();
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
    	    	
    	for(int i = 0 ; i < pacos.size() ; i++) {
    		pac = new Arc(pacos.get(i).getPosx(), pacos.get(i).getPosy(), pacos.get(i).getRadio(), pacos.get(i).getRadio(), 32, 300);
    		pac.setFill(Color.YELLOW);
    		pac.setStroke(Color.BLACK);
    		pac.setStrokeWidth(3);
    		pac.setType(ArcType.ROUND);
    		pane2.getChildren().add(pac);
    		arcPacos.add(pac);
    		MovingThread bt = new MovingThread(this, pacos.get(i));
        	bt.setDaemon(true);
        	bt.start();
    		
    	}

    	
    }

   
    
    @FXML
    void save(ActionEvent event) throws FileNotFoundException {
    	
    	if(getLevel()==0) {
    	PrintWriter pw= new PrintWriter(new File("Levels/level0.txt"));
		  String evalu="";
		  File f=new File("Levels/level0.txt");
		  String nameFile=f.getName();
		  if(nameFile.equals("data/save.txt")) {
			  
		  }else {
			  for(int i=0;i<pacos.size();i++) {
				  evalu+=pacos.get(i).getRadio()+"	"+pacos.get(i).getPosx()+"	"+pacos.get(i).getPosy()+"	"+pacos.get(i).getWait()+"	"+pacos.get(i).getDirection()+"	"+pacos.get(i).isStoped();
			  }
		  } 
		  pw.println(evalu);
		  pw.close();
    	
    	} else if(getLevel()==1) {
        	PrintWriter pw= new PrintWriter(new File("Levels/level1.txt"));
  		  String evalu="";
  		  File f=new File("Levels/level1.txt");
  		  String nameFile=f.getName();
  		  if(nameFile.equals("data/save.txt")) {
  			  
  		  }else {
  			  for(int i=0;i<pacos.size();i++) {
  				  evalu+=pacos.get(i).getRadio()+"	"+pacos.get(i).getPosx()+"	"+pacos.get(i).getPosy()+"	"+pacos.get(i).getWait()+"	"+pacos.get(i).getDirection()+"	"+pacos.get(i).isStoped();
  			  }
  		  } 
  		  pw.println(evalu);
  		  pw.close();
      	
      	} else if(getLevel()==2) {
        	PrintWriter pw= new PrintWriter(new File("Levels/level2.txt"));
  		  String evalu="";
  		  File f=new File("Levels/level2.txt");
  		  String nameFile=f.getName();
  		  if(nameFile.equals("data/save.txt")) {
  			  
  		  }else {
  			  for(int i=0;i<pacos.size();i++) {
  				  evalu+=pacos.get(i).getRadio()+"	"+pacos.get(i).getPosx()+"	"+pacos.get(i).getPosy()+"	"+pacos.get(i).getWait()+"	"+pacos.get(i).getDirection()+"	"+pacos.get(i).isStoped()+"\n";
  			  }
  		  } 
  		  pw.println(evalu);
  		  pw.close();
      	
      	} else {
      		
      		Alert info = new Alert(AlertType.ERROR);
        	info.setTitle("Happened a error");
        	info.setHeaderText(null);
        	info.initStyle(StageStyle.UTILITY);
        	info.setContentText(" \r\n" + 
        			"a serious error has occurred please close the program");
        	info.show();
      		
      	}
    	
    	pane2.getChildren().clear();
    	
    	

    }

    @FXML
    void show(ActionEvent event) {
    	Alert info = new Alert(AlertType.ERROR);
    	info.setTitle("Happened a error");
    	info.setHeaderText(null);
    	info.initStyle(StageStyle.UTILITY);
    	info.setContentText(" \r\n" + 
    			"Se intento pero no hubo time, que se aprecie el esfuerzo JAJAJA");
    	info.show();
  		
    	
    }
    
    public boolean right(boolean move) {
    	if(move) {
    		pac.setLayoutX(pac.getLayoutX()+6);
    	}
    	if(pac.getLayoutX()>pane2.getWidth()-pac.getRadiusX()) {
    		move = false;
    		pac.setRotate(180);
    		points();
    	}
		return move;
    }
    
    public boolean left(boolean move) {
    	if(!move) {
    		pac.setLayoutX(pac.getLayoutX()-6);
    	}
    	if(pac.getLayoutX()<=pac.getRadiusX()) {
    		
			move = true;
			pac.setRotate(0);
			points();
		}	
		return move;
    }
    
    public boolean closeMouth(boolean sprite) {
    	if(sprite) {
    		pac.setLength(pac.getLength()+5);
    		pac.setStartAngle(pac.getStartAngle()-3);
    	}
    	if(pac.getLength()>=360) {
    		move = false;
    	}
		return move;
    }
    
    public boolean openMouth(boolean sprite) {
    	if(!sprite) {
    		pac.setLength(pac.getLength()-5);
    		pac.setStartAngle(pac.getStartAngle()+3);
    	}
    	if(pac.getLength()<270) {
    		move = true;
    	}
		return move;
    }
    
    public int points() {
		
		point++;
		System.out.println(point);
		
		return point;
	}
    
    @FXML
    void initialize() {
    	level=3;
        pacos = new ArrayList<Pacman>();
        arcPacos= new ArrayList<Arc>();
        ControlThread pacmt = new ControlThread(this);
    	pacmt.setDaemon(true);
    	pacmt.start();
    }

	
    public int getLevel() {
		return level;
	}
    
    public void update() {
    	for (int id = 0; id < pacos.size(); id++) {
    		arcPacos.get(id).setLayoutX(pacos.get(id).getPosx());
    		arcPacos.get(id).setLayoutY(pacos.get(id).getPosy());			
		}
    }
    
    public double getWith() {
    	double a=pane2.getWidth();
		return a;
	}
    
    public double getHeigth() {
    	double b=pane2.getHeight();
		return b;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}