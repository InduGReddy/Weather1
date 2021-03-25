package Weather;                                        
                                                                    
import java.io.BufferedReader;                                      
import java.io.FileNotFoundException;                               
import java.io.InputStreamReader;                                   
import java.net.URL;                                                
import java.net.URLConnection;                                      
import java.time.Instant;                                           
import java.time.ZoneId;                                            
import java.time.ZonedDateTime;                                     
import java.util.Scanner;                                           
import java.time.*;                                                 
                                                                    
import org.json.simple.JSONArray;                                   
import org.json.simple.JSONObject;                                  
import org.json.simple.parser.JSONParser;                           
                                                                    
public class Weather                                                
{                                                                   
                                                                    
    public static void main ( String[] args )                       
    {                                                               
                                                                    
        Weather app = new Weather();                                
        app.demo();                                                 
    }                                                               
                                                                    
    private void demo ( )                                           
    {                                                               
                                                                    
        JSONParser jsonParser = new JSONParser();                   
        try                                                         
        {                                                           
                                                                    
            String yourKey = "b6907d289e10d714a6e88b30761fae22";
            URL url = new URL( "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22" + yourKey ); 
            URLConnection conn = url.openConnection();                                                                                                                                                    
            BufferedReader reader = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );

            JSONObject jsonObject = ( JSONObject ) jsonParser.parse( reader );                 
            System.out.println( "jsonObject = " + jsonObject );                                
                                                                                       
            JSONArray list = ( JSONArray ) jsonObject.get( "list" );                           
            System.out.println( "list = " + list );
            
            for ( Object o : list )                                                            
            {                                                                                  
              JSONObject forecast = ( JSONObject ) o;                                        
                                                                                       
              Long dt = ( Long ) forecast.get( "dt" );                                       
              Instant instant = Instant.ofEpochSecond( dt );                                 
              ZoneId z = ZoneId.of( "America/Chicago" );                                     
              ZonedDateTime zdt = instant.atZone(z );                                        
              LocalTime lt = zdt.toLocalTime() ;  
            
              JSONObject main = ( JSONObject ) forecast.get( "main" );  
            
              String dt_txt = ( String ) forecast.get( "dt_txt" );                      
     		  System.out.println( "dt_txt = " + dt_txt); 
     		  
     		  Scanner scan1 = new Scanner(System.in);                                          
              System.out.println("Enter the Key:");                                           
                
              int i = scan1.nextInt(); 
              
              if(i==1) {                                                                     
                   Double temp = ( Double ) main.get( "temp" );                                   
                   System.out.println( "temp = " + temp );                                       
                   }                                                                                
         	  else if(i == 2) {                                                              
         		  Double pressure = ( Double ) main.get( "pressure" );                       
         		  System.out.println( "pressure = " + pressure );                         
         		  }                                                                        
         	  else if(i == 3) {                                                              
         		  JSONObject wind = ( JSONObject ) forecast.get( "wind" );                      
         		  System.out.println( "wind = " + wind );                                       
         		  }                                                                                  
         	  else  {                                                                  
         		  System.out.println(" ");     
         		  System.exit(0);
         		  }
              }
            }catch ( FileNotFoundException e )
        {                                                                                      
        	e.printStackTrace();                                                               
        	}catch ( Exception e )                                                                 
        {                                                                                      
        	e.printStackTrace();                                                              
        	}   
        }                                                                           
    }