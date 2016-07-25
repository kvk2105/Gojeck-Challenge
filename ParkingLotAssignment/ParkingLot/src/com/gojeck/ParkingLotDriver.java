package com.gojeck;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vamshi on 7/7/16.
 */
public class ParkingLotDriver {
    private static final Logger LOGGER=Logger.getLogger(ParkingLotDriver.class.getName());
    static ParkingLot parkingLot=null;

    public static String processCommand(String command){
        String[] commandDetails=command.split(" ");
        if(commandDetails.length==0){
            System.out.println("Invalid command");
            return "Invalid command";
        }
        String action=commandDetails[0];
        if(action.equalsIgnoreCase(Constants.create_parking_lot)){

            parkingLot=new ParkingLot(Long.parseLong(commandDetails[1]));
            System.out.println("Created a parking lot with "+commandDetails[1]+" slots");
        return "Created a parking lot with "+commandDetails[1]+" slots";
        }else if(action.equalsIgnoreCase(Constants.park)){

            if(parkingLot.isLotFull()){
                System.out.println("Sorry, parking lot is full");
                return "Sorry, parking lot is full";
            }
            Slot slot=new Slot(parkingLot.getFreeSlot(),commandDetails[1],commandDetails[2]);
            parkingLot.park(slot);
            System.out.println("Allocated slot number: "+slot.getSlot());
            return "Allocated slot number: "+slot.getSlot();
        }else if(action.equalsIgnoreCase(Constants.leave)){

            if(parkingLot.isLotEmpty()){
                System.out.println("Invalid command as parking lot is already empty");
                return "Invalid command as parking lot is already empty";
            }
            parkingLot.leave(Long.parseLong(commandDetails[1]));
            System.out.println("Slot number "+commandDetails[1]+" is free");
            return "Slot number "+commandDetails[1]+" is free";
        }else if(action.equalsIgnoreCase(Constants.status)){
            System.out.println("Slot No.\tRegistration No\tColour");
            Map<Long,Slot> slots=parkingLot.getSlots();
            for (Map.Entry<Long,Boolean> entry:parkingLot.getSlotsAvailabilityMap().entrySet()){
                if(slots.get(entry.getKey())!=null)
                    System.out.println(slots.get(entry.getKey()).toString());
            }

        }else if(action.equalsIgnoreCase(Constants.registration_numbers_for_cars_with_colour)){
            List<String> regNumbers=parkingLot.getRegNumbersByColor(commandDetails[1]);
            StringBuffer sb=new StringBuffer();
            for (String regNum:regNumbers)
                sb.append(regNum + ",");
            System.out.println(sb.charAt(sb.length() - 1) == ',' ? sb.substring(0,sb.length()-1).toString():sb.substring(0,sb.length()).toString());
            return sb.charAt(sb.length()-1)==','?sb.substring(0,sb.length()-1).toString():sb.substring(0,sb.length()).toString();
        }else if(action.equalsIgnoreCase(Constants.slot_number_for_registration_number)){
            long slotNum=parkingLot.getSlotByRegNumber(commandDetails[1]);
            if(slotNum==-1){
                System.out.println("Not found");
                return "Not found";
            }
            System.out.println(slotNum);
            return String.valueOf(slotNum);
        }else if(action.equalsIgnoreCase(Constants.slot_numbers_for_cars_with_colour)){
            List<Long> slotsByColor=parkingLot.getSlotsByColor(commandDetails[1]);
            if(slotsByColor.size()==0){
                System.out.println("Not found");
                return "Not found";
            }
            StringBuffer sb=new StringBuffer();
            for (Long slotNum:slotsByColor)
                sb.append(slotNum).append(",");
            System.out.println(sb.substring(0,sb.length()-1).toString());
            return sb.substring(0,sb.length()-1).toString();
        }else {
            System.out.println("Invalid command");
            return "Invalid command";
        }
        return "";
    }

    public static void main(String[] args){
        boolean isInteractive=false;
        if(args.length ==0){
            isInteractive=true;
        }else if(args.length==1){
            isInteractive=false;
        } else {
            System.out.println("Wrong way to run program..! \n"+
                    "With File as inout: my_program inputFile.txt \n"+
                    "With interactive mode: my_program");
            System.exit(1);
        }
        if(isInteractive){
            Scanner sc=new Scanner(new InputStreamReader(System.in));
            String input;
            while (sc.hasNextLine()){
                input=sc.nextLine();
                if(input.isEmpty())
                    return;
                processCommand(input);
            }
        }else {
            try {
                BufferedReader br=new BufferedReader(new FileReader(new File(args[0])));
                String line;
                while((line=br.readLine())!=null){
                    processCommand(line);
                }
            }catch (FileNotFoundException e){
                LOGGER.log(Level.FINE,e.getMessage(),e);
            }catch (IOException ioe){
                LOGGER.log(Level.FINE,ioe.getMessage(),ioe);
            }
        }
    }
}
