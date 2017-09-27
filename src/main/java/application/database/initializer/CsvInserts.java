package application.database.initializer;


import application.database.*;
import application.database.repositories.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.text.*;


@Service
public class CsvInserts{

    public void loginCsvInsertions(String csvFile, LoginRepository loginRepository){
        String line="";
        String csvSplitBy=",";
        BufferedReader buf =null;
        try{
            buf = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF8"));
            buf.readLine();
            while((line = buf.readLine()) != null){
                String[] arr_in = line.split(csvSplitBy);
                Login login=new Login();
                login.setUsername(arr_in[0]);
                login.setEmail(arr_in[1]);
                login.setEnabled(true);
                login.setName(arr_in[3]);
                login.setPassword(arr_in[4]);
                login.setPhoneNum(arr_in[5]);
                login.setPhotoPath(arr_in[6]);
                login.setSurname(arr_in[7]);
                login.setValid(true);
                loginRepository.save(login);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (buf != null) {
                try {
                    buf.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void apartmentCsvInsertions(String csvFile, ApartmentRepository apartmentRepository ,LoginRepository loginRepository){
        String line="";
        String csvSplitBy=",";
        BufferedReader buf =null;
        try{
            buf = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF8"));
            buf.readLine();
            while((line = buf.readLine()) != null){
                String[] arr_in = line.split(csvSplitBy);
                Apartment apartment=new Apartment();
                apartment.setApartmentId(Integer.parseInt(arr_in[0]));
                apartment.setAircondition(Boolean.parseBoolean(arr_in[1]));
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator(',');
                symbols.setDecimalSeparator('.');
                String pattern = "#,##0.0#";
                DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
                decimalFormat.setParseBigDecimal(true);
                try{
                    apartment.setArea((BigDecimal) decimalFormat.parse(arr_in[2]));
                }catch (ParseException e){
                    e.printStackTrace();
                }
                apartment.setBaths(Short.parseShort(arr_in[3]));
                apartment.setBed(Short.parseShort(arr_in[4]));
                apartment.setCleanPrice(Short.parseShort(arr_in[5]));
                apartment.setEvents(Boolean.parseBoolean(arr_in[6]));
                apartment.setFridge(Boolean.parseBoolean(arr_in[7]));
                apartment.setGarage(Boolean.parseBoolean(arr_in[8]));
                apartment.setHeat(Boolean.parseBoolean(arr_in[9]));
                apartment.setHouseDescription(arr_in[10]);
                apartment.setKitchen(Boolean.parseBoolean(arr_in[11]));
                try{
                    apartment.setLat((BigDecimal) decimalFormat.parse(arr_in[12]));
                }catch (ParseException e){
                    e.printStackTrace();
                }
                apartment.setLeavingRoom(Boolean.parseBoolean(arr_in[13]));
                apartment.setLift(Boolean.parseBoolean(arr_in[14]));
                apartment.setLocality(arr_in[15]);
                try{
                    apartment.setLon((BigDecimal) decimalFormat.parse(arr_in[16]));
                }catch (ParseException e){
                    e.printStackTrace();
                }
                apartment.setMaxPeople(Short.parseShort(arr_in[17]));
                apartment.setMinPeople(Short.parseShort(arr_in[18]));
                apartment.setName(arr_in[19]);
                apartment.setParking(Boolean.parseBoolean(arr_in[20]));
                apartment.setPets(Boolean.parseBoolean(arr_in[21]));
                apartment.setPlusPrice(Short.parseShort(arr_in[22]));
                apartment.setPrice(Short.parseShort(arr_in[23]));
                apartment.setRooms(Short.parseShort(arr_in[24]));
                apartment.setRules(arr_in[25]);
                apartment.setSmoking(Boolean.parseBoolean(arr_in[26]));
                apartment.setStandardPeople(Short.parseShort(arr_in[27]));
                apartment.setTrasnportationDescription(arr_in[28]);
                apartment.setTv(Boolean.parseBoolean(arr_in[29]));
                apartment.setType(arr_in[30]);
                apartment.setWiFi(Boolean.parseBoolean(arr_in[31]));
                apartment.setLogin(loginRepository.findOne(arr_in[32]));
                apartment.setCountry(arr_in[33]);
                apartment.setStreetNumber(arr_in[34]);
                apartment.setRoute(arr_in[35]);
                apartmentRepository.save(apartment);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (buf != null) {
                try {
                    buf.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void userRoleCsvInsertions(String csvFile, LoginRepository loginRepository, UserRoleRepository userRoleRepository){
        String line="";
        String csvSplitBy=",";
        BufferedReader buf =null;
        try{
            buf = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF8"));
            buf.readLine();
            while((line = buf.readLine()) != null){
                String[] arr_in = line.split(csvSplitBy);
                UserRole userRole=new UserRole();
                userRole.setRoleId(Integer.parseInt(arr_in[0]));
                userRole.setRole(arr_in[2]);
                userRole.setLogin(loginRepository.findOne(arr_in[1]));
                userRoleRepository.save(userRole);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (buf != null) {
                try {
                    buf.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void availabilityCsvInsertions(String csvFile, AvailabilityRepository availabilityRepository,ApartmentRepository apartmentRepository){
        String line="";
        String csvSplitBy=",";
        BufferedReader buf =null;
        try{
            buf = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF8"));
            buf.readLine();
            while((line = buf.readLine()) != null){
                String[] arr_in = line.split(csvSplitBy);
                Availability availability=new Availability();
                availability.setAvailabilityId(Integer.parseInt(arr_in[0]));
                DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                try{
                    availability.setFromAv(format.parse(arr_in[1]));
                }catch (ParseException e){
                    e.printStackTrace();
                }
                try{
                    availability.setToAv(format.parse(arr_in[2]));
                }catch (ParseException e){
                    e.printStackTrace();
                }
                availability.setApartment(apartmentRepository.findOne(Integer.parseInt(arr_in[3])));
                availabilityRepository.save(availability);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (buf != null) {
                try {
                    buf.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void bookInfoCsvInsertions(String csvFile, BookInfoRepository bookInfoRepository, ApartmentRepository apartmentRepository ,LoginRepository loginRepository){
        String line="";
        String csvSplitBy=",";
        BufferedReader buf =null;
        try{
            buf = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF8"));
            buf.readLine();
            while((line = buf.readLine()) != null){
                String[] arr_in = line.split(csvSplitBy);
                BookInfo bookInfo=new BookInfo();
                bookInfo.setBookId(Integer.parseInt(arr_in[0]));
                bookInfo.setApartment(apartmentRepository.findOne(Integer.parseInt(arr_in[1])));
                DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                try{
                    bookInfo.setBookIn(format.parse(arr_in[2]));
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                try{
                    bookInfo.setBookOut(format.parse(arr_in[3]));
                }catch (ParseException e){
                    e.printStackTrace();
                }
                bookInfo.setLogin(loginRepository.findOne(arr_in[5]));
                bookInfo.setReviewDone(Boolean.parseBoolean(arr_in[6]));
                bookInfo.setReviewDone(Boolean.parseBoolean(arr_in[9]));
                bookInfoRepository.save(bookInfo);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (buf != null) {
                try {
                    buf.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}