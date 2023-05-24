package Paket;

import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	//Mina variabler
	Scanner scan; //Skapar en Scanner objekt för att kunna ta emot input från användaren
	int choice; //En integer för att hålla koll på användarens val
	String[] plats; //En array som ska innehålla listan med bokade platser
	
	public static void main(String[] args) {

		//Förberedelse för programmets struktur
		Main boksys;
		boksys = new Main();
				
		boksys.Setup(); //Initialiserar listan med bokade platser
		boksys.Menu(); //Öppnar huvudmenyn
		boksys.Bokning(); //Hanterar bokning
		boksys.delBokning(); //Tar bort passageraren
		boksys.Antal_plats(); //Räknar antalet lediga platser
		boksys.Vinst(); //Beräknar vinsten
		boksys.Kund_list(); //Visar en lista på kunder
		boksys.sortKund_list(); //Visar en sorterad lista på kunder
		
	}

    // Förberedelse innan programmet börjar
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
			System.out.println("Bokningssystem för bussresa");
			System.out.println("\n(Skriv siffran för att välja följande alternativer)\n");
			
			System.out.println("1. Boka en obokad plats");
			System.out.println("2. Ta bort en bokad plats");
			System.out.println("3. Lediga platser");
			System.out.println("4. Vinsten av antalet sålda biljetter");
			System.out.println("5. Se kundlistan");
			System.out.println("6. Se sorterad efter födelsedatum kundlistan");
			System.out.println("7. Avsluta programmet\n");
			
			//Låter dig att skriva vilket alternativ du vill att välja
			scan = new Scanner(System.in);
	        choice = scan.nextInt(); 
	        scan.nextLine(); 
			
	        //Switch-sats som hanterar användarens val i menyn
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
			    
			    default: System.out.println("Fel siffra skriv en gång till!"); //Visa meddelandet om användaren matar in ett ogiltigt siffra
			    	break;
			}	
		}
	}
	
	
	//Metod för att boka en plats
	public void Bokning() {
	    System.out.print("Ange förnamn, efternamn och födelsedatum (yyyymmdd) för att boka plats: ");
	    String input = scan.nextLine();
	    boolean platsBokad = false;
	    
	    //Loopa igenom listan med platser för att hitta en ledig plats att boka
	    for (int i = 0; i < plats.length; i++) {
	        if (plats[i] == null) {
	            plats[i] = input;
	            System.out.println("Plats " + (i + 1) + " har bokats åt " + input + ".");
	            platsBokad = true;
	            break;
	        }
	    }
	    
	    //Visa meddelandet om det inte finns lediga platser
	    if (!platsBokad) {
	        System.out.println("Tyvärr finns det ingen ledig plats");
	    } 
	    Menu();	
	}
	
	
    //Metod för att ta bort en bokning
	public void delBokning() {
		System.out.println("PRESS ONE TIME ENTER BEFORE WRITING!");
	    System.out.print("Ange passagerarens förnamn, efternamn eller födelsedatum (yyyymmdd) för att ta bort: ");
	    scan.nextLine();
	    String detaljer = scan.nextLine();
	    boolean pasHittad = false;
	    
	    //Loopa igenom listan med platser för att hitta och ta bort passageraren
	    for (int i = 0; i < plats.length; i++) {
	        if (plats[i] != null && plats[i].toLowerCase().contains(detaljer.toLowerCase())) {
	            System.out.println("Passageraren "+ plats[i] +" har blivit borttagen från plats " + (i + 1));
	            plats[i] = null;
	            pasHittad = true;
	        }
	    }
	    
	    //Visa meddelandet om det inte hittas någon passagerare
	    if (!pasHittad) {
	        System.out.println("Passagerare ej hittad.");
	    }
	    Menu();
	}


	//Metod för att räkna lediga platser
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
    
    
   //Metod för att beräkna total vinst och skriva ut den
   public void Vinst() {
       double totalVinst = vinstBeräkning(plats);
       System.out.println("Total vinst: " + totalVinst + " kr");
       Menu();
   }

//Hjälpmetod för att beräkna vinsten
private double vinstBeräkning(String[] plats) {
    double vinst = 0.0;
    for (String passagerare : plats) {
        if (passagerare != null) {
            double biljettPris = prisBeräkning(passagerare);
            vinst += biljettPris;
        }
    }
    return vinst;
}

    //Hjälpmetod för att beräkna biljettpris utifrån ålder
    private double prisBeräkning(String passagerare) {
        String[] parts = passagerare.split(" ");
        String StrfödelseDatum = parts[2];
        int födelseDatum = Integer.parseInt(StrfödelseDatum);
        int idag = Integer.parseInt(fåIdag());
        int ålder = (idag - födelseDatum) / 10000;
        // Add year 2023 to make the age system work
        if (ålder < 18 && födelseDatum <= 20230000) {
            return 149.90;
        } else if (ålder > 69 || födelseDatum <= 19000000) {
            return 200.0;
        } else {
            return 299.90;
        }
    }

    //Hjälpmetod för att få dagens datum som en sträng
    private String fåIdag() {
        DateFormat datumFormat = new SimpleDateFormat("yyyymmdd");
        Date datum = new Date();
        return datumFormat.format(datum);
    }
    
    
    //Metod för att skriva ut en lista på kunder
    public void Kund_list() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Kundlista:");
        for (String kunder : plats) {
            System.out.println(kunder);
        }
        Menu();
    }
    
    
    // Metod för att sortera kundlistan efter födelsedatum och skriva ut den
    public void sortKund_list() {
        String[] nonNullPlats = Arrays.stream(plats)
                                      .filter(p -> p != null)
                                      .toArray(String[]::new);
        Arrays.sort(nonNullPlats, Comparator.comparing(p -> p.substring(p.length() - 8)));
        System.out.println("\nKundlista sorterad efter födelsedatum:");
        
        for (String p : nonNullPlats) {
            System.out.println(p);
        }
        Menu();
    }
}
