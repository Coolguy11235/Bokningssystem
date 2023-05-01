package Paket;

import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	//Mina variabler
	Scanner scan; //Skapar en Scanner objekt f�r att kunna ta emot input fr�n anv�ndaren
	int choice; //En integer f�r att h�lla koll p� anv�ndarens val
	String[] plats; //En array som ska inneh�lla listan med bokade platser
	
	public static void main(String[] args) {

		//F�rberedelse f�r programmets struktur
		Main boksys;
		boksys = new Main();
				
		boksys.Setup(); //Initialiserar listan med bokade platser
		boksys.Menu(); //�ppnar huvudmenyn
		boksys.Bokning(); //Hanterar bokning
		boksys.delBokning(); //Tar bort passageraren
		boksys.Antal_plats(); //R�knar antalet lediga platser
		boksys.Vinst(); //Ber�knar vinsten
		boksys.Kund_list(); //Visar en lista p� kunder
		boksys.sortKund_list(); //Visar en sorterad lista p� kunder
		
	}

    // F�rberedelse innan programmet b�rjar
    public void Setup() {
    	
        // Reserverade platser
        plats = new String[] {
                "Emma Johnson 20010921",
                "Noah Williams 19890325",
                "Ava Brown 19990225",
                "Ethan Davis 20020611",
                "Olivia Garcia 20041112",
                "Liam Martinez 20100504",
                "Sophia Rodriguez 19900110",
                "Mason Anderson 19911225",
                "Isabella Wilson 19780911",
                "James Jackson 19480602",
                "Mia Thompson 20120322",
                "Benjamin Hernandez 20060529",
                "Charlotte Lee 20081105",
                "Lucas White 20110511",
                "Amelia Adams 20030925",
                "Alexander Kim 19680217",
                "Harper Turner 19970902",
                "William Clark 19580306",
                "Madison Baker 19950406",
                "Henry Wright 19891121",
                "Abigail Collins 19770511"
        };
    }
	
	//Meny
	public void Menu() {
			
		while (true) {
			System.out.println("\n-------------------------------------------------\n");
			System.out.println("Bokningssystem f�r bussresa");
			System.out.println("\n(Skriv siffran f�r att v�lja f�ljande alternativer)\n");
			
			System.out.println("1. Boka en obokad plats");
			System.out.println("2. Ta bort en bokad plats");
			System.out.println("3. Lediga platser");
			System.out.println("4. Vinsten av antalet s�lda biljetter");
			System.out.println("5. Se kundlistan");
			System.out.println("6. Se sorterad efter f�delsedatum kundlistan");
			System.out.println("7. Avsluta programmet\n");
			
			//L�ter dig att skriva vilket alternativ du vill att v�lja
			scan = new Scanner(System.in);
	        choice = scan.nextInt(); 
	        scan.nextLine(); 
			
	        //Switch-sats som hanterar anv�ndarens val i menyn
	        //Case anropar metoder
			switch (choice) {
			    case 1: Bokning(); 
			    	break;
			    	
			    case 2: delBokning();
		    	break;
			    	
			    case 3: Antal_plats();
			    	break;
			    	
			    case 4: Vinst();
			    	return;
			    	
			    case 5: Kund_list();
			    	return;
			    	
			    case 6: sortKund_list();
			    	return;
			    	
			    case 7: System.exit(choice); //Avsluta programmet
			    	break;
			    
			    default: System.out.println("Fel siffra skriv en g�ng till!"); //Visa meddelandet om anv�ndaren matar in ett ogiltigt siffra
			    	break;
			}	
		}
	}
	
	
	//Metod f�r att boka en plats
	public void Bokning() {
	    System.out.print("Ange f�rnamn, efternamn och f�delsedatum (yyyymmdd) f�r att boka plats: ");
	    String input = scan.nextLine();
	    boolean platsBokad = false;
	    
	    //Loopa igenom listan med platser f�r att hitta en ledig plats att boka
	    for (int i = 0; i < plats.length; i++) {
	        if (plats[i] == null) {
	            plats[i] = input;
	            System.out.println("Plats " + (i + 1) + " har bokats �t " + input + ".");
	            platsBokad = true;
	            break;
	        }
	    }
	    
	    //Visa meddelandet om det inte finns lediga platser
	    if (!platsBokad) {
	        System.out.println("Tyv�rr finns det ingen ledig plats");
	    } 
	    Menu();	
	}
	
	
    //Metod f�r att ta bort en bokning
	public void delBokning() {
		System.out.println("PRESS ONE TIME ENTER BEFORE WRITING!");
	    System.out.print("Ange passagerarens f�rnamn, efternamn eller f�delsedatum (yyyymmdd) f�r att ta bort: ");
	    scan.nextLine();
	    String detaljer = scan.nextLine();
	    boolean pasHittad = false;
	    
	    //Loopa igenom listan med platser f�r att hitta och ta bort passageraren
	    for (int i = 0; i < plats.length; i++) {
	        if (plats[i] != null && plats[i].toLowerCase().contains(detaljer.toLowerCase())) {
	            System.out.println("Passageraren "+ plats[i] +" har blivit borttagen fr�n plats " + (i + 1));
	            plats[i] = null;
	            pasHittad = true;
	        }
	    }
	    
	    //Visa meddelandet om det inte hittas n�gon passagerare
	    if (!pasHittad) {
	        System.out.println("Passagerare ej hittad.");
	    }
	    Menu();
	}


	//Metod f�r att r�kna lediga platser
    public void Antal_plats() {
    	int ledigaPlatser = 0;
        for (int i = 0; i < plats.length; i++) {
            if (plats[i] == null) {
                ledigaPlatser++;
            }
        }
        System.out.println("Antal lediga platser: " + ledigaPlatser);
        Menu();
	}
    
    
  //Metod f�r att ber�kna total vinst och skriva ut den
    public void Vinst() {
        double totalVinst = vinstBer�kning(plats, 0);
        System.out.println("Total vinst: " + totalVinst + " kr");
        Menu();
    }

    //Hj�lpmetod f�r att ber�kna vinsten
    private double vinstBer�kning(String[] plats, int index) {
        if (index >= plats.length) {
            return 0.0;
        }
        String passagerare = plats[index];
        if (passagerare == null) {
            return 0.0; // return 0 kr if passenger has left
        }
        double biljettPris = prisBer�kning(passagerare);
        double vinst = biljettPris + vinstBer�kning(plats, index + 1);
        return vinst;
    }

    //Hj�lpmetod f�r att ber�kna biljettpris utifr�n �lder
    private double prisBer�kning(String passagerare) {
        String[] parts = passagerare.split(" ");
        String Strf�delseDatum = parts[2];
        int f�delseDatum = Integer.parseInt(Strf�delseDatum);
        int idag = Integer.parseInt(f�Idag());
        int �lder = (idag - f�delseDatum) / 10000;
        // Add year 2023 to make the age system work
        if (�lder < 18 && f�delseDatum <= 20230000) {
            return 149.90;
        } else if (�lder > 69 || f�delseDatum <= 19000000) {
            return 200.0;
        } else {
            return 299.90;
        }
    }

    //Hj�lpmetod f�r att f� dagens datum som en str�ng
    private String f�Idag() {
        DateFormat datumFormat = new SimpleDateFormat("yyyymmdd");
        Date datum = new Date();
        return datumFormat.format(datum);
    }
    
    
    //Metod f�r att skriva ut en lista p� kunder
    public void Kund_list() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Kundlista:");
        for (String kunder : plats) {
            System.out.println(kunder);
        }
        Menu();
    }
    
    
    // Metod f�r att sortera kundlistan efter f�delsedatum och skriva ut den
    public void sortKund_list() {
        String[] nonNullPlats = Arrays.stream(plats)
                                      .filter(p -> p != null)
                                      .toArray(String[]::new);
        Arrays.sort(nonNullPlats, Comparator.comparing(p -> p.substring(p.length() - 8)));
        System.out.println("\nKundlista sorterad efter f�delsedatum:");
        
        for (String p : nonNullPlats) {
            System.out.println(p);
        }
        Menu();
    }
}
